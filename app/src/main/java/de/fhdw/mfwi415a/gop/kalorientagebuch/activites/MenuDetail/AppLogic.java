package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuDetail;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuRowAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.*;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Foodstuff;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Menu;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.MenuItem;


public class AppLogic {

    private Gui mGui;
    private Context mContext;
    private Context mCreationContext;
    private int _currentMenuID;

    private View.OnTouchListener mTouchListener;

    private ClickListener clickListener;

    private Menu _menu;

    public AppLogic (Gui gui, Context context, int menuID, Context creationContext) {
        mGui = gui;
        mContext = context;
        mCreationContext = creationContext;

        _currentMenuID = menuID;

        initGui();
        initListener();
        LoadMenu(_currentMenuID);
    }

    public String get_menuName()
    {
        return _menu.get_menuName();
    }

    public int get_menuId()
    {
        return _currentMenuID;
    }

    private void initGui() {

    }

    private void initListener()
    {
        clickListener = new ClickListener(this);
        mGui.getmFabAddIngredient().setOnClickListener(clickListener);

        mTouchListener = new SwipeOnTouchListener(this, mContext, mGui.getListViewIngredients());
    }

    public void LoadMenu(int menuId) {

        DataAdapter mDBHelper = new DataAdapter(mContext);
        mDBHelper.createDatabase();
        mDBHelper.open();

        _menu = Menu.retrieveById(menuId, mDBHelper);

        mDBHelper.close();

        if(_menu != null)
        {
            MenuRowAdapter menuItemArrayAdapter = new MenuRowAdapter(mContext, R.layout.ingredient_row, _menu.get_menuItems(), mTouchListener); // insert touchListener here
            mGui.getListViewIngredients().setAdapter(menuItemArrayAdapter);
            mGui.getLblMenuTotalCalories().setText(String.format("%.2f", _menu.get_menuCalories()) + " kcal");
        }
    }

    public void ShowPopUpAddIngredient()
    {
        try {

            View vMenuDetail =  mGui.getView();

            LayoutInflater inflater = (LayoutInflater)vMenuDetail.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final View layout = inflater.inflate(R.layout.activity_add_menu_item_pop_up, (ViewGroup)vMenuDetail.findViewById(R.id.popup_addMenuItem));

            ArrayList<Foodstuff> foodstuffs = new ArrayList<Foodstuff>();

            DataAdapter mDBHelper = new DataAdapter(vMenuDetail.getContext());
            //mDBHelper.createDatabase();
            mDBHelper.open();

            Cursor cfoodstuff = mDBHelper.getData("SELECT ID FROM Lebensmittel ORDER BY Bezeichnung", "lbGetAllLebensmittelIDs");

            int idxId = cfoodstuff.getColumnIndex("ID");

            if(cfoodstuff.getCount() > 0)
            {
                cfoodstuff.moveToFirst();

                while (!cfoodstuff.isAfterLast()) {
                    foodstuffs.add(Foodstuff.retrieveById(cfoodstuff.getInt(idxId), mDBHelper));
                    cfoodstuff.moveToNext();
                }
            }

            cfoodstuff.close();

            mDBHelper.close();

            ArrayAdapter<Foodstuff> foodstuffAdapter = new ArrayAdapter<Foodstuff>(vMenuDetail.getContext(), android.R.layout.simple_spinner_dropdown_item, foodstuffs);
            //ArrayAdapter<String> quantityUnitsAdapter = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_spinner_dropdown_item, quantityUnits);

            Button btnCancel = layout.findViewById(R.id.btnCancel);
            Button btnAddIng = layout.findViewById(R.id.btnAddIngredient);
            Spinner spinnerFoodstuff = layout.findViewById(R.id.spinnerIngredient);
            mGui.setEditTextQuantity((EditText)layout.findViewById(R.id.editTextQuantity));

            btnAddIng.setOnClickListener(clickListener);
            btnCancel.setOnClickListener(clickListener);

            spinnerFoodstuff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                    Foodstuff fstuff = (Foodstuff)adapterView.getItemAtPosition(position);

                    ((TextView)layout.findViewById(R.id.lblQuantityUnitAbbr)).setText(fstuff.get_quantityAbbreviation());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spinnerFoodstuff.setAdapter(foodstuffAdapter);

            mGui.setSpinnerFoodstuff(spinnerFoodstuff);

            PopupWindow mPopUpAddIngredient = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopUpAddIngredient.setOutsideTouchable(true);

            mPopUpAddIngredient.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mPopUpAddIngredient.setTouchInterceptor(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                        mGui.getmPopUpAddIngredient().dismiss();
                        return true;
                    }
                    return false;
                }
            });

            mGui.setmPopUpAddIngredient(mPopUpAddIngredient);

            mPopUpAddIngredient.showAtLocation(vMenuDetail.findViewById(R.id.clMenuDetail), Gravity.CENTER, 0, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BtnPopAddIngredientClicked(View view)
    {
        Spinner spinnerFoodstuff = mGui.getSpinnerFoodstuff();

        Foodstuff selectedFoodstuff = (Foodstuff)spinnerFoodstuff.getSelectedItem();
        double quantity = Double.parseDouble(mGui.getEditTextQuantity().getText().toString());

        DataAdapter db = new DataAdapter(view.getContext());
        db.open();

        ((MenuRowAdapter) mGui.getListViewIngredients().getAdapter()).add(new MenuItem(selectedFoodstuff, quantity));

        String command = String.format(Locale.US, "INSERT INTO Lebensmittel_Gericht VALUES (null, %d, %d, %d, %f)", selectedFoodstuff.get_foodstuffsId(), _currentMenuID, selectedFoodstuff.get_quantityUnitId(), quantity);

        db.writeData(command, "lgInsertNewIngredient");
        //save the data in db
        db.close();

        mGui.getmPopUpAddIngredient().dismiss();

        Toast.makeText(mContext, String.format(mContext.getResources().getString(R.string.str_ingredient_added), selectedFoodstuff.get_foodstuffName()), Toast.LENGTH_LONG).show();
    }

    public void BtnCancelClicked(View view)
    {
        mGui.getmPopUpAddIngredient().dismiss();
    }

    public void removeIngredient(MenuItem item)
    {
        DataAdapter da = new DataAdapter(mContext);
        da.open();

        da.writeData(String.format(Locale.US,"DELETE FROM Lebensmittel_Gericht WHERE GerichtId = %d AND LebensmittelId = %d;", _currentMenuID, item.get_foodstuff().get_foodstuffsId()), "lgRemoveIngredient");

        da.close();
    }
}
