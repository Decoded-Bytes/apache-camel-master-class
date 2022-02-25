package com.decodedbytes.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LegacyFileRoute extends RouteBuilder {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void configure() throws Exception {

        from("file:src/data/input?fileName=inputFile.csv")
                .routeId("legacyFileRouteId")
                .split(body().tokenize("\n",1,true))
                .process(exchange -> {
                    String fileData = exchange.getIn().getBody(String.class);
                    logger.info("Read File Data: " + fileData);
                })
                .to("file:src/data/output?fileName=outputFile.csv&fileExist=append")
                .end();
    }
}
