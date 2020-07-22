package com.srsbsns.adder.properties;

import java.util.Properties;

public interface ApplicationProperties {
    void setProperties(Properties properties);
    int getConfiguredLeftTermLimit();
    int getConfiguredRightTermLimit();
}
