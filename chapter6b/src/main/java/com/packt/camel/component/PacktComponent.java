package com.packt.camel.component;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

import java.util.Map;

public class PacktComponent extends DefaultComponent {

    public PacktComponent() { }

    public PacktComponent(CamelContext camelContext) {
        super(camelContext);
    }

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        PacktEndpoint packtEndpoint = new PacktEndpoint();
        return packtEndpoint;
    }

}
