package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

public class ApplicationLogic {

    private Button button_name;
    private Button button_hoechstsatz;
    private Button neue_Einheit;
    private Button alle_Einheiten;

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
