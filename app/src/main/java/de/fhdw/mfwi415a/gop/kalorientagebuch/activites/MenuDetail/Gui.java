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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;

public class Gui {

    private View mView;

    Activity mActivity;
    ListAdapter mListAdapter;
    private ListView mLwIngredients;

    private PopupWindow mPopUpAddIngredient;

    private FloatingActionButton mFabAddIngredient;

    private ConstraintLayout mClMenuDetail;
    private TextView mLblCalories;

    public Gui(View myView, Activity myActivity) {
        mView = myView;
        
        FindControls();
        
        mActivity = myActivity;

        mFabAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopUpAddIngredient();
            }
        });

        //mActivity =  (Activity) (mView.getContext());
        //mContext = mActivity;
    }

    private void FindControls() {
        mLwIngredients = mView.findViewById(R.id.LwMenuEntries);
        mFabAddIngredient = mView.findViewById(R.id.fab_add_ingredient);
        mClMenuDetail = mView.findViewById(R.id.clMenuDetail);
        mLblCalories = mView.findViewById(R.id.LblMenuTotalCalories);
    }

    private void ShowPopUpAddIngredient()
    {
        try {

            LayoutInflater inflater = (LayoutInflater) mView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final View layout = inflater.inflate(R.layout.activity_add_menu_item_pop_up, (ViewGroup)mView.findViewById(R.id.popup_addMenuItem));

            Spinner ingredientsSpinner = layout.findViewById(R.id.spinnerIngredient);
            //Spinner quantityUnitsSpinner = layout.findViewById(R.id.spinnerUnit);

            ArrayList<String> nahrungsmittel = new ArrayList<String>();
            //ArrayList<String> quantityUnits = new ArrayList<String>();

            DataAdapter mDBHelper = new DataAdapter(mView.getContext());
            mDBHelper.createDatabase();
            mDBHelper.open();

            Cursor cNahrungsmittel = mDBHelper.getAllLebensmittel();

            int idxBezeichnung = cNahrungsmittel.getColumnIndex("Bezeichnung");

            cNahrungsmittel.moveToFirst();

            while(!cNahrungsmittel.isAfterLast())

            {
                nahrungsmittel.add(cNahrungsmittel.getString(idxBezeichnung));
                cNahrungsmittel.moveToNext();
            }

            cNahrungsmittel.close();

            /*Cursor cQuantityUnits = mDBHelper.getAllEinheiten();

            idxBezeichnung = cNahrungsmittel.getColumnIndex("Bezeichnung");

            cQuantityUnits.moveToFirst();

            while(!cQuantityUnits.isAfterLast())

            {
                quantityUnits.add(cQuantityUnits.getString(idxBezeichnung));
                cQuantityUnits.moveToNext();
            }

            cQuantityUnits.close();*/

            mDBHelper.close();

            ArrayAdapter<String> nahrungsmittelAdapter = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_spinner_dropdown_item, nahrungsmittel);
            //ArrayAdapter<String> quantityUnitsAdapter = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_spinner_dropdown_item, quantityUnits);

            Button btnCancel = layout.findViewById(R.id.btnCancel);
            Button btnAddIng = layout.findViewById(R.id.btnAddIngredient);

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPopUpAddIngredient.dismiss();
                }
            });

            btnAddIng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Spinner spIngr = layout.findViewById(R.id.spinnerIngredient);
                    String tstring = spIngr.getSelectedItem().toString(); //TODO: Get full Item information and add item to the list
                    double quantity = Double.parseDouble(((EditText)layout.findViewById(R.id.editTextQuantity)).getText().toString());
                }
            });

            ingredientsSpinner.setAdapter(nahrungsmittelAdapter);
            //quantityUnitsSpinner.setAdapter(quantityUnitsAdapter);

            mPopUpAddIngredient = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopUpAddIngredient.setOutsideTouchable(true);

            mPopUpAddIngredient.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mPopUpAddIngredient.setTouchInterceptor(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                        mPopUpAddIngredient.dismiss();
                        return true;
                    }
                    return false;
                }
            });

            mPopUpAddIngredient.showAtLocation(mClMenuDetail, Gravity.CENTER, 0, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListView getListViewIngredients() {return mLwIngredients;}
    
    public TextView getLblMenuTotalCalories() { return mLblCalories; }

    public void populateListView(ArrayAdapter<String> arrayAdapter)
    {
        mLwIngredients.setAdapter(arrayAdapter);
    }

    public void setSnackbar(String text) {
        Snackbar.make(mView, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
