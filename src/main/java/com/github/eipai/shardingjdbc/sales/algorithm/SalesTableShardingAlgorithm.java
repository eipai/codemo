package com.github.eipai.shardingjdbc.sales.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.shardingjdbc.core.api.algorithm.sharding.ListShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.ShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;

public class SalesTableShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    static public Logger logger = LoggerFactory.getLogger(SalesTableShardingAlgorithm.class);

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        logger.info("availableTargetNames=" + availableTargetNames + "; shardingValues=" + shardingValues);
        Collection<String> res = new ArrayList<String>();
        for (Iterator<ShardingValue> iterator = shardingValues.iterator(); iterator.hasNext();) {
            ListShardingValue<?> shardingValue = (ListShardingValue<?>) iterator.next();
            String fieldName = shardingValue.getColumnName();
            String fieldValue = (shardingValue.getValues() == null || shardingValues.isEmpty()) ? "" : getOne(shardingValue.getValues()).toString();
            if (StringUtils.isNotBlank(fieldValue)) {
                if ("request_no".equals(fieldName)) res.add("pay_order_" + fieldValue.substring(fieldValue.length() - 2));// R000000xx
                else if ("bank_seq".equals(fieldName)) res.add("pay_order_" + fieldValue.substring(1, 3));// Bxx0000
                break;
            }
        }
        logger.info(">>>>>>>>>>>>>>table=" + res);
        return res;
    }

    private Object getOne(Collection<?> objs) {
        if (null == objs) return null;
        for (Iterator<?> iterator = objs.iterator(); iterator.hasNext();) {
            Object object = (Object) iterator.next();
            return object;
        }
        return null;
    }
}
