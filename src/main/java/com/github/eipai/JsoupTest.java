package com.github.eipai;

import java.net.SocketTimeoutException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {

    public static void main(String[] args) throws Exception {
        try {
            Document doc = Jsoup.connect("http://ec.iresearch.cn/").timeout(15000).get();
            Elements es = doc.select("a");
            for (Element e : es) {
                System.out.println(e.text());
            }
        } catch (SocketTimeoutException e) {
            System.out.println("timeout!");
        }
    }

}
