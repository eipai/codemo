package com.github.eipai.codemo.benchmark.manager;

import org.springframework.stereotype.Repository;

import com.github.eipai.codemo.benchmark.bean.DaoException;
import com.github.eipai.codemo.benchmark.domain.AccountDetail;

@Repository
public class AccountDetailManagerImpl extends AbstractManager<AccountDetail, Long> implements AccountDetailManager {

    @Override
    public AccountDetail get(Long id) {
        return accountDetailDao.get(id);
    }

    @Override
    public int insert(AccountDetail entity) {
        int rows = accountDetailDao.insert(entity);
        if (1 != rows) {
            logger.error("entity insert exception: " + entity.toString());
            throw new DaoException("entity insert exception: " + AccountDetail.class.getSimpleName() + ".id=" + entity.getId());
        }
        return rows;
    }

    @Override
    public int update(AccountDetail entity) {
        throw new UnsupportedOperationException();
    }

}
