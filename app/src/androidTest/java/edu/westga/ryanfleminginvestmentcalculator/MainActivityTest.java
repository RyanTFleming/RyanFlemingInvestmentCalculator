package edu.westga.ryanfleminginvestmentcalculator;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Instrumentation test for the application
 *
 * @author Ryan Fleming
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testInitialResultValue0() {
        MainActivity activity = getActivity();
        TextView resultTextView = (TextView) activity.findViewById(R.id.textViewResult);
        String result = resultTextView.getText().toString();
        assertEquals(result, "0.00");
    }

    public void testInitialPaymentValue0() {
        MainActivity activity = getActivity();
        EditText paymentTextView = (EditText) activity.findViewById(R.id.editTextPayment);
        String payment = paymentTextView.getText().toString();
        assertEquals(payment, "0.00");
    }

    public void testInitialRateValue0() {
        MainActivity activity = getActivity();
        EditText rateTextView = (EditText) activity.findViewById(R.id.editTextRate);
        String rate = rateTextView.getText().toString();
        assertEquals(rate, "0.00");
    }

    public void testInitialPeriodValue0() {
        MainActivity activity = getActivity();
        EditText periodTextView = (EditText) activity.findViewById(R.id.editTextPeriod);
        String period = periodTextView.getText().toString();
        assertEquals(period, "0");
    }
}
