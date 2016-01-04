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
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Vector;


public class CalendarActivity extends AppCompatActivity {


    LinearLayout tab;
    TextView current_week,next_week,month_TV;
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
        current_week = (TextView)findViewById(R.id.tv1);
        next_week = (TextView)findViewById(R.id.tv2);

        Calendar newCalendar;
        newCalendar = Calendar.getInstance();
        day = newCalendar.get(Calendar.DAY_OF_MONTH);
        week = fixWeek(newCalendar.get(Calendar.WEEK_OF_MONTH));
        month = newCalendar.get(Calendar.MONTH);
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        month_name = months[month];
        month_TV.setText(month_name);

        tab = (LinearLayout)findViewById(R.id.act_LinearLayout);

        dbHelp = new DataBaseHelper(this);
        sqdb = dbHelp.getWritableDatabase();

        registeredCultures = dbHelp.getCultureRegistryName(sqdb);
        addActivities();
   /*   tab1 = (LinearLayout)findViewById(R.id.tab2);
        tab2 = (LinearLayout)findViewById(R.id.tab3);

        tab1.setVisibility(View.INVISIBLE);
        tab2.setVisibility(View.INVISIBLE);*/
    }

    private void addActivities(){
        for(int i = 0; i< registeredCultures.length; i++){
            Vector<Culture> act = dbHelp.getActivitiesForMonthCulture(sqdb, registeredCultures[i],
                    month_name, week);
            for(int j = 0; j< act.size(); j++){
                addViewAct(registeredCultures[i], act.elementAt(j).getAct_name(),
                        act.elementAt(j).getMoon(),act.elementAt(j).getPlaceString(),
                        act.elementAt(j).getCountry_zone());
            }
        }
    }

    private void addViewAct(String culture, String activity, String moon, String local, String place){
        LayoutInflater layoutInflater = (LayoutInflater)
                getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View addView = layoutInflater.inflate(R.layout.todo, null);
        TextView actCult = (TextView)addView.findViewById(R.id.actCult_TextView);
        actCult.setText(activity + " " + culture);
        TextView moonTV = (TextView)addView.findViewById(R.id.lua_textView);
        moonTV.setText(moon);
        TextView localTV = (TextView)addView.findViewById(R.id.local_textView);
        localTV.setText(local);
        TextView placeTV = (TextView)addView.findViewById(R.id.zone_TextView);
        placeTV.setText(place);
        tab.addView(addView);
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
        if(id == R.id.listActivities){
            Intent newIntent = new Intent(this, ListActActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
            return true;
        }
        if(id == R.id.listCultures){
            Intent newIntent = new Intent(this, ListCultures.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClickTab0(View view){

        current_week.setTextColor(getResources().getColor(R.color.font_selected));
        next_week.setTextColor(getResources().getColor(R.color.font_unselected));

        tab.removeViews(0, tab.getChildCount());
        Calendar newCalendar;
        newCalendar = Calendar.getInstance();
        day = newCalendar.get(Calendar.DAY_OF_MONTH);
        week = fixWeek(newCalendar.get(Calendar.WEEK_OF_MONTH));
        tab = (LinearLayout)findViewById(R.id.act_LinearLayout);

        dbHelp = new DataBaseHelper(this);
        sqdb = dbHelp.getWritableDatabase();

        registeredCultures = dbHelp.getCultureRegistryName(sqdb);
        addActivities();
    }

    public void onClickTab1(View view){
        current_week.setTextColor(getResources().getColor(R.color.font_unselected));
        next_week.setTextColor(getResources().getColor(R.color.font_selected));

        tab.removeViews(0, tab.getChildCount());
        Calendar newCalendar;
        newCalendar = Calendar.getInstance();
        day = newCalendar.get(Calendar.DAY_OF_MONTH);
        week++;
        tab = (LinearLayout)findViewById(R.id.act_LinearLayout);

        dbHelp = new DataBaseHelper(this);
        sqdb = dbHelp.getWritableDatabase();

        registeredCultures = dbHelp.getCultureRegistryName(sqdb);
        addActivities();
    }
/*
    public void onClickTab2(View view){
        tab2.setVisibility(View.VISIBLE);
        tab1.setVisibility(View.INVISIBLE);
        tab0.setVisibility(View.INVISIBLE);
    } */
}
