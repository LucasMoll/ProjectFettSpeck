package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Home;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {

    private TextView mUsedBar;
    private TextView mUnusedBar;
    private TextView mMotivationText;
    private double mDensity;
    private FloatingActionButton mPlus;
    private View mView;
    private ListView mListView;

   /* public Gui (Init activity) {
        activity.setContentView(R.layout.home_layout);

        mUsedBar = (TextView) activity.findViewById(R.id.home_usedBar);
        mUnusedBar = (TextView) activity.findViewById(R.id.home_unusedBar);


    }*/

    public Gui(View myView, double i) {
        mUsedBar = (TextView) myView.findViewById(R.id.home_usedBar);
        mUnusedBar = (TextView) myView.findViewById(R.id.home_unusedBar);
        mPlus = (FloatingActionButton) myView.findViewById(R.id.home_plus_fab);
        mListView = (ListView) myView.findViewById(R.id.home_listView);
        mMotivationText = (TextView) myView.findViewById(R.id.home_motivation_text);
        mDensity = i;
        mView = myView;
    }


    // getter to access views

    public FloatingActionButton getmHomePlusFab() {
        return mPlus;
    }

    public ListView getmListView() {
        return mListView;
    }

    public TextView getmMotivationText() {
        return mMotivationText;
    }

    // methods to change view attributes

    public void setmUsedBarSizeAndText(double size, String text) {
        if (size > 360) {
            size = 360;
        } else if (size < 0) {
            size = 0;
        }
        size = size * mDensity;
        double size_height = (double) 40 * mDensity;
        mUsedBar.setLayoutParams(new LinearLayout.LayoutParams((int) size, (int) size_height));
        mUsedBar.setText(text);

    }

    public void setmUnusedBarSizeAndText(double size, String text) {
        if (size > 360) {
            size = 360;
        } else if (size < 0) {
            size = 0;
        }
        size = size * mDensity;
        double size_height = (double) 40 * mDensity;
        mUnusedBar.setLayoutParams(new LinearLayout.LayoutParams((int) size, (int) size_height));

        mUnusedBar.setText(text);
    }


    public void setSnackbar(String text) {
        Snackbar.make(mView, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


}

