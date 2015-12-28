package pinkteampdm.farmerhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class PlantActivity extends AppCompatActivity {

    ArrayList<String> cultures;
    String nameCulture;
    TextView titlePlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        cultures=getIntent().getExtras().getStringArrayList("cultures");
        titlePlant=(TextView) findViewById(R.id.textView_plant);
        for ( int i=0;i<cultures.size();i++)
            nameCulture=cultures.get(i);
            titlePlant.setText(titlePlant.getText()+" "+nameCulture+"?");

    }

    public void onClickNextActitvity(View view){
        Intent newIntent = new Intent( getApplicationContext(), LocationActivity.class);
        newIntent.putExtra("cultures",cultures);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);
    }

}
