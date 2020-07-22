package com.srsbsns.adder;

import com.srsbsns.adder.math.AdditionResult;
import com.srsbsns.adder.math.IResult;
import com.srsbsns.adder.math.LeftTermBean;
import com.srsbsns.adder.math.RightTermBean;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test to ensure that the universe has not changed
 */
public class AdditionTest {
    @Test
    public void additionTest() {
        assertEquals("Ensure the universe has not changed", 4, 2 + 2);
    }

    @Test
    public void adderTest() {
        IResult result = new AdditionResult(2, 2);
        LeftTermBean leftTerm = new LeftTermBean();
        leftTerm.setLeftTerm(2);
        RightTermBean rightTerm = new RightTermBean();
        rightTerm.setRightTerm(2);
        result.calculateResult(leftTerm, rightTerm);
        assertEquals("2 + 2 = 4", 4, result.getResult());
    }
}
