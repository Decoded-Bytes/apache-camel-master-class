package com.decodedbytes;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@CamelSpringBootTest
@SpringBootApplication
@UseAdviceWith
class SimpleCamelSpringbootApplicationTests {

	@Autowired
	CamelContext context;

	@EndpointInject("mock:result")
	protected MockEndpoint mockEndpoint;

	@Autowired
	ProducerTemplate producerTemplate;

	@Test
	public void testTimer() throws Exception {

		String expectedBody = "Hello";
		mockEndpoint.expectedBodiesReceived(expectedBody);
		mockEndpoint.expectedMinimumMessageCount(1);

		AdviceWith.adviceWith(context,"simpleTimerId", routeBuilder -> {
			routeBuilder.weaveAddLast().to(mockEndpoint);
				}
		);

		context.start();
		mockEndpoint.assertIsSatisfied();

	}


}
