package com.example.egyICUlabs.egyicu;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ICU_Form extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_GET = 2;
    static final int RESULT_OK = -1;
    static final String HAS_PHOTO = "hasPhoto";
    static final String NO_PHOTO = "noPhoto";

    final String PATIENT_NAME = "name";
    final String PATIENT_AGE = "age";
    final String PATIENT_PHONE = "phone";
    final String PATIENT_REGION = "region";
    final String CONTRACT_KEY = "contract";
    final String CONTRACT_TEXT = "cantractText";
    final String ACCIDENT_KEY = "accident";
    final String SURGERY_KEY = "surgery";
    final String RESPIRATORY_KEY = "respiratory";
    final String KIDNY_KEY = "kidney";
    final String NEUROLOGY_KEY = "neurology";
    final String LIVER_KEY = "liver";
    final String CARDIAC_KEY = "cardiac";
    final String ISOLATION_KEY = "isolation";
    final String DO_NOT_KNOW_KEY = "idk";

    int currentVersion;
    int reqCode =0;
    boolean reqFlag = false;

    File photoFile = null;

    String mCurrentPhotoPath;
    Uri selectedImageUri;

    Button submit_button;
    EditText patient_name, phone,region , age , contract;
    String patient_name_text, phone_text,region_text, age_text , contract_text;

    CheckBox accident_checkbox , respiratory_checkbox , surgery_checkbox , kidney_checkbox , neurology_checkbox ,
            liver_checkbox , cardiac_checkbox, isolation_checkbox , do_not_know_checkbox ;
    RadioButton contract_yes , contract_no;
    ImageView imageView;

    boolean accident_flag = false , surgery_flag = false, respiratory_flag = false, kidney_flag=false , neurology_flag = false ,
            liver_flag = false , cardiac_flag = false , isolation_flag = false , do_not_know_flag = false;

    View.OnClickListener checkbox_listener= new View.OnClickListener(){
        @Override
        public void onClick(View view)
        {
            boolean checked = ((CheckBox) view).isChecked();
            switch (view.getId())
            {
                case R.id.accident_checkbox:
                    accident_flag = checked?true : false;
                    break;
                case R.id.surgery_checkbox:
                    surgery_flag = checked? true : false;
                    break;
                case R.id.respiratory_checkbox:
                    respiratory_flag = checked? true : false;
                    break;
                case R.id.kidney_checkbox:
                    kidney_flag = checked? true : false;
                    break;
                case R.id.neurology_checkbox:
                    neurology_flag = checked? true : false;
                    break;
                case R.id.liver_checkbox:
                    liver_flag = checked? true : false;
                    break;
                case R.id.cardiac_checkbox:
                    cardiac_flag = checked? true : false;
                    break;
                case R.id.isolation_checkbox:
                    isolation_flag = checked? true : false;
                    break;
                case R.id.do_not_know_checkbox:
                    do_not_know_flag = checked? true : false;
                    break;
            }
        }
    };
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icu__form);
        currentVersion = Build.VERSION.SDK_INT;
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //getSupportActionBar().hide();

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(R.string.enter_data);
//        toolbar.setAlpha(0.85f);


        patient_name = (EditText)findViewById(R.id.patient_name_text);
        age = (EditText)findViewById(R.id.age_text);
        phone = (EditText)findViewById(R.id.phone_text);
        region = (EditText)findViewById(R.id.region_text);


        accident_checkbox = (CheckBox)findViewById(R.id.accident_checkbox);
        surgery_checkbox = (CheckBox)findViewById(R.id.surgery_checkbox);
        respiratory_checkbox = (CheckBox)findViewById(R.id.respiratory_checkbox);
        kidney_checkbox = (CheckBox)findViewById(R.id.kidney_checkbox);
        neurology_checkbox = (CheckBox)findViewById(R.id.neurology_checkbox);
        liver_checkbox = (CheckBox)findViewById(R.id.liver_checkbox);
        cardiac_checkbox = (CheckBox)findViewById(R.id.cardiac_checkbox);
        isolation_checkbox = (CheckBox)findViewById(R.id.isolation_checkbox);
        do_not_know_checkbox = (CheckBox)findViewById(R.id.do_not_know_checkbox);

        contract_yes = (RadioButton)findViewById(R.id.contract_yes);
        contract_no = (RadioButton)findViewById(R.id.contract_no);
        contract = (EditText) findViewById(R.id.contract_text);

        imageView = (ImageView)findViewById(R.id.imageView);

        submit_button = (Button)findViewById(R.id.submit_button);


        patient_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                patient_name_text = patient_name.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                age_text = age.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone_text=phone.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        region.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                region_text = region.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });



        contract.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contract_text = contract.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        accident_checkbox.setOnClickListener(checkbox_listener);
        surgery_checkbox.setOnClickListener(checkbox_listener);
        respiratory_checkbox.setOnClickListener(checkbox_listener);
        kidney_checkbox.setOnClickListener(checkbox_listener);
        neurology_checkbox.setOnClickListener(checkbox_listener);
        liver_checkbox.setOnClickListener(checkbox_listener);
        cardiac_checkbox.setOnClickListener(checkbox_listener);
        isolation_checkbox.setOnClickListener(checkbox_listener);
        do_not_know_checkbox.setOnClickListener(checkbox_listener);

        contract_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contract.setVisibility(View.VISIBLE);
                contract_no.setChecked(false);
            }
        });

        contract_no.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                contract.setVisibility(View.INVISIBLE);
                contract_yes.setChecked(false);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((((ImageView)v).getTag()) != HAS_PHOTO)
                {
                    new AlertDialog.Builder(ICU_Form.this)
                            .setTitle(R.string.img_dialog_title)
                            .setMessage(R.string.img_dialog_text)
                            .setPositiveButton(R.string.camera, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    if (intent.resolveActivity(getApplication().getPackageManager()) != null) {
                                        //File photoFile = null;
                                        try {
                                            photoFile = createImageFile();
                                        } catch (IOException ex) {
                                            // Error occurred while creating the File
                                            Toast.makeText(getApplication(), "Error creating file", Toast.LENGTH_SHORT).show();
                                        }
                                        if(photoFile != null)
                                        {
                                            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                                    Uri.fromFile(photoFile));
                                            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                                        }
                                    }
                                }
                            })
                            .setNegativeButton(R.string.gallery, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            if(checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE")==getPackageManager().PERMISSION_DENIED)
                                                requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"},reqCode);
                                            else{
                                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                                intent.setType("image/*");
                                                if (intent.resolveActivity(getApplication().getPackageManager()) != null) {
                                                    try {
                                                        photoFile = createImageFile();
                                                    } catch (IOException e) {
                                                        Toast.makeText(getApplication(), "Error creating file", Toast.LENGTH_SHORT).show();
                                                    }
                                                    if (photoFile != null) {
                                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                                                        startActivityForResult(intent, REQUEST_IMAGE_GET);
                                                    }
                                                }
                                            }
                                        }else{
                                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                            intent.setType("image/*");
                                            if (intent.resolveActivity(getApplication().getPackageManager()) != null) {
                                                try {
                                                    photoFile = createImageFile();
                                                } catch (IOException e) {
                                                    Toast.makeText(getApplication(), "Error creating file", Toast.LENGTH_SHORT).show();
                                                }
                                                if (photoFile != null) {
                                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                                                    startActivityForResult(intent, REQUEST_IMAGE_GET);
                                                }
                                            }
                                        }
                                    }
                            })
                            .setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setOnKeyListener(new DialogInterface.OnKeyListener() {
                                @Override
                                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                                        dialog.dismiss();
                                    }
                                    return false;
                                }
                            })
                            .show();
                }
                else
                {
                    new AlertDialog.Builder(ICU_Form.this)
                            .setTitle(R.string.img_dialog_title)
                            .setMessage(R.string.img_dialog_text)
                            .setPositiveButton(R.string.camera, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    if (intent.resolveActivity(getApplication().getPackageManager()) != null) {
                                        //File photoFile = null;
                                        try {
                                            photoFile = createImageFile();
                                        } catch (IOException ex) {
                                            // Error occurred while creating the File
                                            Toast.makeText(getApplication(), "Error creating file", Toast.LENGTH_SHORT).show();
                                        }
                                        if(photoFile != null)
                                        {
                                            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                                    Uri.fromFile(photoFile));
                                            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                                        }
                                    }
                                }
                            })
                            .setNegativeButton(R.string.gallery, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                    intent.setType("image/*");
                                    if (intent.resolveActivity(getApplication().getPackageManager()) != null) {
                                        try {
                                            photoFile = createImageFile();
                                        } catch (IOException e) {
                                            Toast.makeText(getApplication(), "Error creating file", Toast.LENGTH_SHORT).show();
                                        }
                                        if (photoFile != null) {
                                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                                            startActivityForResult(intent, REQUEST_IMAGE_GET);
                                        }
                                    }
                                }
                            })
                            .setNeutralButton(R.string.remove, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    imageView.setImageResource(R.drawable.attach);
                                    imageView.setTag(NO_PHOTO);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });


        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(validateData()) {
                    new AlertDialog.Builder(ICU_Form.this)
                            .setTitle(R.string.sms_dialog_title)
                            .setMessage(R.string.sms_dialog_text)
                            .setPositiveButton(R.string.SMS_EMAIL, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        if (checkSelfPermission("android.permission.SEND_SMS") == getPackageManager().PERMISSION_DENIED)
                                            requestPermissions(new String[]{"android.permission.SEND_SMS"}, reqCode);
                                        else {
                                            utilities.sendSMS(getString(R.string.ICU_SMS), formatSMS1(), ICU_Form.this);
                                            utilities.sendSMS(getString(R.string.ICU_SMS), formatSMS2(), ICU_Form.this);
                                        }
                                    }else
                                    {
                                        utilities.sendSMS(getString(R.string.ICU_SMS), formatSMS1(), ICU_Form.this);
                                        utilities.sendSMS(getString(R.string.ICU_SMS), formatSMS2(), ICU_Form.this);
                                    }

                                    Intent emailIntent = new Intent();
                                    emailIntent.setAction(Intent.ACTION_SEND);
                                    emailIntent.setType("image/jpeg");
                                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.ICU_MAIL)});
                                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ICU");
                                    emailIntent.putExtra(Intent.EXTRA_TEXT, formatMail());
                                    if (((imageView.getTag()).equals(HAS_PHOTO))) {
                                        emailIntent.putExtra(Intent.EXTRA_STREAM, selectedImageUri);
                                    }
                                    PackageManager pm = getPackageManager();
                                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                                    sendIntent.setType("image/jpeg");


                                    Intent openInChooser = Intent.createChooser(emailIntent, "send mail..");

                                    List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
                                    List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
                                    for (int i = 0; i < resInfo.size(); i++) {
                                        // Extract the label, append it, and repackage it in a LabeledIntent
                                        ResolveInfo ri = resInfo.get(i);
                                        String packageName = ri.activityInfo.packageName;
                                        if (packageName.contains("android.email")) {
                                            emailIntent.setPackage(packageName);
                                        } else if(packageName.contains("android.gm")) {
                                            Intent intent = new Intent();
                                            intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                                            intent.setAction(Intent.ACTION_SEND);
                                            intent.setType("image/jpeg");
                                            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.ICU_MAIL)});
                                            intent.putExtra(Intent.EXTRA_SUBJECT, "ICU");
                                            intent.putExtra(Intent.EXTRA_TEXT, formatMail());
                                            if (((imageView.getTag()).equals(HAS_PHOTO))) {
                                                intent.putExtra(Intent.EXTRA_STREAM, selectedImageUri);
                                            }
                                            intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
                                        }
                                    }

                                    // convert intentList to array
                                    LabeledIntent[] extraIntents = intentList.toArray( new LabeledIntent[ intentList.size() ]);

                                    openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
                                    startActivity(openInChooser);

                                }
                            })
                            .setNegativeButton(R.string.EMAIL_only, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                        //Resources resources = getResources();

                                        Intent emailIntent = new Intent();
                                        emailIntent.setAction(Intent.ACTION_SEND);
                                        emailIntent.setType("image/jpeg");
                                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.ICU_MAIL)});
                                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ICU");
                                        emailIntent.putExtra(Intent.EXTRA_TEXT, formatMail());
                                        if (((imageView.getTag()).equals(HAS_PHOTO))) {
                                            emailIntent.putExtra(Intent.EXTRA_STREAM, selectedImageUri);
                                        }
                                        PackageManager pm = getPackageManager();
                                        Intent sendIntent = new Intent(Intent.ACTION_SEND);
                                        sendIntent.setType("image/jpeg");


                                        Intent openInChooser = Intent.createChooser(emailIntent, "send mail..");

                                        List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
                                        List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
                                        for (int i = 0; i < resInfo.size(); i++) {
                                            // Extract the label, append it, and repackage it in a LabeledIntent
                                            ResolveInfo ri = resInfo.get(i);
                                            String packageName = ri.activityInfo.packageName;
                                            if (packageName.contains("android.email")) {
                                                emailIntent.setPackage(packageName);
                                            } else if(packageName.contains("android.gm")) {
                                                Intent intent = new Intent();
                                                intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                                                intent.setAction(Intent.ACTION_SEND);
                                                intent.setType("image/jpeg");
                                                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.ICU_MAIL)});
                                                intent.putExtra(Intent.EXTRA_SUBJECT, "ICU");
                                                intent.putExtra(Intent.EXTRA_TEXT, formatMail());
                                                if (((imageView.getTag()).equals(HAS_PHOTO))) {
                                                    intent.putExtra(Intent.EXTRA_STREAM, selectedImageUri);
                                                }
                                                intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
                                            }
                                        }

                                        // convert intentList to array
                                        LabeledIntent[] extraIntents = intentList.toArray( new LabeledIntent[ intentList.size() ]);

                                        openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
                                        startActivity(openInChooser);
                                }
                            })
                            .setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {
                    new AlertDialog.Builder(ICU_Form.this)
                            .setTitle(R.string.warning_dialog_title)
                            .setMessage(R.string.warning_dialog_text)
                            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });
    }

    @Override

    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){
        if (permissions[0].equals("android.permission.SEND_SMS")) {
            switch (permsRequestCode) {
                case 0:
                    utilities.sendSMS(getString(R.string.ICU_SMS), formatSMS1(), ICU_Form.this);
                    utilities.sendSMS(getString(R.string.ICU_SMS), formatSMS2(), ICU_Form.this);
                    break;
                default:
                    Toast.makeText(getApplication(), "Please allow sending SMS", Toast.LENGTH_LONG).show();
            }
        }
        else if(permissions[0].equals("android.permission.WRITE_EXTERNAL_STORAGE"))
        {
            switch(permsRequestCode) {
            case 0:
                reqFlag = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getApplication().getPackageManager()) != null) {
                    try {
                        photoFile = createImageFile();
                    } catch (IOException e) {
                        Toast.makeText(getApplication(), "Error creating file", Toast.LENGTH_SHORT).show();
                    }
                    if (photoFile != null) {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        startActivityForResult(intent, REQUEST_IMAGE_GET);
                    }
                }
                break;
            default:
                Toast.makeText(getApplication(), "Please allow access in order to attach file", Toast.LENGTH_LONG).show();
        }
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(PATIENT_NAME, patient_name_text);
        savedInstanceState.putString(PATIENT_AGE, age_text);
        savedInstanceState.putString(PATIENT_PHONE, phone_text);
        savedInstanceState.putString(PATIENT_REGION, region_text);
        savedInstanceState.putBoolean(CONTRACT_KEY, contract_yes.isChecked());
        if(contract_yes.isChecked())
            savedInstanceState.putString(CONTRACT_TEXT,contract_text);
        savedInstanceState.putBoolean(ACCIDENT_KEY, accident_flag);
        savedInstanceState.putBoolean(SURGERY_KEY, surgery_flag);
        savedInstanceState.putBoolean(RESPIRATORY_KEY, respiratory_flag);
        savedInstanceState.putBoolean(KIDNY_KEY, kidney_flag);
        savedInstanceState.putBoolean(NEUROLOGY_KEY, neurology_flag);
        savedInstanceState.putBoolean(LIVER_KEY, liver_flag);
        savedInstanceState.putBoolean(CARDIAC_KEY, cardiac_flag);
        savedInstanceState.putBoolean(ISOLATION_KEY, isolation_flag);
        savedInstanceState.putBoolean(DO_NOT_KNOW_KEY, do_not_know_flag);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        patient_name_text = savedInstanceState.getString(PATIENT_NAME);
        patient_name.setText(patient_name_text);
        age_text = savedInstanceState.getString(PATIENT_AGE);
        age.setText(age_text);
        phone_text = savedInstanceState.getString(PATIENT_PHONE);
        phone.setText(phone_text);
        region_text = savedInstanceState.getString(PATIENT_REGION);
        region.setText(region_text);
        contract_yes.setChecked(savedInstanceState.getBoolean(CONTRACT_KEY));
        contract_no.setChecked(!savedInstanceState.getBoolean(CONTRACT_KEY));
        if(savedInstanceState.getBoolean(CONTRACT_KEY))
        {
            contract.setVisibility(View.VISIBLE);
            contract.setText(savedInstanceState.getString(CONTRACT_TEXT));
        }
        accident_flag = savedInstanceState.getBoolean(ACCIDENT_KEY);
        accident_checkbox.setChecked(accident_flag);
        surgery_flag = savedInstanceState.getBoolean(SURGERY_KEY);
        surgery_checkbox.setChecked(surgery_flag);
        respiratory_flag = savedInstanceState.getBoolean(RESPIRATORY_KEY);
        respiratory_checkbox.setChecked(respiratory_flag);
        kidney_flag = savedInstanceState.getBoolean(KIDNY_KEY);
        kidney_checkbox.setChecked(kidney_flag);
        neurology_flag = savedInstanceState.getBoolean(NEUROLOGY_KEY);
        neurology_checkbox.setChecked(neurology_flag);
        liver_flag = savedInstanceState.getBoolean(LIVER_KEY);
        liver_checkbox.setChecked(liver_flag);
        cardiac_flag = savedInstanceState.getBoolean(CARDIAC_KEY);
        cardiac_checkbox.setChecked(cardiac_flag);
        isolation_flag = savedInstanceState.getBoolean(ISOLATION_KEY);
        isolation_checkbox.setChecked(isolation_flag);
        do_not_know_flag = savedInstanceState.getBoolean(DO_NOT_KNOW_KEY);
        do_not_know_checkbox.setChecked(do_not_know_flag);
    }

    public boolean validateData()
    {
        if (patient_name_text==null || patient_name_text.equals("") || age_text == null || age_text.equals("")
                || phone_text ==null || phone_text.equals("") || region_text ==null || region_text.equals("") ||
                ((contract_text==null || contract_text.equals("")) &&contract_yes.isChecked()))
            return false;
        if(!accident_flag && !surgery_flag && !respiratory_flag && !kidney_flag && !neurology_flag && !liver_flag && !cardiac_flag
                && !isolation_flag && !do_not_know_flag)
            return false;
        return true;
    }

    public String formatSMS1()
    {
        String sms = "ICU\n" + patient_name_text + "," + region_text + ","
                + (contract_yes.isChecked()? contract_text + "," :"");
        return sms;
    }
    public String formatSMS2()
    {
        return age_text + "," + phone_text + "," + (accident_flag? "accident,":"")
                + (surgery_flag? "surgery,":"") + (respiratory_flag? "ventilator,":"")
                + (kidney_flag? "kidney,":"") + (neurology_flag? "neurology,":"")
                + (liver_flag? "liver,":"") + (cardiac_flag? "cardiac,":"")
                + (isolation_flag? "isolation,":"") + (do_not_know_flag? "IDK":"");
    }

    public String formatMail()
    {
        String mail = "name: " + patient_name_text + "\n" + "age: " + age_text + "\n"
                +"phone: " + phone_text + "\n" + "region: " + region_text + "\n"
                +"contract: " + (contract_yes.isChecked()?"yes":"no") + "\n"
                +"accident: " + (accident_flag?"yes":"no") +"\n"
                +"need surgery: " + (surgery_flag?"yes":"no") +"\n"
                +"need ventilator: " + (respiratory_flag?"yes":"no") +"\n"
                +"kidney dialysis: " + (kidney_flag?"yes":"no") +"\n"
                +"neurology: " + (neurology_flag?"yes":"no") +"\n"
                +"liver care: " + (liver_flag?"yes":"no") +"\n"
                +"Cardiac: " + (cardiac_flag?"yes":"no") +"\n"
                +"needs isolation: " + (isolation_flag?"yes":"no") +"\n"
                +"don\'t know: " + (do_not_know_flag?"yes":"no");
        return mail;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            String fname=photoFile.getAbsolutePath();
            ////////////////
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fname,options);
            int height = options.outHeight;
            int width = options.outWidth;
            int inSampleSize =1;
            while (height > 200 || width > 200)
            {
                height /=2;
                width  /=2;
                inSampleSize *= 2;
            }
            options.inSampleSize = inSampleSize;
            options.inJustDecodeBounds = false;
            Bitmap imageBitmap = BitmapFactory.decodeFile(fname,options);
            ///////////////
            //Bitmap imageBitmap = BitmapFactory.decodeFile(fname,options);
            selectedImageUri = Uri.parse(mCurrentPhotoPath);
            imageView.setImageBitmap(imageBitmap);
            imageView.setTag(HAS_PHOTO);
        }
        else {
            if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            /*String fname = photoFile.getAbsolutePath();
            /////////////////////////////////////
            BitmapFactory.Options galleryOptions = new BitmapFactory.Options();
            galleryOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fname,galleryOptions);
            int height = galleryOptions.outHeight;
            int width = galleryOptions.outWidth;
            int inSampleSize =1;
            while (height > 200 || width > 200)
            {
                height /=2;
                width  /=2;
                inSampleSize *= 2;
            }
            galleryOptions.inSampleSize = inSampleSize;
            galleryOptions.inJustDecodeBounds = false;
            Bitmap galleryBitmap = BitmapFactory.decodeFile(fname, galleryOptions);
            selectedImageUri = Uri.parse(mCurrentPhotoPath);
            //mCurrentPhotoPath = getRealPathFromURI(ICU_Form.this,selectedImageUri);
            imageView.setImageBitmap(galleryBitmap);
            imageView.setTag(HAS_PHOTO);*/
                ////////////////////////////////////
                selectedImageUri = data.getData();
                imageView.setImageURI(selectedImageUri);
                imageView.setTag(HAS_PHOTO);
                //if(currentVersion == Build.VERSION_CODES.KITKAT)
                //    selectedImageUri = FileProvider.getUriForFile(ICU_Form.this, "com.myfileprovider", photoFile);

                ///////////////////////////////////
            }
        }
    }

    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}


