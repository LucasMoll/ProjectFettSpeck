package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation;

/**
 * Created by joel on 05.03.18.
 */

import android.widget.Button;
import android.widget.TextView;
import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {

    private Button mExampleButton;
    private TextView mExampleTextView;

    public Gui (Init activity) {
        activity.setContentView(R.layout.activity_navigation);

       // mExampleButton = (Button) activity.findViewById(R.id.idExampleButton);
       // mExampleTextView = (TextView) activity.findViewById(R.id.idExampleTextview);
    }

    // getter to access views

    public Button getExampleButton() {
        return mExampleButton;
    }

    public TextView getExampleTextView() {
        return mExampleTextView;
    }

    // methods to change view attributes

    public void setColorOfExampleTextView(int value) {
        mExampleTextView.setBackgroundColor(value);
    }

    // ....

}