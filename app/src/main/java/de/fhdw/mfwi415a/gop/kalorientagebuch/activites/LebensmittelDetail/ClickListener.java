package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.LebensmittelDetail;

import android.view.View;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class ClickListener implements View.OnClickListener {

    private ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.lebensmitteldetail_delete:
                mApplicationLogic.onClickDelete();
                break;
            case R.id.lebensmitteldetail_add:
                mApplicationLogic.onClickAdd();
                break;
            case R.id.lebensmitteldetail_save:
                mApplicationLogic.onClickSave();
                break;
        }
    }
}

