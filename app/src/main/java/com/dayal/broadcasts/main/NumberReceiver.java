package com.dayal.broadcasts.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.dayal.broadcasts.data.DbHelper;
import com.dayal.broadcasts.util.Constants;

public class NumberReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

            DbHelper db = new DbHelper(context);
            db.saveToDb(number);

            db.close();
        }

            Intent broadcastIntent = new Intent(Constants.UPDATE_UI_FILTER);
            context.sendBroadcast(broadcastIntent);

    }
}
