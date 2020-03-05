package com.aircode.service;

import com.aircode.domain.GenConfig;

/**
 * @author Zheng Jie
 * @date 2019-01-14
 */
public interface GenConfigService {

    GenConfig find();

    GenConfig update(GenConfig genConfig);
}
