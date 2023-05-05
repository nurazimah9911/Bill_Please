package sg.edu.rp.c346.id22037444.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    EditText amountInput;
    EditText paxInput;
    RadioGroup rgPayment;
    Button splitBtn;
    Button resetBtn;
    ToggleButton GSTbtn;
    ToggleButton SVSbtn;
    TextView amountDisplay;
    TextView paxDisplay;
    TextView totalBill;
    TextView eachPay;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountInput = findViewById(R.id.editTextAmount);
        paxInput = findViewById(R.id.editTextPax);
        splitBtn = findViewById(R.id.buttonSplit);
        resetBtn = findViewById(R.id.buttonReset);
        rgPayment = findViewById(R.id.radioGroupPayment);
        GSTbtn = findViewById(R.id.toggleButtonGST);
        SVSbtn = findViewById(R.id.toggleButtonSVS);
        amountDisplay = findViewById(R.id.textViewAmount);
        paxDisplay = findViewById(R.id.textViewPax);
        totalBill = findViewById(R.id.textViewTotalAmount);
        eachPay = findViewById(R.id.textViewEachPay);



        splitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double newAmt = 0;
                if (amountInput.getText().toString().trim().length() != 0 && paxInput.getText().toString().trim().length() != 0) {
                    double ogAmt = Double.parseDouble(amountDisplay.getText().toString());
                    newAmt = 0.0;
                    if (!SVSbtn.isChecked() && !GSTbtn.isChecked()) {
                        newAmt = ogAmt;
                    } else if (SVSbtn.isChecked() && !GSTbtn.isChecked()) {
                        newAmt = ogAmt * 1.1;
                    } else if (!SVSbtn.isChecked() && !GSTbtn.isChecked()) {
                        newAmt = ogAmt * 1.07;
                    } else {
                        newAmt = ogAmt * 1.17;
                    }
                }
                if (amountDisplay.getText().toString().trim().length() != 0){
                    newAmt *= 1 - Double.parseDouble(amountDisplay.getText().toString()) / 100;
                }

                String mode = " in cash";
                if (rgPayment.getCheckedRadioButtonId() == R.id.radioButtonPayNow) {
                    mode = " via PayNow to 912345678";
                }

                totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                int numPerson = Integer.parseInt(paxDisplay.getText().toString());
                if (numPerson != 1) {
                    eachPay.setText("Each pays: $" + String.format("%.2f", newAmt / numPerson) + mode);
                } else {
                    eachPay.setText("Each Pays: $" + newAmt + mode);
                }
            }
        });
    }


}