package com.github.eipai.codemo.benchmark.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.github.eipai.codemo.benchmark.bean.DaoException;
import com.github.eipai.codemo.benchmark.domain.SeqGenerator;
import com.github.eipai.codemo.benchmark.domain.SeqGenerator.SeqGeneratorCode;
import com.github.eipai.codemo.benchmark.manager.SeqGeneratorManager;
import com.github.eipai.codemo.benchmark.utils.Global;

@Service
@Scope(value = "singleton")
public class SeqGeneratorServiceImpl implements SeqGeneratorService {
    //private final Log logger = LogFactory.getLog(getClass());
    private static Map<SeqGeneratorCode, SeqGenerator> SEQ_GENERATORS = new HashMap<>();
    private static Map<SeqGeneratorCode, Object> LOCKS = new HashMap<>();

    @Autowired
    protected SeqGeneratorManager seqGeneratorManager;

    @Override
    public long getNext(SeqGeneratorCode code) {
        Global.Assert.notNull(code, "SeqGenerator code cannot be empty.");
        if (null == LOCKS.get(code)) addLock(code);
        SeqGenerator seqGenerator = SEQ_GENERATORS.get(code);
        synchronized (LOCKS.get(code)) {
            if (null == seqGenerator) refresh(code);
            seqGenerator = SEQ_GENERATORS.get(code);
            if (!seqGenerator.checkValue()) refresh(code);
            seqGenerator = SEQ_GENERATORS.get(code);
            return seqGenerator.generateNextValue();
        }
    }

    synchronized private static void addLock(SeqGeneratorCode code) {
        if (null != LOCKS.get(code)) return;
        LOCKS.put(code, new Object());
    }

    private void refresh(SeqGeneratorCode code) {
        SeqGenerator seqGenerator = seqGeneratorManager.getByCode(code);
        Global.Assert.notNull(seqGenerator, "SeqGenerator not found. code=" + code);

        SEQ_GENERATORS.put(code, seqGenerator);
        boolean success = seqGenerator.updateNextValue();
        if (!success) {
            throw new DaoException("refresh SeqGenerator value failed. code=" + code);
        }
        seqGeneratorManager.update(seqGenerator);
        seqGenerator.updateLocalValue();
    }

}
