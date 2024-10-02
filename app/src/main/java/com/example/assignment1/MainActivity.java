package com.example.assignment1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Double Loan;
    Double Interest;
    int Years;

    EditText LoanIn;
    EditText InterestIn;
    EditText YearsIn;
    Button Calculate;
    Double ResultDouble;
    TextView Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        LoanIn=findViewById(R.id.loanamount);
        InterestIn=findViewById(R.id.interest);
        YearsIn=findViewById(R.id.period);
        Calculate=findViewById(R.id.calc);
        Result=findViewById(R.id.result);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        Calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(LoanIn.getText().toString())
                        || TextUtils.isEmpty(InterestIn.getText().toString())
                        || TextUtils.isEmpty(YearsIn.getText().toString()) ){
                    
                    Toast.makeText(MainActivity.this, "Empty field found!", Toast.LENGTH_SHORT).show();

                } else if (Double.parseDouble(LoanIn.getText().toString())<=0
                        || Double.parseDouble(InterestIn.getText().toString())<=0
                        || Integer.parseInt(YearsIn.getText().toString())<=0) {

                    Toast.makeText(MainActivity.this, "Entries must be greater than 0!", Toast.LENGTH_SHORT).show();

                } else if(Double.parseDouble(LoanIn.getText().toString())<10000||Double.parseDouble(LoanIn.getText().toString())>10000000){

                    Toast.makeText(MainActivity.this, "Loan amount must be between $10,000 and $10,000,000", Toast.LENGTH_SHORT).show();
                }
                else{

                    Loan=Double.valueOf(LoanIn.getText().toString());
                    Interest=Double.valueOf(InterestIn.getText().toString());
                    Years=Integer.parseInt(YearsIn.getText().toString());
                    ResultDouble = CalculateEMI(Loan,Interest,Years);
                    Result.setText(String.format("%.2f", ResultDouble));

                }
            }
        });

    }

    public double CalculateEMI(Double P,Double interest,int term){

        double res;
        double r =(interest/100)/12;
        double n=term*12;
        res= P * r *((Math.pow(1 + r, n))) / ((Math.pow(1 + r, n)) - 1);
        return  res;
    }

}