package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.NavigationActivity;

public class Init extends FragmentActivity {


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

    }

    private void initGUI () {
        mGui = new Gui(this);
    }

    private void initApplicationLogic () {
        mApplicationLogic = new ApplicationLogic( mGui);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        // mData.saveDataInBundle(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        mApplicationLogic.onActivityReturned(requestCode, resultCode, data);
    }

}
