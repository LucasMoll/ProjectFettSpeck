package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation;

/**
 * Created by joel on 05.03.18.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.Data;

public class Init extends Activity {

    private Data mData;
    private Gui mGui;
    private ApplicationLogic mApplicationLogic;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
        initGUI();
        initApplicationLogic();
    }

    private void initData (Bundle savedInstanceState) {
        mData = new Data(savedInstanceState, this);
    }

    private void initGUI () {
        mGui = new Gui(this);
    }

    private void initApplicationLogic () {
        mApplicationLogic = new ApplicationLogic(mData, mGui);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        mData.saveDataInBundle(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        mApplicationLogic.onActivityReturned(requestCode, resultCode, data);
    }

}

