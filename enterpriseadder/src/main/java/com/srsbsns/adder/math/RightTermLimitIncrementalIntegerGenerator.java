package com.srsbsns.adder.math;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RightTermLimitIncrementalIntegerGenerator implements ITermLimitIncrementalIntegerGenerator<RightTermBean> {
    private int configuredRightTerm = 0;
    private final static long TIMEOUT = 5000L;

    public RightTermLimitIncrementalIntegerGenerator(int configuredRightTerm) {
        this.configuredRightTerm = configuredRightTerm;
    }

    @Override
    public int getConfiguredTerm() {
        return configuredRightTerm;
    }

    @Override
    public RightTermBean generate() throws InterruptedException {
        RightTermConcurrentGenerator generator = new RightTermConcurrentGenerator(configuredRightTerm);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(generator);
        executorService.shutdown();
        executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS);

        System.out.println("RIGHT TERM: " + generator.getRightTerm());

        return RightTermBeanBuilderFactory.getInstance().setRightTerm(generator.getRightTerm()).build();
    }
}
