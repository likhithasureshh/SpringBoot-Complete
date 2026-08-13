package com.module_1.HomeWork.impl;

import com.module_1.HomeWork.Syrup;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "syrup.type",havingValue = "strawBerry")
public class StrawBerrySyrup implements Syrup {
    @Override
    public void getSyrupType() {
        System.out.println("StrawBerrySyrup");
    }
}
