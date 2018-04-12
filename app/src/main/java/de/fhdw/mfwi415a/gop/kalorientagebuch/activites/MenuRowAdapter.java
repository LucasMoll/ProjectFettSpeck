package de.fhdw.mfwi415a.gop.kalorientagebuch.activites;

import android.content.Context;
import android.support.annotation.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.MenuItem;


public class MenuRowAdapter extends ArrayAdapter<MenuItem> {

    private Context context;
    private int resourceId;
    private ArrayList<MenuItem> ingredients;

    public static LayoutInflater inflater = null;

    public MenuRowAdapter(Context context, int resource, ArrayList<MenuItem> ingredients)
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

        MenuItem ingredient = ingredients.get(position);

        lbl_ingredient_name.setText(ingredient.get_foodstuffName());
        lbl_ingredient_amount.setText(ingredient.get_quantity() + " " + ingredient.get_quantityAbbreviation());
        lbl_ingredient_calories.setText(ingredient.get_calories() + " kcal");

        return vi;
    }

    @Override
    public int getCount() {
        return ingredients == null ? 0 : ingredients.size();
    }
}

