package com.tyut.service;

import reactor.core.publisher.Flux;

/**
 * @version v1.0
 * @apiNote 接口
 * @author OldGj 2024/12/6
 */
public interface IServiceAssistant {


    Flux<String> chat(String message);
}
