package pinkteampdm.farmerhelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ActProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_profile);
        String name = getIntent().getStringExtra("Act_Name");

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SQLiteDatabase sqlDB =  dbHelper.getReadableDatabase();

        TextView descriptionTV = (TextView)findViewById(R.id.description_textView);

        Cursor newCursor = sqlDB.rawQuery(
                "SELECT " +dbHelper.COLUMN3_ACTIVITY_DESCRIPTION+
                " FROM " +dbHelper.TABLE_ACTIVITY+
                " WHERE " +dbHelper.COLUMN2_ACTIVITY_NAME +"=?",
                new String[]{name});
        newCursor.moveToNext();
        descriptionTV.setText(newCursor.getString(
                newCursor.getColumnIndex(dbHelper.COLUMN3_ACTIVITY_DESCRIPTION)));
        getSupportActionBar().setTitle(name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
