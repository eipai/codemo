package com.github.eipai.codemo.benchmark.service;

import com.github.eipai.codemo.benchmark.bean.TranRequest;
import com.github.eipai.codemo.benchmark.bean.TranResponse;

public interface TranApiService {
    TranResponse tran(TranRequest request);
}
