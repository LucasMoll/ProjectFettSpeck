package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings;

import android.content.Context;

public class ApplicationLogic {

    private Gui mGui;
    private Context mContext;

    public ApplicationLogic (Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
    }

    private void initGui() {

    }

    private void initListener() {
        ClickListener cl;

        cl = new ClickListener(this);
    }

}
