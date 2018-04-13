package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Statistik;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {

    private FloatingActionButton mPlus;
    private View mView;

    Context mContext;
    Activity mActivity;
    ListAdapter mListAdapter;

    private ListView mLwEntries;
    private TextView mLblDate;
    private TextView mLblCaloriesToday;
    private TextView mLblCaloriesAvgSeven;
    private TextView mLblCaloriesAvgFourteen;
    private TextView mLblCaloriesAvgThirty;
    private FloatingActionButton mFabAddEntry;

    public Gui(View view) {
        mView = view;

        mLwEntries = view.findViewById(R.id.lw_KtEntries);
        mLblDate = view.findViewById(R.id.lblDate);

        mLblCaloriesToday = view.findViewById(R.id.lbl_calories_today);
        mLblCaloriesAvgSeven =  view.findViewById(R.id.lbl_calories_avg_seven);
        mLblCaloriesAvgFourteen = view.findViewById(R.id.lbl_calories_avg_fourteen);
        mLblCaloriesAvgThirty = view.findViewById(R.id.lbl_calories_avg_thirty);

        //mFabAddEntry = view.findViewById(R.id.fab_add_ingredient); //TODO add button to view
    }

    public ListView getListViewEntries() {
        return mLwEntries;
    }

    public TextView getmLblDate() {
        return mLblDate;
    }

    public TextView getmLblCaloriesToday() {
        return mLblCaloriesToday;
    }

    public TextView getmLblCaloriesAvgSeven() {
        return mLblCaloriesAvgSeven;
    }

    public TextView getmLblCaloriesAvgFourteen() {
        return mLblCaloriesAvgFourteen;
    }

    public TextView getmLblCaloriesAvgThirty() {
        return mLblCaloriesAvgThirty;
    }


}

