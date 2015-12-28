package pinkteampdm.farmerhelper;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GpsInfoActivity extends AppCompatActivity {

    EditText latitude;
    EditText longitude;
    TextView res;

    DataBaseHelper helpBD;
    SQLiteDatabase bd;

    String nameCulture;
    String dateCulture;
    private static final String TAG="InserirDados";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_info);

        latitude = (EditText) findViewById( R.id.latitude_value);
        longitude = (EditText) findViewById( R.id.longitude_value);
        //res = (TextView) findViewById( R.id.res_txt);
        helpBD = new DataBaseHelper(this);
        bd = helpBD.getWritableDatabase();

        nameCulture="Couve";
        dateCulture="12-12-2015";
    }

    public void insertDataGPS(View view ){
        double inicialValue, value, inicialValue1,value1;
        int degree,minutes,seconds,degree1,minutes1,seconds1;
        String valor;

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
        //res.setText("Latitude:"+degree+"º "+minutes+"'"+seconds+"''\n"+"Longitude: "+degree1+"º "+minutes1+"'"+seconds1+"''");


        if ( helpBD.insertCultureRegistry(bd, nameCulture, dateCulture, inicialValue+","+inicialValue1)!=-1 ) {
            Toast.makeText(this, "Foi inserido na BD", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Nada foi inserido", Toast.LENGTH_LONG).show();

        }

    }

}
