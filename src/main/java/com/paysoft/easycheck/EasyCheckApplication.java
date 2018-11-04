package com.paysoft.easycheck;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class EasyCheckApplication extends ResourceConfig {

    public EasyCheckApplication() {
        packages("com.paysoft.easycheck");
        register(JacksonFeature.class);
    }

}
