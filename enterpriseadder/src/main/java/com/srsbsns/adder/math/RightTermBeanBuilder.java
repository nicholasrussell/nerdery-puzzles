package com.srsbsns.adder.math;

public class RightTermBeanBuilder {
    private Integer rightTerm = null;

    public RightTermBeanBuilder() {

    }

    public RightTermBeanBuilder setRightTerm(int rightTerm) {
        this.rightTerm = rightTerm;
        return this;
    }

    public RightTermBean build() {
        if (rightTerm == null) {
            throw new IllegalStateException("Tried to build a RightTermBean without a supplied right term");
        }

        RightTermBean rightTermBean = new RightTermBean();
        rightTermBean.setRightTerm(rightTerm);

        return rightTermBean;
    }
}
