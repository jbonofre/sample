package com.packt.camel.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

import java.io.IOException;

public class RouteTest extends CamelBlueprintTestSupport {

    @Override
    protected String getBlueprintDescriptor() {
        return "OSGI-INF/blueprint/route.xml";
    }

    @Override
    public String isMockEndpointsAndSkip() {
        return "((direct:error)|(direct:france)|(direct:usa)|(direct:other))";
    }

    @Test
    public void testRoutingFrance() throws Exception {
        String message = "<company><country>France</country></company>";

        // define the expectations on the direct:france mocked endpoint
        MockEndpoint franceEndpoint = getMockEndpoint("mock:direct:france");
        franceEndpoint.expectedMessageCount(1);

        // define the expectations on the direct:usa mocked endpoint
        MockEndpoint usaEndpoint = getMockEndpoint("mock:direct:usa");
        usaEndpoint.expectedMessageCount(0);

        // define the expectations on the direct:error mocked endpoint
        MockEndpoint errorEndpoint = getMockEndpoint("mock:direct:error");
        errorEndpoint.expectedMessageCount(0);

        // define the expectations on the direct:other mocked endpoint
        MockEndpoint otherEndpoint = getMockEndpoint("mock:direct:other");
        otherEndpoint.expectedMessageCount(0);

        // sending the message in the direct:input mocked endpoint
        template.sendBody("direct:input", message);

        // validate the expectations
        assertMockEndpointsSatisfied();
    }

    @Test
    public void testRoutingUsa() throws Exception {
        String message = "<company><country>USA</country></company>";

        // define the expectations on the direct:france mocked endpoint
        MockEndpoint franceEndpoint = getMockEndpoint("mock:direct:france");
        franceEndpoint.expectedMessageCount(0);

        // define the expectations on the direct:usa mocked endpoint
        MockEndpoint usaEndpoint = getMockEndpoint("mock:direct:usa");
        usaEndpoint.expectedMessageCount(1);

        // define the expectations on the direct:error mocked endpoint
        MockEndpoint errorEndpoint = getMockEndpoint("mock:direct:error");
        errorEndpoint.expectedMessageCount(0);

        // define the expectations on the direct:other mocked endpoint
        MockEndpoint otherEndpoint = getMockEndpoint("mock:direct:other");
        otherEndpoint.expectedMessageCount(0);

        // sending the message in the direct:input mocked endpoint
        template.sendBody("direct:input", message);

        // validate the expectations
        assertMockEndpointsSatisfied();
    }

    @Test
    public void testRoutingOther() throws Exception {
        String message = "<company><country>Spain</country></company>";

        // define the expectations on the direct:france mocked endpoint
        MockEndpoint franceEndpoint = getMockEndpoint("mock:direct:france");
        franceEndpoint.expectedMessageCount(0);

        // define the expectations on the direct:usa mocked endpoint
        MockEndpoint usaEndpoint = getMockEndpoint("mock:direct:usa");
        usaEndpoint.expectedMessageCount(0);

        // define the expectations on the direct:error mocked endpoint
        MockEndpoint errorEndpoint = getMockEndpoint("mock:direct:error");
        errorEndpoint.expectedMessageCount(0);

        // define the expectations on the direct:other mocked endpoint
        MockEndpoint otherEndpoint = getMockEndpoint("mock:direct:other");
        otherEndpoint.expectedMessageCount(1);

        // sending the message in the direct:input mocked endpoint
        template.sendBody("direct:input", message);

        // validate the expectations
        assertMockEndpointsSatisfied();
    }

    @Test
    public void testError() throws Exception {
        String message = "<company><country>France</country></company>";

        // fake an error on the direct:france mocked endpoint
        MockEndpoint franceEndpoint = getMockEndpoint("mock:direct:france");
        franceEndpoint.whenAnyExchangeReceived(new Processor() {
            public void process(Exchange exchange) throws Exception {
                throw new IOException("Simulated error");
            }
        });

        // define the expectations on the direct:usa mocked endpoint
        MockEndpoint usaEndpoint = getMockEndpoint("mock:direct:usa");
        usaEndpoint.expectedMessageCount(0);

        // define the expectations on the direct:error mocked endpoint
        MockEndpoint errorEndpoint = getMockEndpoint("mock:direct:error");
        errorEndpoint.expectedMessageCount(1);

        // define the expectations on the direct:other mocked endpoint
        MockEndpoint otherEndpoint = getMockEndpoint("mock:direct:other");
        otherEndpoint.expectedMessageCount(0);

        // sending the message in the direct:input mocked endpoint
        template.sendBody("direct:input", message);

        // validate the expectations
        assertMockEndpointsSatisfied();
    }

}
