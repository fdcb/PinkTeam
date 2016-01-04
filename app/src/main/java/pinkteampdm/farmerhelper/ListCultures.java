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

public class ListCultures extends AppCompatActivity {

    LinearLayout cult_layout;
    DataBaseHelper dbHelper;
    SQLiteDatabase sqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cultures);

        cult_layout = (LinearLayout)findViewById(R.id.cultList_linearLayout);
        dbHelper = new DataBaseHelper(this);
        sqlDB = dbHelper.getReadableDatabase();
        final String[] cultureNames = dbHelper.getListCultureNames(sqlDB);

        for (int i = 0; i< cultureNames.length; i++){
            LayoutInflater layoutInflater =
                    (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View addView = layoutInflater.inflate(R.layout.text_view_act_names, null);

            TextView name_tv = (TextView) addView.findViewById(R.id.textView);
            name_tv.setText(cultureNames[i]);
            name_tv.setTextColor(getResources().getColor(R.color.font_unselected));
            final int finalI = i;
          /*  name_tv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(getApplicationContext(), ActProfileActivity.class);
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    newIntent.putExtra("Act_Name", cultureNames[finalI]);
                    startActivity(newIntent);
                }
            });*/
            cult_layout.addView(addView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_cultures, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
