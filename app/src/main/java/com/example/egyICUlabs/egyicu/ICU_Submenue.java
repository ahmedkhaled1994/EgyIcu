package com.example.egyICUlabs.egyicu;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ICU_Submenue extends AppCompatActivity {
    Toolbar toolbar;
    int reqCode =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_icu__submenue);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setAlpha(0.85f);

        SpannableString s = new SpannableString(getString(R.string.intensive_care));
        s.setSpan(new com.example.egyICUlabs.egyicu.TypefaceSpan(this, "GESS.OTF"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);



        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new ImageAdapter(this,1));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                switch (position)
                {
                    case 0:
                        Intent icuIntent = new Intent(ICU_Submenue.this , ICU_Form.class);
                        startActivity(icuIntent);
                        break;
                    case 1:
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
                    case 2:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkSelfPermission("android.permission.CALL_PHONE") == getPackageManager().PERMISSION_DENIED)
                                requestPermissions(new String[]{"android.permission.CALL_PHONE"}, reqCode);
                            else {
                                String uri = getResources().getString(R.string.ICU_DOCTOR2); ;
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse(uri));
                                startActivity(intent);
                            }
                        }else {
                            String uri = getResources().getString(R.string.ICU_DOCTOR2);
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
                }
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hadana__submenu, menu);
        return true;
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

}
