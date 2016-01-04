package pinkteampdm.farmerhelper;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;


public class MyAlarmService extends IntentService {

    CalendarActivity ca;
    SQLiteDatabase sqdb;
    DataBaseHelper dbHelp;

    public MyAlarmService(){
        super("MyAlarmService");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setSmallIcon(R.mipmap.ic_launcher);
        nBuilder.setContentTitle("Alerta");
        //String nome = intent.getStringExtra("medicamento");
        //String dosagem = intent.getStringExtra("dosagem");
        nBuilder.setContentText("AQUI É A MENSAGEM QUE QUERES.");
        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound1);
        nBuilder.setSound(sound);
        Intent notificationIntent = new Intent(this,MainActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        nBuilder.setContentIntent(pendingIntent);
        nBuilder.setAutoCancel(true);
        nm.notify((int) System.currentTimeMillis(), nBuilder.build());
        //ca.showNotification();
        // vaiiiii
    }
}
