package dk.brams.android.flickrgallery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartUpReceiver extends BroadcastReceiver{
    private static final String TAG = "StartUpReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Received Broadcast intent: " + intent.getAction());

        boolean isOn = QueryPreferences.isAlarmOn(context);
        PollService.setServiceAlarm(context, isOn);
    }
}
