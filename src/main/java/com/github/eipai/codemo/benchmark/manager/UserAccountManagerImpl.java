package com.github.eipai.codemo.benchmark.manager;

import org.springframework.stereotype.Repository;

import com.github.eipai.codemo.benchmark.bean.DaoException;
import com.github.eipai.codemo.benchmark.bean.RespCode;
import com.github.eipai.codemo.benchmark.domain.UserAccount;

@Repository
public class UserAccountManagerImpl extends AbstractManager<UserAccount, Long> implements UserAccountManager {

    @Override
    public UserAccount get(Long id) {
        return userAccountDao.get(id);
    }

    @Override
    public int insert(UserAccount entity) {
        int rows = userAccountDao.insert(entity);
        if (1 != rows) {
            logger.error("entity insert exception: " + entity.toString());
            throw new DaoException("entity insert exception: " + UserAccount.class.getSimpleName() + ".id=" + entity.getId());
        }
        return rows;
    }

    @Override
    public int update(UserAccount entity) {
        int rows = userAccountDao.update(entity);
        if (1 != rows) {
            logger.error("optimistic lock exception: " + entity.toString());
            throw new DaoException(RespCode.OPTIMISTIC_LOCK, "optimistic lock exception: " + UserAccount.class.getSimpleName() + ".id=" + entity.getId());
        }
        return rows;
    }

}
