package com.github.eipai.codemo.benchmark.manager;

import org.springframework.stereotype.Repository;

import com.github.eipai.codemo.benchmark.bean.DaoException;
import com.github.eipai.codemo.benchmark.bean.RespCode;
import com.github.eipai.codemo.benchmark.domain.SeqGenerator;
import com.github.eipai.codemo.benchmark.domain.SeqGenerator.SeqGeneratorCode;
import com.github.eipai.codemo.benchmark.utils.Global;

@Repository
public class SeqGeneratorManagerImpl extends AbstractManager<SeqGenerator, Integer> implements SeqGeneratorManager {

    @Override
    public SeqGenerator get(Integer id) {
        return seqGeneratorDao.get(id);
    }

    @Override
    public int insert(SeqGenerator entity) {
        int rows = seqGeneratorDao.insert(entity);
        if (1 != rows) {
            logger.error("entity insert exception: " + entity.toString());
            throw new DaoException("entity insert exception: " + SeqGenerator.class.getSimpleName() + ".id=" + entity.getId());
        }
        return rows;
    }

    @Override
    public int update(SeqGenerator entity) {
        int rows = seqGeneratorDao.update(entity);
        if (1 != rows) {
            logger.error("optimistic lock exception: " + entity.toString());
            throw new DaoException(RespCode.OPTIMISTIC_LOCK, "optimistic lock exception: " + SeqGenerator.class.getSimpleName() + ".id=" + entity.getId());
        }
        return rows;
    }

    @Override
    public SeqGenerator getByCode(SeqGeneratorCode code) {
        Global.Assert.notNull(code);
        return seqGeneratorDao.getByCode(code.toString());
    }
}
