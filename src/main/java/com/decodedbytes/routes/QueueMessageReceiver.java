package com.decodedbytes.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class QueueMessageReceiver extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("activemq:queue:nameaddressqueue")
                .log(LoggingLevel.INFO, ">>>>>>>>>>> Received Queue Message: ${body}");
    }
}
