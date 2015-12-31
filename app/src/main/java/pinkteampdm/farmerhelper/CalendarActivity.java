package pinkteampdm.farmerhelper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


public class CalendarActivity extends AppCompatActivity {


    LinearLayout tab0, tab1, tab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        tab0 = (LinearLayout)findViewById(R.id.tab1);
   /*     tab1 = (LinearLayout)findViewById(R.id.tab2);
        tab2 = (LinearLayout)findViewById(R.id.tab3);

        tab1.setVisibility(View.INVISIBLE);
        tab2.setVisibility(View.INVISIBLE);*/
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.insertCulture) {
            Intent newIntent = new Intent(this, MainActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            newIntent.putExtra("Activity", "CalendarAct");
            startActivity(newIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
 /*   public void onClickTab0(View view){
        tab0.setVisibility(View.VISIBLE);
        tab1.setVisibility(View.INVISIBLE);
        tab2.setVisibility(View.INVISIBLE);
    }

    public void onClickTab1(View view){
        tab1.setVisibility(View.VISIBLE);
        tab0.setVisibility(View.INVISIBLE);
        tab2.setVisibility(View.INVISIBLE);
    }

    public void onClickTab2(View view){
        tab2.setVisibility(View.VISIBLE);
        tab1.setVisibility(View.INVISIBLE);
        tab0.setVisibility(View.INVISIBLE);
    } */
}
