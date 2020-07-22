package com.srsbsns.adder.math;

public class LeftTermConcurrentGenerator implements Runnable {
    private final Object MUTEX = new Object();
    private int leftTermLimit = 0;
    private int leftTerm = 0;

    public LeftTermConcurrentGenerator(int leftTermLimit) {
        this.leftTermLimit = leftTermLimit;
    }

    public void run() {
        int leftTerm = this.leftTerm;
        while (leftTerm < leftTermLimit) {
            synchronized (MUTEX) {
                leftTerm++;
            }
        }
        this.leftTerm = leftTerm;
    }

    public int getLeftTerm() {
        return leftTerm;
    }
}
