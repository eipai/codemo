package com.github.eipai.elasticsearch.basic;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
    private String host = "127.0.0.1";
    private int port = 9300;
    Client client = null;

    @Test
    public void test() throws Exception {

        // saveTestDoc("pay_order", 1);

        // bulkSaveTest("pay_order", 10);

        printFirst1000("pay_order");

        // deleteFirst1000("pay_order");

    }

    public void bulkSaveTest(String index, int bulkSize) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for (int i = 0; i < bulkSize; i++) {
            Map<String, Object> doc = getTestDoc();
            bulkRequest.add(client.prepareIndex(index, "main", (String) doc.get("id")).setSource(doc));
        }
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        System.out.println("bulk save: " + ToStringBuilder.reflectionToString(bulkResponse));
        for (int i = 0; i < bulkResponse.getItems().length; i++) {
            System.out.println("bulk save item: " + ToStringBuilder.reflectionToString(bulkResponse.getItems()[i]));
        }
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
        }
    }

    public Map<String, Object> getTestDoc() {
        String id = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + RandomStringUtils.randomNumeric(4);
        Map<String, Object> doc = new HashMap<String, Object>();
        doc.put("request_no", id);
        doc.put("trade_amount", 100);
        doc.put("bank_code", "BOC");
        doc.put("id", id);
        return doc;
    }

    public void saveTestDoc(String index, int count) {
        for (int i = 0; i < count; i++) {
            String id = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            Map<String, Object> doc = new HashMap<String, Object>();
            doc.put("request_no", id);
            doc.put("trade_amount", 1000 + i);
            doc.put("bank_code", "BOC");
            IndexResponse indexResponse = saveDoc("pay_order", id, doc);
            System.out.println("save doc: [" + id + "]" + indexResponse.getResult());
        }
    }

    public void printFirst1000(String index) {
        SearchResponse docs = searchDoc(index, 0, 1000);
        System.out.println(ToStringBuilder.reflectionToString(docs));
        System.out.println(docs.toString());
        SearchHits hits = docs.getHits();
        for (int i = 0; i < hits.totalHits; i++) {
            System.out.println("Search Hit-" + i + ": [" + hits.getAt(i).getId() + "]" + hits.getAt(i).getSource());
        }
    }

    public void deleteFirst1000(String index) {
        SearchResponse docs = searchDoc(index, 0, 1000);
        SearchHits hits = docs.getHits();
        for (int i = 0; i < hits.totalHits; i++) {
            DeleteResponse deleteDoc = deleteDoc(index, hits.getAt(i).getId());
            System.out.println("delete doc: [" + hits.getAt(i).getId() + "]" + deleteDoc.getResult());
        }
    }

    public SearchResponse searchDoc(String index, int pageFrom, int pageSize) {
        QueryBuilder qb = QueryBuilders.boolQuery();
        SearchResponse actionGet = client.prepareSearch(index).setTypes("main").setQuery(qb).setFrom(pageFrom).setSize(pageSize).execute().actionGet();
        return actionGet;
    }

    public GetResponse getDoc(String index, String id) {
        GetResponse response = client.prepareGet(index, "main", id).get();
        return response;
    }

    @Before
    public void init() throws Exception {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
        client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(this.host), this.port));
    }

    @After
    public void exit() {
        client.close();
    }

    public DeleteResponse deleteDoc(String index, String id) {
        DeleteResponse response = client.prepareDelete(index, "main", id).execute().actionGet();
        return response;
    }

    public IndexResponse saveDoc(String index, String id, Map<String, Object> doc) {
        System.out.println(">>>>>>> id=" + id + "; " + doc);
        return client.prepareIndex(index, "main", id).setSource(doc).execute().actionGet();
    }

}
