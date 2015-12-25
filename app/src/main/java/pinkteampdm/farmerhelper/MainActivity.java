package pinkteampdm.farmerhelper;

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
import android.widget.LinearLayout;
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
    }


    public void onClickAddNameButton(View view){
        boolean flag = false;
        String newName = cultureName_aCTextView.getText().toString();

        for(int i = 0; i<cultureNamesArray.length; i++)
            if(cultureNamesArray[i].equals(newName))
                flag = true;

        if(!flag) {
            cultureName_aCTextView.setError(Html.fromHtml("<font color='blue'>this is the error</font>"));
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
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }

        insertedCulturesArray.addElement(newName);

        cultureZone_linearLayout.addView(createNewTextView(cultureName_aCTextView.getText().toString()));
        cultureZone_scrollView.scrollTo(0, cultureZone_scrollView.getBottom());
    }

    private boolean checkNewCulture(String newName){
        if(newName.equals(""))
            return false;
        if(newName.length()>= 1){
            if ((newName.charAt(0) == ' ') && (newName.charAt(1)!= ' '))
                return true;
        }

        return true;
    }
    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ScrollView.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
      //  textView.setTextSize(18);
        textView.setText(text);
        return textView;
    }

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
