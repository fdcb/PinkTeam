package pinkteampdm.farmerhelper;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DateActivity extends AppCompatActivity {

    ArrayList<String> cultures;
    DataBaseHelper helpBD;
    SQLiteDatabase db;
    EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        cultures=getIntent().getExtras().getStringArrayList("cultures");
        helpBD = new DataBaseHelper(this);
        db = helpBD.getWritableDatabase();

        date= (EditText) findViewById(R.id.date_editText);
    }

    public void insertDate(View view){
      /* if ( helpBD.insertCultureRegistry(db, cultures.get(0), date.getText().toString(), helpBD.no_location) ){
             Toast.makeText(this, "Foi inserido na BD a DATA", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Nada foi inserido", Toast.LENGTH_LONG).show();
        }
        helpBD.listCultureRegistry(db);*/


    }


}
