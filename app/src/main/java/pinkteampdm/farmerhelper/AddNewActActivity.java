package pinkteampdm.farmerhelper;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddNewActActivity extends AppCompatActivity {

    DataBaseHelper dbHelper;
    SQLiteDatabase sqlDB;
    String name, description;
    EditText nameET, descET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_act);

        dbHelper = new DataBaseHelper(this);
        sqlDB = dbHelper.getWritableDatabase();

        nameET = (EditText)findViewById(R.id.actName_editText);
        descET = (EditText)findViewById(R.id.description_editText);
    }

    public void onClickFinish(View view){
        name = nameET.getText().toString();
        description = descET.getText().toString();
        dbHelper.insertActivity(sqlDB, name, description);
        Intent newIntent = new Intent(this, ListActActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);
    }

}
