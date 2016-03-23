package com.github.eipai.codemo.benchmark.dao;

import org.apache.ibatis.annotations.Param;

public interface BaseDao<E, ID> {

    E get(@Param("id") ID id);

    int insert(@Param("entity") E entity);

    int update(@Param("entity") E entity);

}
