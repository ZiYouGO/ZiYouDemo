package com.example.bear4515.gui_demo.Content;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.bear4515.gui_demo.R;
import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MapActivity extends Activity {

    RelativeLayout Parent;
    PanelBom panelBom;
    RelativeLayout.LayoutParams parentParams;
    RelativeLayout.LayoutParams paneBomParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parent = new RelativeLayout(getApplicationContext());
        panelBom = new PanelBom(getApplicationContext());
        parentParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        paneBomParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 150);
        paneBomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        paneBomParams.bottomMargin = -80;
        Parent.addView(panelBom, paneBomParams);
        setContentView(Parent, parentParams);
    }
}
