package com.dayal.broadcasts.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dayal.broadcasts.R;
import com.dayal.broadcasts.data.DbHelper;
import com.dayal.broadcasts.model.IncomingNumber;
import com.dayal.broadcasts.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<IncomingNumber> numberList;
    private List<IncomingNumber> listItems = new ArrayList<>();
    private DbHelper db;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readFromDb();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                readFromDb();
            }
        };

    }

    private void readFromDb(){

        db = new DbHelper(this);
        numberList = new ArrayList<>();
        //listItems.clear();
        numberList = db.getNumber();

        for (IncomingNumber n : numberList ){
            IncomingNumber incomingNumber = new IncomingNumber();
            incomingNumber.setId(n.getId());
            incomingNumber.setNumber(n.getNumber());

            listItems.add(incomingNumber);    Log.w("log","readFromDb(),incoming no: " + n.getNumber());
        }
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        textView = (TextView)findViewById(R.id.text);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(listItems);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.VISIBLE);
        if (!listItems.isEmpty()) textView.setVisibility(View.GONE);

        db.close();

    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(broadcastReceiver,new IntentFilter(Constants.UPDATE_UI_FILTER));
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(broadcastReceiver);
    }
}
