package com.github.eipai.codemo.benchmark.manager;

public interface BaseManager<E, ID> {

    E get(ID id);

    int insert(E entity);

    int update(E entity);

}
