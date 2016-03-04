package edu.westga.ryanfleminginvestmentcalculator;

import org.junit.Test;

import edu.westga.ryanfleminginvestmentcalculator.model.InvestmentCalculator;

import static org.junit.Assert.*;

/**
 * Test case for the InvestmentCalculator Classe.
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class CalculateFutureValueTest {
    InvestmentCalculator testCalc;

    @Test
    public void shouldReturn0WhenPaymentAndRateAndPeriodAre0() throws Exception {
        this.testCalc = new InvestmentCalculator(0.0, 0.0, 0);
        assertEquals(0.0, testCalc.getValue(), 2);
    }

    @Test
    public void shouldReturn0WhenOnlyPaymentIs0() throws Exception {
        this.testCalc = new InvestmentCalculator(0.0, 10.0, 5);
        assertEquals(0.0, testCalc.getValue(), 2);
    }


}