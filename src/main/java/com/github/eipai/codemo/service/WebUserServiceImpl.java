package com.github.eipai.codemo.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.eipai.codemo.dao.WebUserDao;
import com.github.eipai.codemo.domain.WebUser;

@Service
public class WebUserServiceImpl implements WebUserService {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private WebUserDao webUserDao;

    @Override
    public WebUser getById(String id) {
        return webUserDao.get(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public void transDemo(String id) {
        WebUser user = getById(id);
        user.setAge(user.getAge() + 1);
        webUserDao.update(user);
        logger.info("------------------------------------");
        user.setAge(user.getAge() + 10);
        user.setRemark("LongRemark LongRemark LongRemark LongRemark LongRemark LongRemark LongRemark LongRemark LongRemark LongRemark");
        webUserDao.update(user);// rollback
    }

}
