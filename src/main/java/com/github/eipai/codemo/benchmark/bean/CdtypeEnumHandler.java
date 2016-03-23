package com.github.eipai.codemo.benchmark.bean;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class CdtypeEnumHandler extends BaseTypeHandler<CdType> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CdType parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            throw new IllegalArgumentException(CdType.class.getSimpleName() + " cannot be null!");
        } else {
            ps.setString(i, parameter.toString());
        }
    }

    @Override
    public CdType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        return CdType.valueOf(s);
    }

    @Override
    public CdType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        return CdType.valueOf(s);
    }

    @Override
    public CdType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        return CdType.valueOf(s);
    }
}
