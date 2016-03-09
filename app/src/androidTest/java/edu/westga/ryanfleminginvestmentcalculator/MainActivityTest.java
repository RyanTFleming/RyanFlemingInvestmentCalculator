package edu.westga.ryanfleminginvestmentcalculator;

import android.app.Activity;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.KeyEvent;
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
        assertEquals("$0.00", result);
    }

    public void testInitialPaymentValue0() {
        MainActivity activity = getActivity();
        EditText paymentTextView = (EditText) activity.findViewById(R.id.editTextPayment);
        String payment = paymentTextView.getText().toString();
        assertEquals("0.00", payment);
    }

    public void testInitialRateValue0() {
        MainActivity activity = getActivity();
        EditText rateTextView = (EditText) activity.findViewById(R.id.editTextRate);
        String rate = rateTextView.getText().toString();
        assertEquals("0.00", rate);
    }

    public void testInitialPeriodValue0() {
        MainActivity activity = getActivity();
        EditText periodTextView = (EditText) activity.findViewById(R.id.editTextPeriod);
        String period = periodTextView.getText().toString();
        assertEquals("0", period);
    }

    public void testResultValueShouldBeSetWhenValidValuesAndButtonClick() throws Exception {
        final MainActivity activity = getActivity();
        final EditText paymentEdit = (EditText) activity.findViewById(R.id.editTextPayment);
        final EditText rateEditText = (EditText) activity.findViewById(R.id.editTextRate);
        final EditText periodEditText = (EditText) activity.findViewById(R.id.editTextPeriod);

        this.getFocus(paymentEdit);
        this.clearEditText();
        getInstrumentation().sendStringSync("100.0");

        this.getFocus(rateEditText);
        this.clearEditText();
        getInstrumentation().sendStringSync("10.0");

        this.getFocus(periodEditText);
        getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DPAD_RIGHT);
        getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DEL);
        getInstrumentation().sendStringSync("10");

        this.clickButton(activity);

        assertEquals("$1,593.74", getResultString(activity));

    }

    public void testResultsWithInvalidPayment() {
        final MainActivity activity = getActivity();
        final EditText paymentEdit = (EditText) activity.findViewById(R.id.editTextPayment);
        this.getFocus(paymentEdit);
        this.clearEditText();
        this.clickButton(activity);
        assertEquals("Invalid Payment", getResultString(activity));
    }

    public void testResultsWithInvalidRate() {
        final MainActivity activity = getActivity();
        final EditText rateEdit = (EditText) activity.findViewById(R.id.editTextRate);
        this.getFocus(rateEdit);
        this.clearEditText();
        this.clickButton(activity);
        assertEquals("Invalid Rate", getResultString(activity));
    }

    public void testResultsWithInvalidPeriod() {
        final MainActivity activity = getActivity();
        final EditText periodEdit = (EditText) activity.findViewById(R.id.editTextPeriod);
        this.getFocus(periodEdit);
        getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DPAD_RIGHT);
        getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DEL);
        this.clickButton(activity);
        assertEquals("Invalid Period", getResultString(activity));
    }

    public void testResultsWithInvalidPaymentAndRate() {
        final MainActivity activity = getActivity();
        final EditText paymentEdit = (EditText) activity.findViewById(R.id.editTextPayment);
        final EditText rateEdit = (EditText) activity.findViewById(R.id.editTextRate);
        this.getFocus(paymentEdit);
        this.clearEditText();
        this.getFocus(rateEdit);
        this.clearEditText();
        this.clickButton(activity);
        assertEquals("Invalid Payment\nInvalid Rate", getResultString(activity));
    }

    public void testResultsWithInvalidPaymentAndPeriod() {
        final MainActivity activity = getActivity();
        final EditText paymentEdit = (EditText) activity.findViewById(R.id.editTextPayment);
        final EditText periodEdit = (EditText) activity.findViewById(R.id.editTextPeriod);
        this.getFocus(paymentEdit);
        this.clearEditText();
        this.getFocus(periodEdit);
        getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DPAD_RIGHT);
        getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DEL);
        this.clickButton(activity);
        assertEquals("Invalid Payment\nInvalid Period", getResultString(activity));
    }

    public void testResultsWithInvalidRateAndPeriod() {
        final MainActivity activity = getActivity();
        final EditText rateEdit = (EditText) activity.findViewById(R.id.editTextRate);
        final EditText periodEdit = (EditText) activity.findViewById(R.id.editTextPeriod);
        this.getFocus(rateEdit);
        this.clearEditText();
        this.getFocus(periodEdit);
        getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DPAD_RIGHT);
        getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DEL);
        this.clickButton(activity);
        assertEquals("Invalid Rate\nInvalid Period", getResultString(activity));
    }

    public void testResultsWithAllInvalidInputs() {
        final MainActivity activity = getActivity();
        final EditText paymentEdit = (EditText) activity.findViewById(R.id.editTextPayment);
        final EditText rateEdit = (EditText) activity.findViewById(R.id.editTextRate);
        final EditText periodEdit = (EditText) activity.findViewById(R.id.editTextPeriod);
        this.getFocus(paymentEdit);
        this.clearEditText();
        this.getFocus(rateEdit);
        this.clearEditText();
        this.getFocus(periodEdit);
        getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DPAD_RIGHT);
        getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DEL);
        this.clickButton(activity);
        assertEquals("Invalid Payment\nInvalid Rate\nInvalid Period", getResultString(activity));
    }

    /* *********************** Private Methods ******************** */
    private void clearEditText() {
        for (int x = 0; x < 4; x++) {
            getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DPAD_RIGHT);
            getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DEL);
        }
    }

    private void getFocus(EditText editText) {
        final EditText focusedEditText = editText;
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                focusedEditText.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();
        SystemClock.sleep(500);
    }

    private String getResultString(Activity activity) {
        TextView resultTextView = (TextView) activity.findViewById(R.id.textViewResult);
        return resultTextView.getText().toString();
    }

    private void clickButton(Activity activity) {
        Button button = (Button) activity.findViewById(R.id.btnCalculate);
        TouchUtils.clickView(this, button);
    }
}
