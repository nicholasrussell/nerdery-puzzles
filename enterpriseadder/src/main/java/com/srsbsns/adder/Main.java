package com.srsbsns.adder;

import com.srsbsns.adder.io.ResultPrinter;
import com.srsbsns.adder.math.*;
import com.srsbsns.adder.properties.ApplicationProperties;
import com.srsbsns.adder.properties.ApplicationPropertiesLoader;

import java.io.IOException;
import java.io.InputStream;

public final class Main {
    private static final String PROPERTIES_FILE_NAME = "/Adder.properties";

    public static void main(final String[] args) throws Exception {
        ApplicationProperties applicationProperties;
        try {
            InputStream propertiesStream = Main.class.getClass().getResourceAsStream(PROPERTIES_FILE_NAME);
            applicationProperties = new ApplicationPropertiesLoader().load(propertiesStream);
        } catch (IOException loadPropertiesException) {
            throw new IOException("Failed to load application properties", loadPropertiesException);
        }
        IResult result = new AdditionResult(applicationProperties.getConfiguredLeftTermLimit(), applicationProperties.getConfiguredRightTermLimit());
        ITermLimitIncrementalIntegerGenerator<LeftTermBean> leftTermGenerator = new LeftTermLimitIncrementalIntegerGenerator(applicationProperties.getConfiguredLeftTermLimit());
        ITermLimitIncrementalIntegerGenerator<RightTermBean> rightTermGenerator = new RightTermLimitIncrementalIntegerGenerator(applicationProperties.getConfiguredRightTermLimit());
        LeftTermBean leftTerm = leftTermGenerator.generate();
        RightTermBean rightTerm = rightTermGenerator.generate();
        result.calculateResult(leftTerm, rightTerm);
        new ResultPrinter(result).printResults();
    }
}
