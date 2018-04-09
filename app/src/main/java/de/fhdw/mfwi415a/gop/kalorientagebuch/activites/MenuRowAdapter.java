package de.fhdw.mfwi415a.gop.kalorientagebuch.activites;

import android.content.Context;
import android.support.annotation.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Gericht;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Nahrungsmittel;


public class MenuRowAdapter extends ArrayAdapter<Nahrungsmittel> {

    private Context context;
    private int resourceId;
    private ArrayList<Nahrungsmittel> ingredients;

    public static LayoutInflater inflater = null;

    public MenuRowAdapter(Context context, int resource, ArrayList<Nahrungsmittel> ingredients)
    {
        super(context, resource, ingredients);

        this.context = context;
        this.resourceId = resource;
        this.ingredients = ingredients;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View vi = convertView;

        if(vi == null)
            vi = inflater.inflate(R.layout.ingredient_row, null);

        ImageView img_ingredient = vi.findViewById(R.id.img_ingredient);
        TextView lbl_ingredient_name = vi.findViewById(R.id.lbl_ingredient_name);
        TextView lbl_ingredient_amount = vi.findViewById(R.id.lbl_ingredient_amount);
        TextView lbl_ingredient_calories = vi.findViewById(R.id.lbl_ingredient_calories);

        Nahrungsmittel ingredient = ingredients.get(position);

        lbl_ingredient_name.setText(ingredient.get_Bezeichnung());

        // TODO: Get missing data and fill in the view

        return vi;
    }
}

