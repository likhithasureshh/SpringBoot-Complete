package com.module_1.HomeWork.impl;

import com.module_1.HomeWork.Frosting;
import com.module_1.HomeWork.Syrup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CakeBaker {
    private final Frosting frosting;
    private final Syrup syrup;
    public void bakeCake()
    {
        frosting.getFrostingType();
        syrup.getSyrupType();
    }

}
