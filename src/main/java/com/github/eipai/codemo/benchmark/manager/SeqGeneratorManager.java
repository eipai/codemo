package com.github.eipai.codemo.benchmark.manager;

import com.github.eipai.codemo.benchmark.domain.SeqGenerator;
import com.github.eipai.codemo.benchmark.domain.SeqGenerator.SeqGeneratorCode;

public interface SeqGeneratorManager extends BaseManager<SeqGenerator, Integer> {

    SeqGenerator getByCode(SeqGeneratorCode code);

}
