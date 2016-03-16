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


public class Callus extends ActionBarActivity {
Toolbar toolbar;
    int reqCode =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callus);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setAlpha(0.85f);

        SpannableString s = new SpannableString(getString(R.string.call_us));
        s.setSpan(new com.example.egyICUlabs.egyicu.TypefaceSpan(this, "GESS.OTF"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);



        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new ImageAdapter(this, 6));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                switch (position) {
                    case 0:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkSelfPermission("android.permission.CALL_PHONE") == getPackageManager().PERMISSION_DENIED)
                                requestPermissions(new String[]{"android.permission.CALL_PHONE"}, reqCode);
                            else {
                                String uri = getResources().getString(R.string.ICU_DOCTOR1); ;
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse(uri));
                                startActivity(intent);
                            }
                        }else {
                            String uri = getResources().getString(R.string.ICU_DOCTOR1);
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse(uri));
                            startActivity(intent);
                        }
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
                            String uri = getResources().getString(R.string.FOR_Questions);
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
                                String uri = getResources().getString(R.string.ICU_DOCTOR3); ;
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse(uri));
                                startActivity(intent);
                            }
                        }else {
                            String uri = getResources().getString(R.string.ICU_DOCTOR3);
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse(uri));
                            startActivity(intent);
                        }
                        break;
                    case 3:
                        Intent i = new Intent(Intent.ACTION_SENDTO);
                        i.setData(Uri.parse("mailto:ahmedhsnab1994@gmail.com"));
                        //i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                        //i.putExtra(Intent.EXTRA_TEXT, "body of email");
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getApplication(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_callus, menu);
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
