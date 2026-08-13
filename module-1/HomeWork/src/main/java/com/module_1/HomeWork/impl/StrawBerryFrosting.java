package com.module_1.HomeWork.impl;

import com.module_1.HomeWork.Frosting;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "frosting.type",havingValue = "strawBerry")
public class StrawBerryFrosting implements Frosting {
    @Override
    public void getFrostingType() {
        System.out.println("StrawBerryFrosting");
    }
}
