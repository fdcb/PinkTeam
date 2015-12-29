package pinkteampdm.farmerhelper;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlantActivity extends AppCompatActivity {

    ArrayList<String> cultures;
    String nameCulture,a = "sem_data" ,b = "sem_gps";
    TextView titlePlant;
    DataBaseHelper helpBD;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        cultures=getIntent().getExtras().getStringArrayList("cultures");
        titlePlant=(TextView) findViewById(R.id.textView_plant);
       /* for ( int i=0;i<cultures.size();i++) {
            //currIndex=i;
            Log.i("Name Culture", cultures.get(i));

        }*/
        nameCulture = cultures.get(0);
        titlePlant.setText(titlePlant.getText() + " " + nameCulture + "?");

        helpBD = new DataBaseHelper(this);
        db = helpBD.getWritableDatabase();

    }
    // buttonYes
    public void onClickNextActivity(View view){
        Intent newIntent = new Intent( getApplicationContext(), LocationActivity.class);
        newIntent.putExtra("cultures",cultures);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);
    }
    //buttonNo
    public void onClickInsertData(View view) {
        //insert info into db
     //   if (helpBD.insertCultureRegistry(db, nameCulture, a, b)) {
            /*Toast.makeText(this, "Foi inserido na BD", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Nada foi inserido", Toast.LENGTH_LONG).show();
        }
       helpBD.listCultureRegistry(db);*/
    /*     for ( int i=0;i<cultures.size();i++)
            Log.i("Name Culture", cultures.get(i));
    */
        helpBD.insertCultureRegistry(db, nameCulture, a, b);
        if (cultures.size()< 1)
            return;
        cultures.remove(0);
        for ( int i=0;i<cultures.size();i++)
            Log.i("Name Culture", cultures.get(i));
        Intent meAgain = new Intent( getApplicationContext(), PlantActivity.class);
        meAgain.putExtra("cultures",cultures);
        meAgain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(meAgain);
   //    }
    }
}

