package com.github.eipai.shardingjdbc.sales.algorithm;

import java.util.Collection;

import io.shardingjdbc.core.api.algorithm.sharding.ShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;

public class SalesTableShardingAlgorithm implements ComplexKeysShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        System.out.println("222222222222222222222");
        return null;
    }

}
