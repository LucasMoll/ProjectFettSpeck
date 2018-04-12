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


    public Gui(View myView) {
        mUsedBar = (TextView) myView.findViewById(R.id.home_usedBar);
        mUnusedBar = (TextView) myView.findViewById(R.id.home_unusedBar);
        mPlus = (FloatingActionButton) myView.findViewById(R.id.home_plus_fab);
        mListView = (ListView) myView.findViewById(R.id.home_listView);
        mMotivationText = (TextView) myView.findViewById(R.id.home_motivation_text);
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

    public void setmUsedBarSizeAndText(int size, String text) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = (float) size;
        mUsedBar.setLayoutParams(params);
        mUsedBar.setText(text);

    }

    public void setmUnusedBarSizeAndText(int size, String text) {


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = (float) size;
        mUnusedBar.setLayoutParams(params);
        mUnusedBar.setText(text);
    }


    public void setSnackbar(String text) {
        Snackbar.make(mView, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


}

