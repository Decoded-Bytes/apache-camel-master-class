package com.decodedbytes.routes;

import com.decodedbytes.beans.InboundNameAddress;
import com.decodedbytes.processor.NameAddressProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class NewRestRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration().component("jetty").host("0.0.0.0").port(8080).bindingMode(RestBindingMode.json).enableCORS(true);

        rest("masterclass")
                .produces("application/json")
                .post("nameAddress").type(InboundNameAddress.class).route().routeId("newRestRouteId")
                .log(LoggingLevel.INFO, String.valueOf(simple("${body}")))
                .to("direct:persistMessage")
                .wireTap("seda:sendToQueue")

                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .transform().simple("Message Processed: ${body}")
                .endRest();



        from("direct:persistMessage")
                .routeId("persistMessageRouteId")
                .to("jpa:"+InboundNameAddress.class.getName());

        from("seda:sendToQueue")
                .routeId("sendToQueueRouteId")
                .to("activemq:queue:nameaddressqueue");

    }
}
