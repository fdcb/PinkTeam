package pinkteampdm.farmerhelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

public class LocationActivity extends AppCompatActivity {

    Button yesButton;
    Button noButton;
    TextView titleChoose;
    ArrayList<String> cultures;
    String nameCulture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        cultures=getIntent().getExtras().getStringArrayList("cultures");

        yesButton = (Button) findViewById(R.id.buttonYes);
        noButton = (Button) findViewById(R.id.buttonNo);
        titleChoose=(TextView) findViewById(R.id.textView_chooseCulture);


       for ( int i=0;i<cultures.size();i++)
           nameCulture=cultures.get(i);
            titleChoose.setText(titleChoose.getText()+" "+nameCulture+"?");
        //Log.i("Rebentei", "Depois de alterar o nome");

    }

    public void locationAutomatic( View view){
        System.out.println("YESSS");
    }

    public void locationManual( View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        for ( int i=0;i<cultures.size();i++ )
            nameCulture=cultures.get(i);
        builder.setTitle("  Localização GPS");
        builder.setMessage("Quer introduzir as coordenadas para a cultura ?");

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                Intent gpsValues = new Intent(getApplicationContext(), GpsInfoActivity.class);
                gpsValues.putStringArrayListExtra("cultures", cultures);
                gpsValues.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gpsValues);

            }

        });

        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Vai buscar o método andre ou seja o locationAutomatic
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
