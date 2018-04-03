package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Home;

import android.content.Intent;
import android.util.Log;

import static java.lang.Math.round;

public class ApplicationLogic {


     /*
     ******************
     */

    private int usedLimit = 2950;
    private int DailyLimit = 3000;

     /*
     ******************
      */


    private Gui mGui;

    public ApplicationLogic (Gui gui) {
        mGui = gui;
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
        //mGui.getExampleButton().setOnClickListener(cl);
    }

    public void onExampleButtonClicked() {
    }

    public void onExampleTextViewClicked() {
    }

    public void onSeekBarChanged(int value) {

    }



    public void changeBarValue() {


        double usedSize = (double) usedLimit/DailyLimit*360;
        Log.d("ApplicationLogic", String.valueOf(usedSize));
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

}