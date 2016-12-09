package com.adaldosso.spendykt.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adaldosso.spendykt.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExpenseAdapter extends JSONAdapter {

    public ExpenseAdapter(Activity activity, JSONArray jsonArray) {
        super(activity, jsonArray);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = getActivity().getLayoutInflater().inflate(R.layout.detail_expense, null);
        }
        TextView dateDetail =(TextView)convertView.findViewById(R.id.dateDetail);
        TextView amountDetail =(TextView)convertView.findViewById(R.id.amountDetail);
        TextView categoryDetail =(TextView)convertView.findViewById(R.id.categoryDetail);
        TextView noteDetail =(TextView)convertView.findViewById(R.id.noteDetail);

        JSONObject jsonData = getItem(position);
        if (null != jsonData ){
            String date = "";
            String amount = "";
            String category = "";
            String note = "";
            try {
                date = jsonData.getString("dataSpesaFormatted");
                amount = jsonData.getString("importo");
                category = jsonData.getString("categoria");
                note = jsonData.getString("note");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dateDetail.setText("Data: " + date);
            amountDetail.setText("Importo: " + amount + " €");
            categoryDetail.setText("Categoria: " + category);
            noteDetail.setText("Note: " + note);
        }

        return convertView;
    }

}
