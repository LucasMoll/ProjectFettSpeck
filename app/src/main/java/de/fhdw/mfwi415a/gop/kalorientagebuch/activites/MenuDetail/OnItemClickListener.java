package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuDetail;

import android.view.View;
import android.widget.AdapterView;

    public class OnItemClickListener implements AdapterView.OnItemClickListener {

        private AppLogic mApplicationLogic;

        public OnItemClickListener(AppLogic applicationLogic) {
            mApplicationLogic = applicationLogic;
        }
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // reload the menu
            mApplicationLogic.LoadMenu(mApplicationLogic.get_menuId());
        }
    }
