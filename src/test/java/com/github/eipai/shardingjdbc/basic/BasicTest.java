package com.github.eipai.shardingjdbc.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

//TODO
import org.apache.commons.dbcp.BasicDataSource;
//import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.InlineShardingStrategyConfiguration;

public class BasicTest {
    static public Logger logger = LoggerFactory.getLogger(BasicTest.class);

    @Test
    public void test() throws Exception {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        BasicDataSource dataSource1 = new BasicDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/ds_0");
        dataSource1.setUsername("root");
        dataSource1.setPassword("root");
        dataSourceMap.put("ds_0", dataSource1);

        // 配置第二个数据源
        BasicDataSource dataSource2 = new BasicDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://localhost:3306/ds_1");
        dataSource2.setUsername("root");
        dataSource2.setPassword("root");
        dataSourceMap.put("ds_1", dataSource2);

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("ds_${0..1}.t_order_${0..1}");
        // 配置分库策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds_${user_id % 2}"));
        // 配置分表策略
        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order_${order_id % 2}"));
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        TableRuleConfiguration orderItemTableRuleConfig = new TableRuleConfiguration();
        orderItemTableRuleConfig.setLogicTable("t_order_item");
        orderItemTableRuleConfig.setActualDataNodes("ds_${0..1}.t_order_item_${0..1}");
        // 配置分库策略
        orderItemTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds_${user_id % 2}"));
        // 配置分表策略
        orderItemTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order_item_${order_id % 2}"));
        shardingRuleConfig.getTableRuleConfigs().add(orderItemTableRuleConfig);

        // 获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap<String, Object>(), new Properties());

        String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.user_id=? AND o.order_id=?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, 10);
            preparedStatement.setInt(2, 1001);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    logger.info("" + rs.getInt(1));
                    logger.info("" + rs.getInt(2));
                }
            }
        }

//        sql = "select * from t_order_item where user_id=21 and order_id=2001 ";
//        try (Connection conn = dataSource.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
//            try (ResultSet rs = preparedStatement.executeQuery()) {
//            }
//        }

    }

}
