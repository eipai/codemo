package com.github.eipai.codemo.benchmark.bean;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class SimpleEnumHandler<E extends Enum<?>> extends BaseTypeHandler<Object>  {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }}
