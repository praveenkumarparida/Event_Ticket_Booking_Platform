package com.ey.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class QrCodeGenerator {

    public String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

