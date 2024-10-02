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
    int Term;

    EditText LoanIn;
    EditText InterestIn;
    EditText TermIn;
    Button Calculate;
    Double ResultDouble;
    TextView Result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Initializing IDs
        LoanIn=findViewById(R.id.loanamount);
        InterestIn=findViewById(R.id.interest);
        TermIn =findViewById(R.id.term);
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
                //Checking whether any fields are empty
                if(TextUtils.isEmpty(LoanIn.getText().toString())
                        || TextUtils.isEmpty(InterestIn.getText().toString())
                        || TextUtils.isEmpty(TermIn.getText().toString())){
                    
                    Toast.makeText(MainActivity.this, "Empty field found!", Toast.LENGTH_SHORT).show();

                }
                //Checking if any input is zero
                else if (Double.parseDouble(LoanIn.getText().toString())<=0
                        || Double.parseDouble(InterestIn.getText().toString())<=0
                        || Integer.parseInt(TermIn.getText().toString())<=0) {

                    Toast.makeText(MainActivity.this, "Entries must be greater than 0!", Toast.LENGTH_SHORT).show();

                }
                //Loan amount boundary
                else if(Double.parseDouble(LoanIn.getText().toString())<10000||Double.parseDouble(LoanIn.getText().toString())>10000000){

                    Toast.makeText(MainActivity.this, "Loan amount must be between $10,000 and $10,000,000", Toast.LENGTH_SHORT).show();
                }
                else{
                    //initializing values
                    Loan=Double.valueOf(LoanIn.getText().toString());
                    Interest=Double.valueOf(InterestIn.getText().toString());
                    Term =Integer.parseInt(TermIn.getText().toString());

                    //Initializing results through EMI function
                    ResultDouble = CalculateEMI(Loan,Interest, Term);
                    Result.setText(String.format("%.2f", ResultDouble));

                }
            }
        });

    }

    //The Function to calculate EMI using Loan, Interest and Term
    public double CalculateEMI(Double P,Double interest,int term){

        double res;
        double r =(interest/100)/12;
        double n=term*12;
        res= P * r *((Math.pow(1 + r, n))) / ((Math.pow(1 + r, n)) - 1);//The EMI Formula
        return  res;
    }

}