package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Home;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.StatistikFragment;


public class ApplicationLogic {


     /*
     ******************
     */

    private int usedLimit = 1736;
    private int DailyLimit = 3000;

     /*
     ******************
      */


    private Gui mGui;
    private Context mContext;

    public ApplicationLogic (Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
        changeBarValue();
    }

    private void initGui() {
        int value;

        // initialize view attributes

        // mGui.getSeekBar().setMax(0xFF);
        // mGui.getSeekBar().setProgress(mData.getValueOfSelectedColorPart());

        // ...
    }

    private void initListener() {
        ClickListener cl;

        cl = new ClickListener(this);
        mGui.getmHomePlusFab().setOnClickListener(cl);
    }

    public void onExampleButtonClicked() {
    }

    public void onExampleTextViewClicked() {
    }

    public void onSeekBarChanged(int value) {

    }



    public void changeBarValue() {


        double usedSize = (double) usedLimit/DailyLimit*360;
        mGui.setmUsedBarSizeAndText(usedSize,  (usedLimit + " kcal"));
        mGui.setmUnusedBarSizeAndText(360 - usedSize, (DailyLimit - usedLimit) + " kcal");
    }

        public void onActivityReturned(int requestCode, int resultCode, Intent data) {
       /* int value;
        long valueLong;

        if ( resultCode == Activity.RESULT_OK ) {
            switch ( requestCode ) {
                case Constants.REQUESTCODEONE:
                    value = data.getIntExtra(Constants.KEYEXAMPLEINTVALUE, mData.getExampleIntValue());
                    // set data
                    // set gui
                    break;
                case Constants.REQUESTCODETWO:
                    valueLong = data.getLongExtra(Constants.KEYEXAMPLELONGVALUE, mData.getExampleLongValue());
                    // set data
                    // set gui
                    break;
            }
        }*/
    }



    public void onPlusFabClicked() {
        //mGui.setSnackbar("Replace with your own action");
        changeFragment();

    }

    public void changeFragment(){
        Activity activity = (Activity) mContext;
        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new StatistikFragment()).commit();
    }






}