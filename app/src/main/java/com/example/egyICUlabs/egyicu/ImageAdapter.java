package com.example.egyICUlabs.egyicu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ahmed on 25/08/2015.
 */
public class ImageAdapter extends BaseAdapter {


    private Context mContext;
    private int mType;
    private static LayoutInflater inflater=null;

    public ImageAdapter(Context c,int type) {
        mContext = c;
        mType= type;
        inflater = ( LayoutInflater )mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return count[mType];
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    // create a new ImageView for each item referenced by the Adapter


    public View getView(int position, final View convertView, ViewGroup parent) {

        View grid;



        if (convertView == null) {
            // if it's not recycled, initialize some attributes



            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext
                    .getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);






            grid = new View(mContext);
            grid = inflater.inflate(R.layout.row_grid, parent ,false);

          //  grid.setLayoutParams(new GridView.LayoutParams((int) (metrics.widthPixels/2), (int) (metrics.widthPixels/2)));

            grid.setBackgroundColor(Color.parseColor(colors[4]));
            TextView textView = (TextView) grid.findViewById(R.id.item_text);
            ImageView imageView = (ImageView) grid.findViewById(R.id.item_image);
            ImageView arrow = (ImageView) grid.findViewById(R.id.arrow);
            if(mType != 0)
            {
                if(call[mType-1][position])
                arrow.setImageResource(R.drawable.call);
            }


        //   grid.setLayoutParams(new GridView.LayoutParams(metrics.widthPixels / 2, (int) (metrics.widthPixels / 2)));






            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(0, 0, 0, 0);
            textView.setTextColor(Color.WHITE);
            Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(), "fonts/GESS.ttf");
            textView.setTypeface(custom_font);

            switch(mType)
            {
                case 0:
                    imageView.setImageResource(mThumbIds0[position]);
                    textView.setText(results0[position]);
                    break;
                case 1:
                    imageView.setImageResource(mThumbIds1[position]);
                    textView.setText(results1[position]);
                    break;
                case 2:
                    imageView.setImageResource(mThumbIds2[position]);
                    textView.setText(results2[position]);
                    break;
                case 3:
                    imageView.setImageResource(mThumbIds3[position]);
                    textView.setText(results3[position]);
                    break;
                case 4:
                    imageView.setImageResource(mThumbIds4[position]);
                    textView.setText(results4[position]);
                    break;
                case 6:
                    imageView.setImageResource(mThumbIds6[position]);
                    textView.setText(results6[position]);
                    break;

            }



        } else {
            grid = convertView;
        }




        return grid;
    }

    // references to our images



//
//    private Integer[] mThumbIds0 = {
//            R.drawable.s1, R.drawable.s2,
//            R.drawable.s3, R.drawable.s4,
//            R.drawable.s5, R.drawable.s6
//
//    };
//
//    private Integer[] mThumbIds1 = {
//            R.drawable.s7, R.drawable.s1,
//            R.drawable.s6
//
//    };
//
//    private Integer[] mThumbIds3 = {
//            R.drawable.s3, R.drawable.s3
//
//    };
//
//    private Integer[] mThumbIds4 = {
//            R.drawable.s6, R.drawable.s7
//
//    };
//    private Integer[] mThumbIds6 = {
//            R.drawable.s1, R.drawable.s2,R.drawable.s4,R.drawable.s7,R.drawable.s8
//
//    };


    private Integer[] mThumbIds0 = {
            R.drawable.ss1, R.drawable.ss2,
            R.drawable.ss3, R.drawable.ss4,
            R.drawable.ss5, R.drawable.ss6

    };

    private Integer[] mThumbIds1 = {
            R.drawable.ss7,
            R.drawable.ss6,
            R.drawable.ss6,
            R.drawable.ss6

    };

    private Integer[] mThumbIds2 = {
            R.drawable.ss9, R.drawable.ss1,
            R.drawable.ss10

    };


    private Integer[] mThumbIds3 = {
            R.drawable.ss3, R.drawable.ss3

    };

    private Integer[] mThumbIds4 = {
            R.drawable.ss7, R.drawable.ss6,
            R.drawable.ss6,
            R.drawable.ss6

    };
    private Integer[] mThumbIds6 = {
            R.drawable.ss1,R.drawable.ss4,R.drawable.ss7,R.drawable.ss8

    };

    private Integer[] results0 = {R.string.intensive_care, R.string.specialized_centers,R.string.ambulance, R.string.home_delivery, R.string.about, R.string.contact

    };

    private Integer[] results1 = {R.string.enter_data,R.string.tabeeb1,R.string.tabeeb2,R.string.tabeeb3

    };

    private Integer[] results2 = {R.string.brain_stroke_unit, R.string.cardiac_unit,R.string.kidney_unit

    };

    private Integer[] results3 = {R.string.public_ambulance, R.string.private_ambulance

    };

    private Integer[] results4 = {R.string.required_service, R.string.tabeeb1,R.string.tabeeb2,R.string.tabeeb3

    };
    private Integer[] results6 = {R.string.intensive_care,R.string.home_delivery,R.string.adminstration,R.string.email_us

    };
    boolean [][] call = {{false,true,true,true},{true,true,true},{true,true},{false,true,true,true},{false},{true,true,true,false}};



    private String[] colors = {"#F1C40F","#1ABC9C","#E74C3C","#3498DB","#80000000", "#9B59B6"

    };

    private int[] count = {mThumbIds0.length,mThumbIds1.length,mThumbIds2.length,mThumbIds3.length,mThumbIds4.length,0,mThumbIds6.length

    };


}
