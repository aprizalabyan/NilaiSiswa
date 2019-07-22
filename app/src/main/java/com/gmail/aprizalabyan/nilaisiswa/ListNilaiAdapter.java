package com.gmail.aprizalabyan.nilaisiswa;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListNilaiAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<String> mapel;
    private final ArrayList<String> nilai;


    public ListNilaiAdapter(Context context, ArrayList<String> mapel, ArrayList<String> nilai) {
        //super(context, R.layout.list_nilai);

        this.context = context;
        this.mapel = mapel;
        this.nilai = nilai;
    }

    @Override
    public int getCount() {
        return mapel.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {
        Holder holder;
        LayoutInflater inflater;

        if (child == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = inflater.inflate(R.layout.list_nilai, null);

            holder = new Holder();

            holder.textviewMapel = child.findViewById(R.id.listMapel);
            holder.textviewNilai = child.findViewById(R.id.listNilai);

            child.setTag(holder);

        } else {
            holder = (Holder) child.getTag();
        }

        holder.textviewMapel.setText(mapel.get(position));
        holder.textviewNilai.setText(nilai.get(position));

        return  child;

        /*
        View rowView = inflater.inflate(R.layout.list_nilai, null, true);

        TextView mapelText = rowView.findViewById(R.id.listMapel);
        TextView nilaiText = rowView.findViewById(R.id.listNilai);

        mapelText.setText(mapel.get(index));
        nilaiText.setText(nilai.get(index));
        return rowView;
        */
    }

    public class Holder {
        TextView textviewMapel;
        TextView textviewNilai;
    }
}
