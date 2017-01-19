package com.adaldosso.spendykt.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import com.adaldosso.spendykt.MainActivity;
import com.adaldosso.spendykt.R;
import com.adaldosso.spendykt.utils.JSONAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExpensesListFragment extends Fragment implements AbsListView.OnItemClickListener {

    private AbsListView listView;
    private JSONAdapter jsonAdapter;
    private JSONArray jsonArray;

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jsonAdapter = new JSONAdapter(getActivity(), jsonArray) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.expense, null);
                }
                TextView textMonth =(TextView)convertView.findViewById(R.id.month);
                TextView textYear =(TextView)convertView.findViewById(R.id.year);
                TextView textAmount =(TextView)convertView.findViewById(R.id.amount);
                JSONObject jsonData = getItem(position);
                if (null != jsonData ){
                    String month = "";
                    String year = "";
                    String amount = "";
                    try {
                        month = jsonData.getString("mese");
                        year = jsonData.getString("anno");
                        amount = jsonData.getString("importo");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    textMonth.setText(month);
                    textYear.setText(year);
                    textAmount.setText(amount);
                }
                return convertView;
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses_list, container, false);
        listView = (AbsListView) view.findViewById(android.R.id.list);
        listView.setAdapter(jsonAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        JSONObject item = (JSONObject) listView.getItemAtPosition(position);
        MainActivity activity = (MainActivity) getActivity();
        try {
            activity.loadMonthlyOutgoings(item.getInt("anno"), item.getInt("numMese"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void fillList() {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray("[" +
                    "{'date': '01/01/2017', 'amount': 150.43}" +
                    "{'date': '04/01/2017', 'amount': 50.00}" +
                    "{'date': '06/01/2017', 'amount': 10.37}" +
                    "]");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setJsonArray(jsonArray);

    }
}
