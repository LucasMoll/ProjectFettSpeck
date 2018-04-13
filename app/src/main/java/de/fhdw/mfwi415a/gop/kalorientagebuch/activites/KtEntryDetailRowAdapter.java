package de.fhdw.mfwi415a.gop.kalorientagebuch.activites;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Edible;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.IEdible;

public class KtEntryDetailRowAdapter extends ArrayAdapter<Edible> {

    private Context mContext;
    private int resourceId;
    private ArrayList<Edible> mEdibles;

    public static LayoutInflater inflater = null;

    public KtEntryDetailRowAdapter(Context context, int resource, ArrayList<Edible> entries)
    {
        super(context,resource,entries);

        mContext = context;
        resourceId = resource;
        mEdibles = entries;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View vi = convertView;

        if(vi == null)
            vi = inflater.inflate(R.layout.kt_entry_detail_row, null);


        Edible edible = mEdibles.get(position);

        ((TextView)vi.findViewById(R.id.LblktEntryName)).setText(edible.getEdible().get_Name());
        ((TextView)vi.findViewById(R.id.LblKtQuantityName)).setText(edible.getQuantity() + " " + edible.getEdible().get_quantityAbbreviation());
        ((TextView)vi.findViewById(R.id.LblKTEntryDetailCalories)).setText(String.format(Locale.ROOT, "%.2f", edible.getEdible().get_Calories() * edible.getQuantity()) + " kcals");

        return vi;
    }

    @Override
    public int getCount() {
        return mEdibles == null ? 0 : mEdibles.size();
    }
}
