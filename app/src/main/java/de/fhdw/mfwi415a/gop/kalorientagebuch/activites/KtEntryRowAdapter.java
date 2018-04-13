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
import java.util.Locale;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.KTEntry;

public class KtEntryRowAdapter extends ArrayAdapter<KTEntry> {

    private Context mContext;
    private int resourceId;
    private ArrayList<KTEntry> mEntries;

    public static LayoutInflater inflater = null;


    public KtEntryRowAdapter(Context context, int resource, ArrayList<KTEntry> entries)
    {
        super(context, resource, entries);

        mContext = context;
        resourceId = resource;
        mEntries = entries;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View vi = convertView;

        if(vi == null)
            vi = inflater.inflate(R.layout.ktentry_row, null);


        KTEntry entry = mEntries.get(position);

        ((TextView)vi.findViewById(R.id.lblEntryName)).setText(entry.get_description());
        ((TextView)vi.findViewById(R.id.lbl_kt_ingredient)).setText(entry.get_ingredient());
        ((TextView)vi.findViewById(R.id.lbl_ktentry_calories)).setText(String.format(Locale.ROOT, "%.2f", entry.get_totalCalories()) + " kcals");

        return vi;
    }

    @Override
    public int getCount() {
        return mEntries == null ? 0 : mEntries.size();
    }
}
