package com.leesasianbistro.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigDecimal;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends Activity {

    Button showMsgButton;
    Button newbutton;
    //Button ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ch = (Button) findViewById(R.id.activateCh);

        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Locale locale = new Locale("zh");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);*/

        newbutton = (Button) findViewById(R.id.btNew);

        newbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
            }
        });

        /*sw1 = (Switch) findViewById(R.id.switch1);
        sw1.setTextOff("English");
        sw1.setTextOn("中文");
        sw1.setChecked(false);

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (sw1.isChecked()) {

                    Locale locale = new Locale("zh");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());

                }
            }
        });*/

        final EditText busAmtField = (EditText) findViewById(R.id.bus_amt);
        final EditText ccAmtField = (EditText) findViewById(R.id.cc_amt);
        final EditText cashAmtField = (EditText) findViewById(R.id.cash_amt);

        showMsgButton = (Button) findViewById(R.id.bttnShwMsg);
        showMsgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String busAmt = busAmtField.getText().toString();
                final String ccAmt = ccAmtField.getText().toString();

                if ( (busAmt.length() != 0) && (ccAmt.length() != 0) ) {

                    final BigDecimal busAmtInt = new BigDecimal(busAmt);
                    final BigDecimal ccAmtInt = new BigDecimal(ccAmt);

                    //busAmtField.setText("$" + busAmt);
                    //ccAmtField.setText("$" + ccAmt);
                    //Would like to format text as focus leaves the field but then reformat back to BigDecimal when in focus

                    BigDecimal cashAmt = busAmtInt.subtract(ccAmtInt);
                    cashAmtField.setText("$" + cashAmt.toString(), TextView.BufferType.NORMAL);

                    //Hide keyboard after calculation is finished
                    InputMethodManager imm = (InputMethodManager)getSystemService(
                            INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ccAmtField.getWindowToken(), 0);

                } else{
                    Toast.makeText(getApplicationContext(), "Error yo, Enter some numbers!", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
