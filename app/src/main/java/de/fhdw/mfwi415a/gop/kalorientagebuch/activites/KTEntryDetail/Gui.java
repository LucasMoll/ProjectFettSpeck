package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.KTEntryDetail;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
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
    private TextView mLblKtEntryTotalCalories;

    private FloatingActionButton mFabDuplicate;
    private FloatingActionButton mFabDelete;

    public Gui(View view) {
        mView = view;

        mLwEntries = view.findViewById(R.id.lw_KtEntries_detail);

        mLblKtEntryTotalCalories = view.findViewById(R.id.LblKtEntryTotalCalories);

        mFabDuplicate = view.findViewById(R.id.fab_duplicate_entry);
        mFabDelete = view.findViewById(R.id.fab_delete_entry);
    }

    public ListView getListViewEntries() {
        return mLwEntries;
    }

    public TextView getmLblKtEntryTotalCalories() {
        return mLblKtEntryTotalCalories;
    }

    public FloatingActionButton getFabDuplicate() {
        return mFabDuplicate;
    }

    public FloatingActionButton getFabDelete() {
        return mFabDelete;
    }
}

