package pinkteampdm.farmerhelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LocationActivity extends AppCompatActivity {

    Button yesButton;
    Button noButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        yesButton = (Button) findViewById(R.id.buttonYes);
        noButton = (Button) findViewById(R.id.buttonNo);


    }

    public void locationAutomatic( View view){
        System.out.println("YESSS");
    }

    public void locationManual( View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("  Localização GPS");
        builder.setMessage("Quer introduzir as suas coordenadas ?");

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                Intent gpsValues = new Intent( getApplicationContext(), GpsInfoActivity.class );
                gpsValues.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
