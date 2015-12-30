package pinkteampdm.farmerhelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class LocationActivity extends AppCompatActivity {

    Button yesButton;
    Button noButton;
    TextView titleChoose;
    ArrayList<String> cultures;
    String nameCulture;
    DataBaseHelper helpBD;
    SQLiteDatabase db;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        helpBD = new DataBaseHelper(this);
        db = helpBD.getWritableDatabase();

        cultures=getIntent().getExtras().getStringArrayList("cultures");

        yesButton = (Button) findViewById(R.id.buttonYes);
        noButton = (Button) findViewById(R.id.buttonNo);
        titleChoose = (TextView) findViewById(R.id.textView_chooseCulture);


        //   for ( int i=0;i<cultures.size();i++)
        nameCulture=cultures.get(1);
            titleChoose.setText(titleChoose.getText()+" "+nameCulture+"?");

        gps = new GPSTracker(LocationActivity.this);
        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }
    //buttonYes
    public void locationAutomatic( View view){
        System.out.println("YESSS");
        //depois de implementar a actividade onde vamos buscar a data, alterar codigo aqui!!!

        helpBD.insertCultureRegistry(db, nameCulture, cultures.get(0),helpBD.no_location);
        if (cultures.size()<=2)
            return;
        cultures.remove(0);
        cultures.remove(0);
        for ( int i=0;i<cultures.size();i++)
            Log.i("Name Culture", cultures.get(i));
        Intent meAgain = new Intent( getApplicationContext(), PlantActivity.class);
        meAgain.putExtra("cultures",cultures);
        meAgain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(meAgain);
    }
    //buttonNo
    public void locationManual( View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.localGPS);
        builder.setMessage(R.string.insertCoo);

        builder.setPositiveButton(R.string.yesButton, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                Intent gpsValues = new Intent(getApplicationContext(), GpsInfoActivity.class);
                //gpsValues.putStringArrayListExtra("cultures", cultures);
                gpsValues.putExtra("cultures", cultures);
                gpsValues.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gpsValues);

            }

        });

        builder.setNegativeButton(R.string.noButton, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
