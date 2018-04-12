package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuDetail;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuRowAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Foodstuff;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.MenuItem;

public class Gui {

    private View mView;

    Activity mActivity;
    ListAdapter mListAdapter;
    private ListView mLwIngredients;

    private PopupWindow mPopUpAddIngredient;

    private FloatingActionButton mFabAddIngredient;
    private Spinner spinnerFoodstuff;
    private EditText editTextQuantity;

    private ConstraintLayout mClMenuDetail;
    private TextView mLblCalories;

    public Gui(View myView, Activity myActivity) {
        mView = myView;
        
        FindControls();
        
        mActivity = myActivity;


        //mActivity =  (Activity) (mView.getContext());
        //mContext = mActivity;
    }

    public Spinner getSpinnerFoodstuff() {
        return spinnerFoodstuff;
    }

    public void setSpinnerFoodstuff(Spinner spinnerFoodstuff)
    {
        this.spinnerFoodstuff = spinnerFoodstuff;
    }

    public EditText getEditTextQuantity() {
        return editTextQuantity;
    }

    public void setEditTextQuantity(EditText editTextQuantity)
    {
        this.editTextQuantity = editTextQuantity;
    }

    public FloatingActionButton getmFabAddIngredient()
    {
        return mFabAddIngredient;
    }

    public PopupWindow getmPopUpAddIngredient()
    {
        return mPopUpAddIngredient;
    }

    public void setmPopUpAddIngredient(PopupWindow popUpAddIngredient)
    {
        mPopUpAddIngredient = popUpAddIngredient;
    }

    public View getView()
    {
        return mView;
    }

    private void FindControls() {
        mLwIngredients = mView.findViewById(R.id.LwMenuEntries);
        mFabAddIngredient = mView.findViewById(R.id.fab_add_ingredient);
        mClMenuDetail = mView.findViewById(R.id.clMenuDetail);
        mLblCalories = mView.findViewById(R.id.LblMenuTotalCalories);
    }



    public ListView getListViewIngredients() {return mLwIngredients;}
    
    public TextView getLblMenuTotalCalories() { return mLblCalories; }

    public void setSnackbar(String text) {
        Snackbar.make(mView, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
