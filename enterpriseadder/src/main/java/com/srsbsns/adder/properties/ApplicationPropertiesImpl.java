package com.srsbsns.adder.properties;

import java.util.Properties;

public class ApplicationPropertiesImpl implements ApplicationProperties {
    private static final String LEFT_TERM_CONFIGURATION_KEY = "leftTermLimit";
    private static final String RIGHT_TERM_CONFIGURATION_KEY = "rightTermLimit";
    private static final String DEFAULT_VALUE = "0";

    private Properties properties;

    protected ApplicationPropertiesImpl() {

    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public int getConfiguredLeftTermLimit() {
        String leftTerm = properties.getProperty(LEFT_TERM_CONFIGURATION_KEY, DEFAULT_VALUE);
        return Integer.valueOf(leftTerm);
    }

    @Override
    public int getConfiguredRightTermLimit() {
        String rightTerm = properties.getProperty(RIGHT_TERM_CONFIGURATION_KEY, DEFAULT_VALUE);
        return Integer.valueOf(rightTerm);
    }
}
