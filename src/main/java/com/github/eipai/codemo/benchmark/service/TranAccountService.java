package com.github.eipai.codemo.benchmark.service;

import com.github.eipai.codemo.benchmark.bean.RespCode;
import com.github.eipai.codemo.benchmark.bean.TranMetaData;
import com.github.eipai.codemo.benchmark.bean.TranRequest;
import com.github.eipai.codemo.benchmark.bean.TranResponse;

public interface TranAccountService {

    public void updateTransInfo(TranRequest request);

    public void saveTransInfo(TranRequest request, TranMetaData tranMetaData);

    public void doTrans(TranRequest request, TranResponse response, TranMetaData tranMiddleData);

    public void updateTransInfoRespCode(Long id, RespCode respCode, boolean nullException);
}
