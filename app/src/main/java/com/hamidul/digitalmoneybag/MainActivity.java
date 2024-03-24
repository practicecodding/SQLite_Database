package com.hamidul.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView addExpense,addIncome,tvExpense,tvIncome,tvBalance,showDetails;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);

        addExpense = findViewById(R.id.addExpense);
        addIncome = findViewById(R.id.addIncome);
        tvExpense = findViewById(R.id.tvExpense);
        tvIncome = findViewById(R.id.tvIncome);
        tvBalance = findViewById(R.id.balance);
        showDetails = findViewById(R.id.showDetails);
        dbHelper = new DatabaseHelper(this);

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData.EXPENSE = true;
                startActivity(new Intent(MainActivity.this,AddData.class));
            }
        });

        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData.EXPENSE = false;
                startActivity(new Intent(MainActivity.this,AddData.class));
            }
        });

        updateUI();

        showDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Showing_Data.class));
            }
        });


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateUI();
    }

    private void updateUI (){
        tvExpense.setText("BDT "+dbHelper.calculateExpense());
        tvIncome.setText("BDT "+dbHelper.calculateIncome());
        double balance = ( dbHelper.calculateIncome() - dbHelper.calculateExpense() );
        tvBalance.setText("BDT "+balance);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    //===================================================================
}