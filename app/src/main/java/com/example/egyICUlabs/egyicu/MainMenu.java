package com.example.egyICUlabs.egyicu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainMenu extends AppCompatActivity {
    Toolbar toolbar;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //getSupportActionBar().hide();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Egy ICU");
        toolbar.setAlpha(0.85f);



        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new ImageAdapter(this,0));


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                switch (position)
                {
                    case 0:
                        startActivity( new Intent(MainMenu.this , ICU_Submenue.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainMenu.this , Specialized_Submenu.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainMenu.this , Ambulance_submenu.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainMenu.this , Home_Submenu.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainMenu.this , About_Submenu.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainMenu.this , Callus.class));
                        break;
                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}
