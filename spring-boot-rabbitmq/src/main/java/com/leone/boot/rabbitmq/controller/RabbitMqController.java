package com.leone.boot.rabbitmq.controller;

import com.leone.boot.rabbitmq.config.RabbitMqConstant;
import com.leone.boot.rabbitmq.sender.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author leone
 * @since 2018-05-15
 **/
@Slf4j
@RestController
@RequestMapping("/api/rabbitmq")
public class RabbitMqController {

    @Autowired
    private MessageSender messageSender;

    @GetMapping("/send/{exchange}")
    public String send(@PathVariable("exchange") String exchange,
                       @RequestParam String message,
                       @RequestParam(required = false, defaultValue = "") String routingKey,
                       @RequestHeader Map<String,Object> headers) {
        switch (exchange) {
            case "topic":
                messageSender.sendTopic(RabbitMqConstant.TOPIC_EXCHANGE, routingKey, message);
                break;
            case "fanout":
                messageSender.sendFanout(RabbitMqConstant.FANOUT_EXCHANGE, message);
                break;
            case "direct":
                messageSender.sendDirect(RabbitMqConstant.DIRECT_EXCHANGE, routingKey, message);
                break;
            case "headers":
                messageSender.sendHeaders(RabbitMqConstant.HEADERS_EXCHANGE, headers, message);
                break;
            default:
                messageSender.send(routingKey, message);
                break;
        }
        return "send to " + exchange + ", routingKey: " + routingKey + ", message: " + message;
    }

}