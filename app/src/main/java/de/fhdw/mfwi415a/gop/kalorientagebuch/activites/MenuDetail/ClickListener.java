package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuDetail;

import android.view.View;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.LebensmittelDetail.ApplicationLogic;

public class ClickListener implements View.OnClickListener
{
    private AppLogic mApplicationLogic;

    public ClickListener(AppLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fab_add_ingredient:
                mApplicationLogic.ShowPopUpAddIngredient();
                break;
            case R.id.btnAddIngredient:
                mApplicationLogic.BtnPopAddIngredientClicked(view);
                mApplicationLogic.LoadMenu(mApplicationLogic.get_menuId());
                break;
            case R.id.btnCancel:
                mApplicationLogic.BtnCancelClicked(view);
        }
    }
}
