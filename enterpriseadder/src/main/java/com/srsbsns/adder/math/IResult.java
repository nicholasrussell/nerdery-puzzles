package com.srsbsns.adder.math;

public interface IResult {
    int getLeftTerm();
    int getRightTerm();
    int getResult();
    void calculateResult(LeftTermBean leftTermBean, RightTermBean rightTermBean);
}
