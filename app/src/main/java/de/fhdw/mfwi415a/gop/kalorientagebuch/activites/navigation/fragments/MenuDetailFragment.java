package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuDetail.AppLogic;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuRowAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Menues.ApplicationLogic;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Menues.Gui;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.Data;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.*;

public class MenuDetailFragment extends Fragment {

    private AppLogic appLogic;
    private de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuDetail.Gui mGui;

    public MenuDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.activity_menu_detail, container, false);

        Bundle args = getArguments();

        int menuId = -1;

        if(args != null)
            menuId = args.getInt("Menue_ID");

        if(menuId == 0)
            return view;

        Activity activity = getActivity();

        mGui = new de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuDetail.Gui(view, getActivity());

        appLogic = new AppLogic(mGui, activity, menuId);

        activity.setTitle(appLogic.get_menuName());

        return view;
    }
}
