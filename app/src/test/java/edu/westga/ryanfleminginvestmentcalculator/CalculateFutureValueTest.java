package edu.westga.ryanfleminginvestmentcalculator;

import org.junit.Test;

import dalvik.annotation.TestTargetClass;
import edu.westga.ryanfleminginvestmentcalculator.model.InvestmentCalculator;

import static org.junit.Assert.*;

/**
 * Test case for the InvestmentCalculator Class.
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 *
 * @author Ryan Fleming
 * @version 3/4/2016
 */
public class CalculateFutureValueTest {
    InvestmentCalculator testCalc;

    @Test
    public void shouldReturn0WhenPaymentAndRateAndPeriodAre0() throws Exception {
        this.testCalc = new InvestmentCalculator(0.0, 0.0, 0);
        assertEquals(0.0, this.testCalc.calculateValue(), 2);
    }

    @Test
    public void shouldReturn0WhenOnlyPaymentIs0() throws Exception {
        this.testCalc = new InvestmentCalculator(0.0, 10.0, 5);
        assertEquals(0.0, this.testCalc.calculateValue(), 2);
    }

    @Test
    public void shouldReturn0WhenOnlyPeriodIs0() throws Exception {
        this.testCalc = new InvestmentCalculator(1000.00, 10.0, 0);
        assertEquals(0.0, this.testCalc.calculateValue(), 2);
    }

    @Test
    public void shouldReturnPaymentValueWhenRate0AndPeriod1() throws Exception {
        this.testCalc = new InvestmentCalculator(99.99, 0.0, 1);
        assertEquals(99.99, this.testCalc.calculateValue(), 2);
    }

    @Test
    public void shouldReturn600WhenPayment50Rate0Period12() throws Exception {
        this.testCalc = new InvestmentCalculator(50.00, 0.0, 12);
        assertEquals(600.00, this.testCalc.calculateValue(), 2);
    }

    @Test
    public void shouldReturn630WhenPayment300Rate10Period2() throws Exception {
        this.testCalc = new InvestmentCalculator(300.00, 10.0, 2);
        assertEquals(630.00, this.testCalc.calculateValue(), 2);
    }

    @Test
    public void shouldReturnCorrectValueWhenPaymentSetToNewValue() {
        this.testCalc = new InvestmentCalculator(100, 10.0, 10);
        this.testCalc.setPaymentAmount(50.00);
        assertEquals(796.87, this.testCalc.calculateValue(), 2);
    }

    @Test
    public void shouldReturnCorrectValueWhenRateSetToNewValue() {
        this.testCalc = new InvestmentCalculator(100, 10.0, 10);
        this.testCalc.setRateAmount(20.00);
        assertEquals(2595.87, this.testCalc.calculateValue(), 2);
    }

    @Test
    public void shouldReturnCorrectValueWhenPeriodSetToNewValue() {
        this.testCalc = new InvestmentCalculator(100, 10.0, 10);
        this.testCalc.setPeriod(5);
        assertEquals(610.51, this.testCalc.calculateValue(), 2);
    }
}