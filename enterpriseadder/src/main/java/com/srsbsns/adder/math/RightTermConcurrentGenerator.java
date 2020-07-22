package com.srsbsns.adder.math;

public class RightTermConcurrentGenerator implements Runnable {
    private final Object MUTEX = new Object();
    private int rightTermLimit = 0;
    private int rightTerm = 0;

    public RightTermConcurrentGenerator(int rightTermLimit) {
        this.rightTermLimit = rightTermLimit;
    }

    public void run() {
        int rightTerm = this.rightTerm;
        while (rightTerm <= rightTermLimit) {
            synchronized (MUTEX) {
                rightTerm++;
            }
        }
        this.rightTerm = rightTerm;
    }

    public int getRightTerm() {
        return rightTerm;
    }
}
