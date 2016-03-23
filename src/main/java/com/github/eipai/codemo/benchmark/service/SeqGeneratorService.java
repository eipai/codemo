package com.github.eipai.codemo.benchmark.service;

import com.github.eipai.codemo.benchmark.domain.SeqGenerator.SeqGeneratorCode;

public interface SeqGeneratorService {
    long getNext(SeqGeneratorCode code);
}
