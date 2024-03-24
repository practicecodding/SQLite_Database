package com.hamidul.digitalmoneybag;

import android.database.Cursor;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Showing_Data extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    ArrayList arrayList;
    HashMap hashMap;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showing_data);

        recyclerView = findViewById(R.id.recyclerView);
        textView = findViewById(R.id.textView);
        dbHelper = new DatabaseHelper(this);

        loadData();

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder>{

        @NonNull
        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.showing_item,parent,false);
            return new myViewHolder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

            hashMap = (HashMap) arrayList.get(position);

            String id = (String) hashMap.get("id");
            String amount = (String) hashMap.get("amount");
            String reason = (String) hashMap.get("reason");
            double dDate = (double) hashMap.get("date");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh : mm a", Locale.getDefault());

            String date = simpleDateFormat.format(dDate);
            String time = simpleTimeFormat.format(dDate);

            holder.tvReason.setText(reason);
            holder.tvBalance.setText("BDT "+amount);
            holder.tvDate.setText(date+"\n\n"+time);

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class myViewHolder extends RecyclerView.ViewHolder{
            TextView tvReason,tvBalance,tvDelete,tvDate;
            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                tvReason = itemView.findViewById(R.id.tvReason);
                tvBalance = itemView.findViewById(R.id.tvBalance);
                tvDelete = itemView.findViewById(R.id.tvDelete);
                tvDate = itemView.findViewById(R.id.tvDate);

            }
        }

    }

    public void loadData (){

        arrayList = new ArrayList();

        Cursor cursor = dbHelper.getAllExpenseData();

        if (cursor!=null && cursor.getCount()>0) {
            while (cursor.moveToNext()){
                String id = String.valueOf(cursor.getInt(0));
                String amount = String.valueOf(cursor.getDouble(1));
                String reason = cursor.getString(2);
                double date = cursor.getDouble(3);

                hashMap = new HashMap();
                hashMap.put("id",id);
                hashMap.put("amount",amount);
                hashMap.put("reason",reason);
                hashMap.put("date",date);
                arrayList.add(hashMap);
            }

            recyclerView.setAdapter(new MyAdapter());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }




    }



}