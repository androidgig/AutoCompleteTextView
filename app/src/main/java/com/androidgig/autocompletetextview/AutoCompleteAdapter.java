package com.androidgig.autocompletetextview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RRR on 11-02-2016.
 */
public class AutoCompleteAdapter extends ArrayAdapter<ColorModel> {

    private ArrayList<ColorModel> list;
    Context context;
    LayoutInflater inflater;
    int resource;

    public AutoCompleteAdapter(Context context, int resource, ArrayList<ColorModel> list) {
        super(context, resource);
        this.context = context;
        this.resource = resource;

        this.list = list;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = inflater.inflate(resource, null);
        }

        ColorModel model=getItem(position);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textViewColor = (TextView) view.findViewById(R.id.txtColorCode);

        textView.setText(model.getColorName());
        textViewColor.setBackgroundColor(model.getColorId());

        view.setTag(model);
        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((ColorModel) (resultValue)).getColorName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                ArrayList<ColorModel> suggestions = new ArrayList<>();

                for (ColorModel color : list) {
                    if (color.getColorName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(color);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            if (results != null && results.count > 0) {
                // we have filtered results
                addAll((ArrayList<ColorModel>) results.values);
            }
            notifyDataSetChanged();
        }
    };
}