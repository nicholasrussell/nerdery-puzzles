package com.srsbsns.adder.math;

public class AdditionResult implements IResult {
    private int leftTerm;
    private int rightTerm;

    private int result;

    public AdditionResult(int leftTerm, int rightTerm) {
        this.leftTerm = leftTerm;
        this.rightTerm = rightTerm;
    }

    @Override
    public int getLeftTerm() {
        return leftTerm;
    }

    @Override
    public int getRightTerm() {
        return rightTerm;
    }

    @Override
    public int getResult() {
        return result;
    }

    @Override
    public void calculateResult(LeftTermBean leftTermBean, RightTermBean rightTermBean) {
        result = leftTermBean.getLeftTerm() + rightTermBean.getRightTerm();
    }
}
