package pinkteampdm.farmerhelper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class GpsInfoActivity extends AppCompatActivity {

    EditText latitude;
    EditText longitude;
    TextView res;


    DataBaseHelper helpBD;
    SQLiteDatabase bd;

    String nameCulture;
    String dateCulture;
    private static final String TAG = "InserirDados";
    ArrayList<String> cultures;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_info);

        latitude = (EditText) findViewById(R.id.latitude_value);
        longitude = (EditText) findViewById(R.id.longitude_value);
        res = (TextView) findViewById(R.id.res_txt);
        helpBD = new DataBaseHelper(this);
        bd = helpBD.getWritableDatabase();

       cultures=getIntent().getExtras().getStringArrayList("cultures");
       /* for ( int i=0;i<cultures.size();i++)
            Log.i("GPS ACTIVIDADE",cultures.get(i) );*/

        nameCulture = "Couve";
        dateCulture = "1";

       /* SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        Date data = new Date(0);

        Calendar  cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = (Date) cal.getTime();
        dateCulture = dateFormat.format(data_atual);
       Log.i("Data Cultura","Data: "+dateCulture);*/
    }



    public void insertDataGPS(View view ){
        double inicialValue, value, inicialValue1,value1;
        int degree,minutes,seconds,degree1,minutes1,seconds1;
        Boolean sucess;

        if ( latitude.length()==0 ){
            Toast.makeText(GpsInfoActivity.this, "Latitude inválida", Toast.LENGTH_SHORT).show();
        }
        if ( longitude.length()==0){
            Toast.makeText(GpsInfoActivity.this, "Longitude inválida", Toast.LENGTH_SHORT).show();
        }
        /*
                tipo valor=23,4

                grau=23
                Calcular o minuto:
                    0.4*60
                   minuto= parte inteira do calculo
                   segundos = valor do resto do calculo * 60
        */
        value= Double.parseDouble( latitude.getText().toString() );
        inicialValue=value;
        degree=(int)value;
        value=value-degree;
        value=value*60;
        minutes=(int)value;
        value=value-minutes;
        value=value*60;
        seconds=(int)value;

        value1 = Double.parseDouble(longitude.getText().toString() );
        inicialValue1=value1;
        degree1=(int)value1;
        value1=value1-degree1;
        value1=value1*60;
        minutes1=(int)value1;
        value1=value1-minutes1;
        value1=value1*60;
        seconds1=(int)value1;
        res.setText("Latitude:"+degree+"º "+minutes+"'"+seconds+"''\n"+"Longitude: "+degree1+"º "+minutes1+"'"+seconds1+"''");

        sucess=helpBD.insertCultureRegistry(bd, nameCulture, dateCulture, inicialValue+","+inicialValue1);
        Log.i(TAG,"Já foste"+sucess);
        if ( sucess ) {
            Toast.makeText(this, "Foi inserido na BD", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Nada foi inserido", Toast.LENGTH_LONG).show();

        }

    }

}
