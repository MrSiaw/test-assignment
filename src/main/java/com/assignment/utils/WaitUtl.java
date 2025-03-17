package com.assignment.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitUtl {

    private static final Logger logger = LoggerFactory.getLogger(WaitUtl.class);

    public static void sleep(long millSeconds) {
        try {
            Thread.sleep(millSeconds);
        } catch (InterruptedException e) {
            logger.error("issue with sleeping");
        }
    }
}
