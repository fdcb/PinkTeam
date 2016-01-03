package pinkteampdm.farmerhelper;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Vector;


public class CalendarActivity extends AppCompatActivity {


    LinearLayout tab, tab1, tab2;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, month_TV;
    int day, month, week;
    String month_name;
    String[] registeredCultures;
    SQLiteDatabase sqdb;
    DataBaseHelper dbHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        month_TV = (TextView)findViewById(R.id.textView);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        tv5 = (TextView)findViewById(R.id.tv5);
        tv6 = (TextView)findViewById(R.id.tv6);
        tv7 = (TextView)findViewById(R.id.tv7);

        Calendar newCalendar;
        newCalendar = Calendar.getInstance();
        day = newCalendar.get(Calendar.DAY_OF_MONTH);
        week = fixWeek(newCalendar.get(Calendar.WEEK_OF_MONTH));
        month = newCalendar.get(Calendar.MONTH);
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        month_name = months[month];
        month_TV.setText(month_name);
        Log.d("day", "" + day);
        Log.d("week", "" + week);
        int aux = day;
        tv1.setText(""+aux);
        aux = auxProcessor(aux);
        tv2.setText(""+aux);
        aux = auxProcessor(aux);
        tv3.setText(""+aux);
        aux = auxProcessor(aux);
        tv4.setText(""+aux);
        aux = auxProcessor(aux);
        tv5.setText(""+aux);
        aux = auxProcessor(aux);
        tv6.setText(""+aux);
        aux = auxProcessor(aux);
        tv7.setText(""+aux);

        tab = (LinearLayout)findViewById(R.id.act_LinearLayout);

        dbHelp = new DataBaseHelper(this);
        sqdb = dbHelp.getWritableDatabase();

        registeredCultures = dbHelp.getCultureRegistryName(sqdb);

        for(int i = 0; i< registeredCultures.length; i++){
            Vector<String> act = dbHelp.getActivitiesForMonthCulture(sqdb, registeredCultures[i],
                                                                        month_name, week);
            for(int j = 0; j< act.size(); j++){
                Log.d(""+registeredCultures, act.elementAt(j));
                LayoutInflater layoutInflater = (LayoutInflater)
                        getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View addView = layoutInflater.inflate(R.layout.todo, null);
                TextView actCult = (TextView)addView.findViewById(R.id.actCult_TextView);
                actCult.setText(act.elementAt(j) + " " + registeredCultures[i]);
                tab.addView(addView);
            }
        }
   /*   tab1 = (LinearLayout)findViewById(R.id.tab2);
        tab2 = (LinearLayout)findViewById(R.id.tab3);

        tab1.setVisibility(View.INVISIBLE);
        tab2.setVisibility(View.INVISIBLE);*/
    }

    private int auxProcessor(int aux){
        if(aux == 31)
            return 1;
        return aux+1;
    }

    private int fixWeek(int week){
        if(week<3)
            return 0;
        return 2;
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
