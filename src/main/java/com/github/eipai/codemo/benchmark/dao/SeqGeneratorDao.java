package com.github.eipai.codemo.benchmark.dao;

import org.apache.ibatis.annotations.Param;

import com.github.eipai.codemo.benchmark.domain.SeqGenerator;

public interface SeqGeneratorDao extends BaseDao<SeqGenerator, Integer> {

    SeqGenerator getByCode(@Param("code") String code);

}
