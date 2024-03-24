package com.hamidul.digitalmoneybag;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddData extends AppCompatActivity {

    EditText edAmount,edReason;
    Button button;
    DatabaseHelper dbHelper;
    public static boolean EXPENSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data);

        edAmount = findViewById(R.id.edAmount);
        edReason = findViewById(R.id.edReason);
        button = findViewById(R.id.button);
        dbHelper = new DatabaseHelper(this);

        edAmount.addTextChangedListener(watcher);
        edReason.addTextChangedListener(watcher);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = edReason.getText().toString();
                String sAmount = edAmount.getText().toString();
                double amount = Double.parseDouble(sAmount);

                if (EXPENSE){
                    dbHelper.addExpense(amount,reason);
                }
                else {
                    dbHelper.addIncome(amount,reason);
                }

                startActivity(new Intent(AddData.this,MainActivity.class));

            }
        });


    }


    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String s1 = edAmount.getText().toString();
            String s2 = edReason.getText().toString();

            button.setEnabled(!s1.isEmpty() && !s2.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };




}