package edu.westga.ryanfleminginvestmentcalculator.model;

/**
 * Model for the Investment calculator. Calculates the future value of an
 * annuity
 *
 * Created by Ryan on 3/4/2016.
 */
public class InvestmentCalculator {

    final private double paymentAmount;
    final private double theRate;
    final private int paymentPeriod;

    /**
     * Constructor for the Investement calculator. Accepts a payment amount,
     * a rate, and a number of periods for the calculation.
     * @param payment - the amount of each payment
     * @param rate - the percentage rate for each period
     * @param period - the number of periods the payment will be made
     */
    public InvestmentCalculator(double payment, double rate, int period) {
        this.paymentAmount = payment;
        this.theRate = rate;
        this.paymentPeriod = period;
    }

    public double getValue() {
        return 0.0;
    }
}
