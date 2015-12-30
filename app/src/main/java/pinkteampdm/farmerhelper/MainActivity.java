package pinkteampdm.farmerhelper;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView cultureName_aCTextView;
    private LinearLayout cultureZone_linearLayout;
    private ScrollView cultureZone_scrollView;
    private SQLiteDatabase oSQLiteDB;
    private DataBaseHelper dBHelp;
    private String[] cultureNamesArray;
    private Vector<String> insertedCulturesArray;
    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cultureZone_linearLayout = (LinearLayout)findViewById(R.id.linearLayout_nomes);
        insertedCulturesArray = new Vector<>();

        cultureName_aCTextView = (AutoCompleteTextView)findViewById(R.id.editText_NomeCultura);
        cultureZone_scrollView = (ScrollView)findViewById(R.id.scrollView_zonaNomes);

        dBHelp = new DataBaseHelper(this);
        oSQLiteDB = dBHelp.getWritableDatabase();

        cultureNamesArray = dBHelp.getListCultureNames(oSQLiteDB);

        ArrayAdapter<String> newAdapter = new ArrayAdapter<>(this, R.layout.actv, cultureNamesArray);
        cultureName_aCTextView.setAdapter(newAdapter);

        //GPS Stuff - begin
        gps = new GPSTracker(MainActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        //GPS Stuff - end


    }

    public void onClickAddNameButton(View view){
        boolean flag = false;
        String newName = cultureName_aCTextView.getText().toString();

        for(int i = 0; i<cultureNamesArray.length; i++)
            if(cultureNamesArray[i].equals(newName))
                flag = true;

        if(!flag) {
            cultureName_aCTextView.setError(Html.fromHtml("<font color='green'>this is the error</font>"));
            // cultureName_aCTextView.setError("Cultura inexistente!\nInsira uma cultura presente na lista.");
            return;
        }

        for(int i = 0 ; i < insertedCulturesArray.size(); i++)
            if(insertedCulturesArray.elementAt(i).equals(newName)){
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toasts_layout, (ViewGroup) findViewById(R.id.toast_layout_root));
                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText(R.string.alreadyExistsCulture);
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
            }

        insertedCulturesArray.addElement(newName);

        LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.row, null);
        final TextView textOut = (TextView)addView.findViewById(R.id.textout);
        textOut.setText(newName);
        Button buttonRemove = (Button)addView.findViewById(R.id.remove);
        buttonRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for( int i = 0; i < insertedCulturesArray.size(); i++)
                    if(insertedCulturesArray.elementAt(i).equals(textOut.getText())){
                        insertedCulturesArray.removeElementAt(i);
                        break;
                    }
                ((LinearLayout) addView.getParent()).removeView(addView);
            }
        });

     // cultureZone_linearLayout.addView(createNewLinearLayout(createNewTextView(newName), createNewButton()));
        cultureZone_linearLayout.addView(addView);
        cultureZone_scrollView.scrollTo(0, cultureZone_scrollView.getBottom());
    }

    private boolean checkNewCulture(String newName){
        if(newName.equals(""))
            return false;
        if(newName.length()>= 1)
            if ((newName.charAt(0) == ' ') && (newName.charAt(1)!= ' '))
                return true;

        return true;
    }

    public void onClickFinalize( View view){
        Intent newIntent = new Intent( getApplicationContext(),PlantActivity.class);
        newIntent.putExtra("cultures",insertedCulturesArray);
       // newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);
    }
  /*  private LinearLayout createNewLinearLayout(TextView textView, Button button){
        LinearLayout horizontalLL = new LinearLayout(this);

        horizontalLL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT));
        horizontalLL.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        //   lp.addRule(RelativeLayout.LEFT_OF, textView.getId());
        horizontalLL.addView(button, lp);
        horizontalLL.addView(textView);

        return horizontalLL;
    }

    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setTextSize(18);
        textView.setText(text);
        return textView;
    }

    private Button createNewButton(){
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final Button button = new Button(this);
        button.setLayoutParams(lparams);

        button.setText("x");
        return button;
    } */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
