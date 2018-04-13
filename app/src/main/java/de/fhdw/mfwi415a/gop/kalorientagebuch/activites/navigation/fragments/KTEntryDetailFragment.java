package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.KTEntryDetail.ApplicationLogic;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.KTEntryDetail.Gui;

public class KTEntryDetailFragment extends Fragment {

    ApplicationLogic appLogic;
    Gui mGui;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        Bundle args = getArguments();

        if(!args.containsKey("KT_ID"))
            return null;

        int entryId = (int)args.get("KT_ID");

        View view = inflater.inflate(R.layout.kt_entry_detail, container, false);

        Activity activity = getActivity();

        mGui = new Gui(view);

        appLogic = new ApplicationLogic(mGui, activity, entryId);

        activity.setTitle(appLogic.getEntryName());

        return view;
    }
}
