package com.tangotv.cli;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.netspace.tango.tangotv.R;


public class FocusAnimation implements View.OnFocusChangeListener {
    private Context mContext;
    private View view;
    private  Animation animZoomIn;
    private  Animation animZoomOut;

    public FocusAnimation(Context context) {
        this.mContext = context;
        //this.view = view;
    }



    @Override
    public void onFocusChange(final View view, final boolean isFocused) {
        if (isFocused) {

            view.bringToFront();
            //highlight_movie.invalidate();
            //highlight_series.invalidate();
            //highlight_settings.invalidate();
                int id = view.getId();
                Resources res = view.getResources();
                String resName = res.getResourceEntryName(id);

                Toast toast = Toast.makeText(mContext, resName, Toast.LENGTH_SHORT);
                toast.show();

                viewScale(R.anim.home_layout_scale, view);


            }else {
                viewScale(R.anim.home_layout_scaleout,view);
            }
        }
    private void viewScale( int scaleXmlFile,View view){

        if(scaleXmlFile == R.anim.home_layout_scaleout){
            // load the animation
            animZoomIn = AnimationUtils.loadAnimation(mContext, scaleXmlFile);
            view.startAnimation(animZoomIn);
        }else{
            animZoomOut = AnimationUtils.loadAnimation(mContext, scaleXmlFile);
            view.startAnimation(animZoomOut);
        }

    }
}
