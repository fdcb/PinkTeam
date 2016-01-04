package pinkteampdm.farmerhelper;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListActActivity extends AppCompatActivity {


    DataBaseHelper dbHelper;
    SQLiteDatabase sqlDB;
    LinearLayout act_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_act);
        act_layout = (LinearLayout)findViewById(R.id.act_linearLayout);
        dbHelper = new DataBaseHelper(this);
        sqlDB = dbHelper.getReadableDatabase();
        final String[] activityNames = dbHelper.getListActivityNames(sqlDB);

        for (int i = 0; i< activityNames.length; i++){
            LayoutInflater layoutInflater =
                    (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View addView = layoutInflater.inflate(R.layout.text_view_act_names, null);

            TextView name_tv = (TextView) addView.findViewById(R.id.textView);
            name_tv.setText(activityNames[i]);
            name_tv.setTextColor(getResources().getColor(R.color.font_unselected));
            final int finalI = i;
            name_tv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(getApplicationContext(), ActProfileActivity.class);
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    newIntent.putExtra("Act_Name", activityNames[finalI]);
                    startActivity(newIntent);
                }
            });
            act_layout.addView(addView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_act, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_activity) {
            Intent newIntent = new Intent(this, AddNewActActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
