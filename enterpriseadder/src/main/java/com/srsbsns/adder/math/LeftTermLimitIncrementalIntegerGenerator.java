package com.srsbsns.adder.math;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LeftTermLimitIncrementalIntegerGenerator implements ITermLimitIncrementalIntegerGenerator<LeftTermBean> {
    private int configuredLeftTerm = 0;
    private final static long TIMEOUT = 5000L;
    private final Object MUTEX = new Object();

    public LeftTermLimitIncrementalIntegerGenerator(int configuredLeftTerm) {
        this.configuredLeftTerm = configuredLeftTerm;
    }

    @Override
    public int getConfiguredTerm() {
        return configuredLeftTerm;
    }

    @Override
    public LeftTermBean generate() throws InterruptedException {
        LeftTermConcurrentGenerator generator = new LeftTermConcurrentGenerator(configuredLeftTerm);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(generator);
        executorService.shutdown();
        executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS);

        return LeftTermBeanBuilderFactory.getInstance().setLeftTerm(generator.getLeftTerm()).build();
    }
}
