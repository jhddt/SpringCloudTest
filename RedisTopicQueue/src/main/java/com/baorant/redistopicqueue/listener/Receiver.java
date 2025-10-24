package com.baorant.redistopicqueue.listener;

import org.slf4j.LoggerFactory;

public class Receiver {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(Receiver.class);

    public void receiveMessage(String message) {
        logger.info("Received <" + message + ">");
    }
}
