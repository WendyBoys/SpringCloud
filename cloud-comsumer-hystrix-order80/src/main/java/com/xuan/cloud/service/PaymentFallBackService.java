package com.xuan.cloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements PaymentService {
    @Override
    public String ok(int id) {
        return "fallback--------ok";
    }

    @Override
    public String timeout(int id) {
        return "fallback--------timeout";
    }
}
