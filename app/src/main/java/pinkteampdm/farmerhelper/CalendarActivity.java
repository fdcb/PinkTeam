package pinkteampdm.farmerhelper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, PlantActivity.class);
// use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

       /* Intent intent2 = new Intent(this, LocationActivity.class);
        PendingIntent pIntent2 = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);*/

// build notification
// the addAction re-use the same intent to keep the example short
        Notification n  = new Notification.Builder(this)
                .setContentTitle("New mail from " + "VANESSA@gmail.com")
                .setContentText("Subject")
                .setSmallIcon(R.drawable.picket)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.picket, "Call",pIntent)
                .addAction(R.drawable.farmgps2, "More", pIntent)
                .addAction(R.drawable.coordenadas, "And more", pIntent).build();

        notificationManager.notify(0, n);
    }


}
