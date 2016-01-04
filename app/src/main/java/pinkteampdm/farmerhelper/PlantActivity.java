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
    Calendar calendar;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        cultures=getIntent().getExtras().getStringArrayList("cultures");
        titlePlant=(TextView) findViewById(R.id.textView_plant);


        nameCulture = cultures.get(0);
        titlePlant.setText(titlePlant.getText() + "?");
        getSupportActionBar().setTitle(nameCulture);
        helpBD = new DataBaseHelper(this);
        db = helpBD.getWritableDatabase();
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }
        //buttonYes
    @SuppressWarnings("deprecation")
    public void onClickNextActivity(View view) {
        showDialog(123); // id_of_dialog
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 123) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year | arg2 = month |  arg3 = day
            //Log.d("data escolhida :", cultures.size()-1+" "+arg3+"-"+(arg2+1)+"-"+arg1);
            for( int i=0;i<cultures.size();i++)
                Log.d("Antes de add data: "+i+":", cultures.get(i));
           cultures.add(0,arg3+"-"+(arg2+1)+"-"+arg1);
            for( int i=0;i<cultures.size();i++)
               Log.d("Depois de add data "+i+":", cultures.get(i));
            helpBD.listCultureRegistry(db);
            Intent newIntent = new Intent( getApplicationContext(), LocationActivity.class);
            newIntent.putExtra("cultures",cultures);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
        }
    };

    //buttonNo
    public void onClickInsertData(View view) {
        //insert info into db
        helpBD.insertCultureRegistry(db, nameCulture, helpBD.no_date, helpBD.no_location);
        if (cultures.size()<= 1){
            Intent meAgain = new Intent(getApplicationContext(), CalendarActivity.class);
            meAgain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(meAgain);
            return;
        }
        cultures.remove(0);
        for ( int i=0;i<cultures.size();i++)
            Log.i("Name Culture", cultures.get(i));
        Intent meAgain = new Intent( getApplicationContext(), PlantActivity.class);
        meAgain.putExtra("cultures",cultures);
        meAgain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(meAgain);
      //  helpBD.listCultureRegistry(db);
    }
}

