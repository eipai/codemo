package com.github.eipai.codemo.benchmark.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.eipai.codemo.benchmark.dao.AccountDetailDao;
import com.github.eipai.codemo.benchmark.dao.SeqGeneratorDao;
import com.github.eipai.codemo.benchmark.dao.TransInfoDao;
import com.github.eipai.codemo.benchmark.dao.UserAccountDao;

public abstract class AbstractManager<E, ID> implements BaseManager<E, ID> {
    protected static final Log logger = LogFactory.getLog(AbstractManager.class);
    @Autowired
    protected SeqGeneratorDao seqGeneratorDao;
    @Autowired
    protected AccountDetailDao accountDetailDao;
    @Autowired
    protected TransInfoDao transInfoDao;
    @Autowired
    protected UserAccountDao userAccountDao;
}
