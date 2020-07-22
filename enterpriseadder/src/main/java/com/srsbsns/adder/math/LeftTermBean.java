package com.srsbsns.adder.math;

public class LeftTermBean {
    private int leftTerm;

    public int getLeftTerm() {
        return leftTerm;
    }

    public void setLeftTerm(int leftTerm) {
        this.leftTerm = leftTerm;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof LeftTermBean)) {
            return false;
        }
        LeftTermBean otherLeftTerm = (LeftTermBean) other;
        return getLeftTerm() == otherLeftTerm.getLeftTerm();
    }
}
