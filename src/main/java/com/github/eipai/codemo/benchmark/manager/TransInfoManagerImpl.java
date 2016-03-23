package com.github.eipai.codemo.benchmark.manager;

import org.springframework.stereotype.Repository;

import com.github.eipai.codemo.benchmark.bean.DaoException;
import com.github.eipai.codemo.benchmark.bean.RespCode;
import com.github.eipai.codemo.benchmark.domain.TransInfo;

@Repository
public class TransInfoManagerImpl extends AbstractManager<TransInfo, Long> implements TransInfoManager {

    @Override
    public TransInfo get(Long id) {
        return transInfoDao.get(id);
    }

    @Override
    public int insert(TransInfo entity) {
        int rows = transInfoDao.insert(entity);
        if (1 != rows) {
            logger.error("entity insert exception: " + entity.toString());
            throw new DaoException("entity insert exception: " + TransInfo.class.getSimpleName() + ".id=" + entity.getId());
        }
        return rows;
    }

    @Override
    public int update(TransInfo entity) {
        int rows = transInfoDao.update(entity);
        if (1 != rows) {
            logger.error("optimistic lock exception: " + entity.toString());
            throw new DaoException(RespCode.OPTIMISTIC_LOCK, "optimistic lock exception: " + TransInfo.class.getSimpleName() + ".id=" + entity.getId());
        }
        return rows;
    }

}
