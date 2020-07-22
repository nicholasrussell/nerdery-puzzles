package com.srsbsns.adder.math;

public class RightTermBean {
    private int rightTerm;

    public int getRightTerm() {
        return rightTerm;
    }

    public void setRightTerm(int rightTerm) {
        this.rightTerm = rightTerm;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof RightTermBean)) {
            return false;
        }
        RightTermBean otherRightTerm = (RightTermBean) other;
        return getRightTerm() == otherRightTerm.getRightTerm();
    }
}
