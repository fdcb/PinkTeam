package pinkteampdm.farmerhelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class NotificationAlert extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, MyAlarmService.class);
        context.startService(service);
    }
}
