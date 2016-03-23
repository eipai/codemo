package com.github.eipai.codemo.benchmark.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.github.eipai.codemo.benchmark.manager.AccountDetailManager;
import com.github.eipai.codemo.benchmark.manager.TransInfoManager;
import com.github.eipai.codemo.benchmark.manager.UserAccountManager;

public abstract class AbstractService implements BaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    private static Random RANDOM = new Random();

    @Autowired
    protected AccountDetailManager accountDetailManager;
    @Autowired
    protected TransInfoManager transInfoManager;
    @Autowired
    protected UserAccountManager userAccountManager;

    @Autowired
    protected SeqGeneratorService seqGeneratorService;

    @Autowired
    protected TranApiService tranApiService;

    @Value("#{config_properties['app.name']}")
    private String APP_NAME;
    @Value("#{config_properties['benchmark.tran.default.scale']}")
    private int TRAN_DEFAULT_SCALE;
    @Value("#{config_properties['benchmark.tran.default.amount.mean']}")
    private int TRAN_DEFAULT_AMOUNT_MEAN;
    @Value("#{config_properties['benchmark.tran.default.amount.ratio']}")
    private int TRAN_DEFAULT_AMOUNT_RATIO;
    @Value("#{config_properties['benchmark.tran.default.size.mean']}")
    private int TRAN_DEFAULT_SIZE_MEAN;
    @Value("#{config_properties['benchmark.tran.account.id.min']}")
    private long TRAN_ACCOUNT_ID_MIN;
    @Value("#{config_properties['benchmark.tran.account.id.max']}")
    private long TRAN_ACCOUNT_ID_MAX;
    @Value("#{config_properties['benchmark.tran.account.id.ratio']}")
    private long TRAN_ACCOUNT_ID_RATIO;

    protected static BigDecimal TRAN_AMOUNT_MEAN;
    protected static BigDecimal TRAN_AMOUNT_RATIO;

    protected BigDecimal getRandomTranAmount() {
        BigDecimal diff = new BigDecimal(RANDOM.nextGaussian()).multiply(TRAN_AMOUNT_RATIO);
        return TRAN_AMOUNT_MEAN.add(diff).setScale(TRAN_DEFAULT_SCALE, BigDecimal.ROUND_CEILING);
    }

    protected int getTranSize() {
        int diff = (int) RANDOM.nextGaussian();
        return TRAN_DEFAULT_SIZE_MEAN + diff;
    }

    protected long getRandomPayer() {
        long diff = (long) (Math.abs(RANDOM.nextGaussian()) * TRAN_ACCOUNT_ID_RATIO);
        return TRAN_ACCOUNT_ID_MIN + diff;
    }

    protected long getRandomReceiver() {
        long diff = (long) (RANDOM.nextDouble() * (TRAN_ACCOUNT_ID_MAX - TRAN_ACCOUNT_ID_MIN));
        return TRAN_ACCOUNT_ID_MIN + diff;
    }

    protected List<BigDecimal> allotAmounts(BigDecimal amount, int tranSize) {
        List<BigDecimal> amounts = new ArrayList<BigDecimal>(tranSize);
        if (0 >= tranSize) return amounts;
        BigDecimal sum = new BigDecimal("0");
        BigDecimal average = amount.divide(new BigDecimal(tranSize), TRAN_DEFAULT_SCALE, BigDecimal.ROUND_CEILING);
        for (int i = 0; i < tranSize; i++) {
            if (sum.compareTo(amount) >= 0) {
                amounts.add(new BigDecimal("0"));
                continue;
            }
            if (i == tranSize - 1) {
                amounts.add(amount.subtract(sum));
                break;
            }
            amounts.add(average);
            sum = sum.add(average);
        }
        return amounts;
    }

    @PostConstruct
    public void init() {
        TRAN_AMOUNT_MEAN = new BigDecimal(TRAN_DEFAULT_AMOUNT_MEAN);
        TRAN_AMOUNT_RATIO = new BigDecimal(TRAN_DEFAULT_AMOUNT_RATIO);
    }
}
