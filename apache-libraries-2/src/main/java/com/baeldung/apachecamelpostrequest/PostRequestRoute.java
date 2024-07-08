package com.baeldung.apachecamelpostrequest;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class PostRequestRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:start").process(exchange -> exchange.getIn()
                .setBody(new PostPojo(1, "Java 21", "Virtual Thread")))
            .marshal()
            .json(JsonLibrary.Jackson)
            .setHeader(Exchange.HTTP_METHOD, constant("POST"))
            .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
            .to("https://jsonplaceholder.typicode.com/posts")
            .process(exchange -> log.info("The HTTP response code is: {}", exchange.getIn()
                .getHeader(Exchange.HTTP_RESPONSE_CODE)))
            .process(exchange -> log.info("The response body is: {}", exchange.getIn()
                .getBody(String.class)))
            .to("mock:result");

        from("direct:post").process(exchange -> exchange.getIn()
                .setBody("{\"title\":\"Java 21\",\"body\":\"Virtual Thread\",\"userId\":\"1\"}"))
            .setHeader(Exchange.HTTP_METHOD, constant("POST"))
            .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
            .to("https://jsonplaceholder.typicode.com/posts")
            .to("mock:post");
    }

}
