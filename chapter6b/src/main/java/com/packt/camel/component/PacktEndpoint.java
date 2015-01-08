package com.packt.camel.component;

import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultEndpoint;

public class PacktEndpoint extends DefaultEndpoint {

    public PacktProducer createProducer() {
        return new PacktProducer(this);
    }

    public PacktConsumer createConsumer(Processor processor) {
        return new PacktConsumer(this, processor);
    }

    public boolean isSingleton() {
        return false;
    }

}
