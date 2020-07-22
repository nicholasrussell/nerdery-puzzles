package com.srsbsns.adder.math;

public class LeftTermBeanBuilder {
    private Integer leftTerm = null;

    public LeftTermBeanBuilder() {

    }

    public LeftTermBeanBuilder setLeftTerm(int leftTerm) {
        this.leftTerm = leftTerm;
        return this;
    }

    public LeftTermBean build() {
        if (leftTerm == null) {
            throw new IllegalStateException("Tried to build a LeftTermBean without a supplied left term");
        }

        LeftTermBean leftTermBean = new LeftTermBean();
        leftTermBean.setLeftTerm(leftTerm);

        return leftTermBean;
    }
}
