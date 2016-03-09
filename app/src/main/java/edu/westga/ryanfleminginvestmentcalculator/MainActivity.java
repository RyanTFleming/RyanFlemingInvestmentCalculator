package edu.westga.ryanfleminginvestmentcalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;

import edu.westga.ryanfleminginvestmentcalculator.model.InvestmentCalculator;

public class MainActivity extends AppCompatActivity {
    private InvestmentCalculator calculator;
    private TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        this.results = (TextView) findViewById(R.id.textViewResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onCalculateBtnClick(View view) {
        Double payment = this.getPaymentValue();
        Double rate = this.getRateValue();
        Integer period = this.getPeriodValue();
        if (payment == null && rate == null && period == null) {
            this.results.setText("Invalid Payment\nInvalid Rate\n" +
                "Invalid Period");
            return;
        } else if (payment == null && rate == null) {
            this.results.setText("Invalid Payment\nInvalid Rate");
            return;
        } else if (payment == null && period == null) {
            this.results.setText("Invalid Payment\nInvalid Period");
            return;
        } else if (period == null && rate == null) {
            this.results.setText("Invalid Rate\nInvalid Period");
            return;
        } else if (payment == null) {
            this.results.setText("Invalid Payment");
            return;
        } else if (rate == null) {
            this.results.setText("Invalid Rate");
            return;
        } else if (period == null) {
            this.results.setText("Invalid Period");
            return;
        }

        this.calculator = new InvestmentCalculator(payment, rate, period);
        Double value = this.calculator.calculateValue();

        String resultString = String.format("$%,.2f", value);

        this.results.setText(resultString);




    }

    private Double getPaymentValue() {
        EditText paymentTextBox = (EditText) this.findViewById(R.id.editTextPayment);
        double payment;
        try {
            payment = Double.parseDouble(paymentTextBox.getText().toString());
        } catch (NumberFormatException nfe) {
            return null;
        }
        return payment;
    }

    private Double getRateValue() {
        EditText rateTextBox = (EditText) this.findViewById(R.id.editTextRate);
        double rate;
        try {
            rate = Double.parseDouble(rateTextBox.getText().toString());
        } catch (NumberFormatException nfe) {
            return null;
        }
        return rate;
    }

    private Integer getPeriodValue() {
        EditText periodTextBox = (EditText) this.findViewById(R.id.editTextPeriod);
        int period;
        try {
            period = Integer.parseInt(periodTextBox.getText().toString());
        } catch (NumberFormatException nfe) {
            return null;
        }
        return period;
    }


}
