package com.srsbsns.adder.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationPropertiesLoader {
    private Properties properties;

    public ApplicationPropertiesLoader() {
        properties = new Properties();
    }

    public ApplicationProperties load(InputStream stream) throws IOException {
        properties.load(stream);
        ApplicationPropertiesImpl applicationProperties = new ApplicationPropertiesImpl();
        applicationProperties.setProperties(properties);
        return applicationProperties;
    }
}
