package de.fhdw.mfwi415a.gop.kalorientagebuch.activites;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Foodstuff;

public class MenuItemSpinnerAdapter extends ArrayAdapter<Foodstuff> {
    private Context context;
    private int resourceId;
    private ArrayList<Foodstuff> ingredients;

    public static LayoutInflater inflater = null;

    public MenuItemSpinnerAdapter(Context context, int resource, ArrayList<Foodstuff> ingredients)
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

        Foodstuff foodstuff = ingredients.get(position);

        lbl_ingredient_name.setText(foodstuff.get_foodstuffName());
        lbl_ingredient_amount.setText(foodstuff.get_quantityAbbreviation());

        return vi;
    }

    @Override
    public int getCount() {
        return ingredients == null ? 0 : ingredients.size();
    }
}
