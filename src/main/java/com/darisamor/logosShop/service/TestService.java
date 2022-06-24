package com.darisamor.logosShop.service;

import com.darisamor.logosShop.dto.TestDTO;
import com.darisamor.logosShop.service.impl.BaseService;

public interface TestService extends BaseService<TestDTO> {
    TestDTO findByName(String name);
}