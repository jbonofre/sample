package com.packt.camel.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.camel.util.KeyValueHolder;
import org.junit.Test;

import java.util.Dictionary;
import java.util.Map;

public class RouteTest extends CamelBlueprintTestSupport {

    @Override
    protected String getBlueprintDescriptor() {
        return "OSGI-INF/blueprint/route.xml";
    }

    @Override
    public String isMockEndpointsAndSkip() {
        return "direct:output";
    }

    @Override
    public void addServicesOnStartup(Map<String, KeyValueHolder<Object, Dictionary>> services) {
        KeyValueHolder serviceHolder = new KeyValueHolder(new Processor() {
           public void process(Exchange exchange) throws Exception {
              exchange.getIn().setBody("DONE", String.class);
           }
        }, null);
        services.put(Processor.class.getName(), serviceHolder);
    }

    @Test
    public void testRoutingFrance() throws Exception {
        String message = "BEGIN";

        MockEndpoint franceEndpoint = getMockEndpoint("mock:direct:output");
        franceEndpoint.expectedMessageCount(1);
        franceEndpoint.expectedBodiesReceived("DONE");

        template.sendBody("direct:input", message);

        assertMockEndpointsSatisfied();
    }

}
