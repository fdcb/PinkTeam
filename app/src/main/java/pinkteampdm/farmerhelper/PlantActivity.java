package pinkteampdm.farmerhelper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class PlantActivity extends AppCompatActivity {

    ArrayList<String> cultures;
    String nameCulture;
    TextView titlePlant;
    DataBaseHelper helpBD;
    SQLiteDatabase db;
    //DatePicker datePicker;
    Calendar calendar;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        cultures=getIntent().getExtras().getStringArrayList("cultures");
        titlePlant=(TextView) findViewById(R.id.textView_plant);

        nameCulture = cultures.get(0);
        titlePlant.setText(titlePlant.getText() + " " + nameCulture + "?");

        helpBD = new DataBaseHelper(this);
        db = helpBD.getWritableDatabase();
        //datePicker= (DatePicker) findViewById(R.id.date_datePicker);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

    }

    @SuppressWarnings("deprecation")
    public void onClickNextActivity(View view) {
        showDialog(999);
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
            Log.d("data escolhida :", cultures.size()-1+" "+arg3+"-"+(arg2+1)+"-"+arg1);
           // showDate(arg1, arg2 + 1, arg3);
            //cultures.add(cultures.size()-1,arg3+"-"+(arg2+1)+"-"+arg1);
        }
    };

  /*  private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }*/
    // buttonYes
  /*  public void onClickNextActivity(View view){
        Intent newIntent = new Intent( getApplicationContext(), DateActivity.class);
        newIntent.putExtra("cultures",cultures);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);
    }*/
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
        helpBD.insertCultureRegistry(db, nameCulture, helpBD.no_date, helpBD.no_location);
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

