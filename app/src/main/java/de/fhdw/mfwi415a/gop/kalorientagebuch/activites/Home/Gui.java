package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Home;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {

    private TextView mUsedBar;
    private TextView mUnusedBar;
    private LinearLayout mLinearLayout;
    private double mDesity;

    public Gui (Init activity) {
        activity.setContentView(R.layout.home_layout);

        mUsedBar = (TextView) activity.findViewById(R.id.home_usedBar);
        mUnusedBar = (TextView) activity.findViewById(R.id.home_unusedBar);
        mLinearLayout = (LinearLayout) activity.findViewById(R.id.linearLayout);


    }

    public Gui(View myView, double i) {
        mUsedBar = (TextView) myView.findViewById(R.id.home_usedBar);
        mUnusedBar = (TextView) myView.findViewById(R.id.home_unusedBar);
        mLinearLayout = (LinearLayout) myView.findViewById(R.id.linearLayout);
        mDesity = i;
    }


    // getter to access views



    // methods to change view attributes

    public void setmUsedBarSizeAndText(double size, String text) {
        if (size > 360) {
            size = 360;
        } else if (size < 0) {
            size = 0;
        }
        size = size *mDesity;
        double size_height = (double) 35 * mDesity;
        mUsedBar.setLayoutParams(new LinearLayout.LayoutParams((int) size, (int) size_height));
        mUsedBar.setText(text);

    }

    public void setmUnusedBarSizeAndText(double size, String text) {
        if (size > 360) {
            size = 360;
        } else if (size < 0) {
            size = 0;
        }
        size = size *mDesity;
        double size_height = (double) 35 * mDesity;
        mUnusedBar.setLayoutParams(new LinearLayout.LayoutParams((int) size, (int) size_height));

        mUnusedBar.setText(text);

    }


}

