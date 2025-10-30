package com.baorant.redistopicqueue.listener;

import org.slf4j.LoggerFactory;

/**
 * 消息接收者类
 * 用于接收和处理来自Redis主题队列的消息
 */
public class Receiver {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(Receiver.class);

    /**
     * 接收消息并记录到日志中
     * @param message 接收到的消息内容
     */
    public void receiveMessage(String message) {
        logger.info("Received <" + message + ">");
    }
}