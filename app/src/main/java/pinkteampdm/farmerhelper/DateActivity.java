package pinkteampdm.farmerhelper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class DateActivity extends AppCompatActivity {

    ArrayList<String> cultures;
    DataBaseHelper helpBD;
    SQLiteDatabase db;
    DatePicker datePicker;
    Calendar calendar;
    TextView dateView;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        cultures=getIntent().getExtras().getStringArrayList("cultures");
        helpBD = new DataBaseHelper(this);
        db = helpBD.getWritableDatabase();

        dateView = (TextView) findViewById(R.id.dateView);
        datePicker= (DatePicker) findViewById(R.id.date_datePicker);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "YEAHHHH", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            // Log.d("dia:", ""+arg1);
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
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
