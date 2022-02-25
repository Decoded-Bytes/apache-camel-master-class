package com.decodedbytes.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleTimer extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:foo?period=1000")
                .id("simpleTimerId")
                .setBody(constant("Hello"))
                .log(LoggingLevel.INFO, "${body}");
    }
}
