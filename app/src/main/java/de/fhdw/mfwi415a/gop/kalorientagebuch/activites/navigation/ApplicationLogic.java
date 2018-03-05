package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation;

/**
 * Created by joel on 05.03.18.
 */

import android.app.Activity;
import android.content.Intent;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.*;
import  de.fhdw.mfwi415a.gop.kalorientagebuch.activites.constants.Constants;

public class ApplicationLogic {

    private Data mData;
    private Gui mGui;

    public ApplicationLogic (Data data, Gui gui) {
        mData = data;
        mGui = gui;
        initGui();
        initListener();
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
        mGui.getExampleButton().setOnClickListener(cl);
    }

    public void onExampleButtonClicked() {
    }

    public void onExampleTextViewClicked() {
    }

    public void onSeekBarChanged(int value) {

    }

    public void onActivityReturned(int requestCode, int resultCode, Intent data) {
        int value;
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
        }
    }

}
