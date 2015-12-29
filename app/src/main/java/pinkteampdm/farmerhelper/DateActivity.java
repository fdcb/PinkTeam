package pinkteampdm.farmerhelper;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class DateActivity extends AppCompatActivity {

    ArrayList<String> cultures;
    DataBaseHelper helpBD;
    SQLiteDatabase db;
    DatePicker datePicker;
    Calendar calendar;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        cultures=getIntent().getExtras().getStringArrayList("cultures");
        helpBD = new DataBaseHelper(this);
        db = helpBD.getWritableDatabase();

        datePicker= (DatePicker) findViewById(R.id.date_datePicker);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void insertDate(View view){
      /* if ( helpBD.insertCultureRegistry(db, cultures.get(0), date.getText().toString(), helpBD.no_location) ){
             Toast.makeText(this, "Foi inserido na BD a DATA", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Nada foi inserido", Toast.LENGTH_LONG).show();
        }
        helpBD.listCultureRegistry(db);*/

        //String dateComplete=date.getDayOfMonth()+"-"+date.getMonth()+"-"+date.getYear();
        Log.i("Date escolhida",datePicker.getDayOfMonth()+"-"+datePicker.getMonth()+1+"-"+datePicker.getYear());

    }


}
