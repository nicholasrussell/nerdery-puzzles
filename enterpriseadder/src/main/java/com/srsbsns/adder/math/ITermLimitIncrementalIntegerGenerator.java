package com.srsbsns.adder.math;

public interface ITermLimitIncrementalIntegerGenerator<T> {
    int getConfiguredTerm();
    T generate() throws InterruptedException;
}
