package com.packt.camel.chapter4;

import org.apache.camel.Body;
import org.apache.camel.Handler;

public class MyBean {

    @Handler
    public String doMyLogic(@Body String body) {
        return "My Logic got " + body;
    }

}
