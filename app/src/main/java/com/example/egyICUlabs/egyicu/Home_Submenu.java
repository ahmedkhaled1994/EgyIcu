package com.example.egyICUlabs.egyicu;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class Home_Submenu extends ActionBarActivity {
Toolbar toolbar;
    int reqCode =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__submenu);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setAlpha(0.85f);


        SpannableString s = new SpannableString(getString(R.string.home_delivery));
        s.setSpan(new com.example.egyICUlabs.egyicu.TypefaceSpan(this, "GESS.OTF"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);


        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new ImageAdapter(this, 4));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                switch (position) {
                    case 0:
                        Intent homeIntent = new Intent(Home_Submenu.this, Home_Form.class);
                        startActivity(homeIntent);
                        break;
                    case 1:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkSelfPermission("android.permission.CALL_PHONE") == getPackageManager().PERMISSION_DENIED)
                                requestPermissions(new String[]{"android.permission.CALL_PHONE"}, reqCode);
                            else {
                                String uri = getResources().getString(R.string.HOME_DOCTOR1); ;
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse(uri));
                                startActivity(intent);
                            }
                        }else {
                            String uri = getResources().getString(R.string.HOME_DOCTOR1);
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse(uri));
                            startActivity(intent);
                        }
                        break;
                    case 2:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkSelfPermission("android.permission.CALL_PHONE") == getPackageManager().PERMISSION_DENIED)
                                requestPermissions(new String[]{"android.permission.CALL_PHONE"}, reqCode);
                            else {
                                String uri = getResources().getString(R.string.HOME_DOCTOR2); ;
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse(uri));
                                startActivity(intent);
                            }
                        }else {
                            String uri = getResources().getString(R.string.HOME_DOCTOR2);
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse(uri));
                            startActivity(intent);
                        }
                        break;
                    case 3:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkSelfPermission("android.permission.CALL_PHONE") == getPackageManager().PERMISSION_DENIED)
                                requestPermissions(new String[]{"android.permission.CALL_PHONE"}, reqCode);
                            else {
                                String uri = getResources().getString(R.string.HOME_DOCTOR3); ;
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse(uri));
                                startActivity(intent);
                            }
                        }else {
                            String uri = getResources().getString(R.string.HOME_DOCTOR3);
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse(uri));
                            startActivity(intent);
                        }
                        break;
                }

            }
        });

    }

    @Override

    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){
        switch (permsRequestCode) {
            case 0:
                String uri = getResources().getString(R.string.ICU_DOCTOR1);
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
                break;
            default:
                Toast.makeText(getApplication(), "Please allow calling", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home__submenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
