package com.adaldosso.spendykt.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import com.adaldosso.spendykt.MainActivity;
import com.adaldosso.spendykt.R;
import com.adaldosso.spendykt.api.Expense;
import com.adaldosso.spendykt.utils.JSONAdapter;
import com.adaldosso.spendykt.utils.NameValuePair;
import com.adaldosso.spendykt.utils.SpendyUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class SpendyListFragment extends Fragment implements AbsListView.OnItemClickListener {

    private static final String CLASS_TAG = SpendyListFragment.class.getSimpleName();
    private AbsListView listView;
    private JSONAdapter jsonAdapter;
    private JSONArray jsonArray;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this::fillList);
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
        setAdapter();
        this.jsonAdapter.notifyDataSetChanged();
    }

    private void setAdapter() {
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
                        String date = jsonData.getString("date");
                        month = extractMonth(date);
                        year = extractYear(date);
                        amount = jsonData.getString("amount");
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
        listView.setAdapter(jsonAdapter);
    }

    private DateTime convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        return formatter.parseDateTime(date);
    }

    private String extractMonth(String date) {
        return String.valueOf(convertDate(date).getMonthOfYear());
    }

    private String extractYear(String date) {
        return String.valueOf(convertDate(date).getYear());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses_list, container, false);
        listView = (AbsListView) view.findViewById(android.R.id.list);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        JSONObject item = (JSONObject) listView.getItemAtPosition(position);
        MainActivity activity = (MainActivity) getActivity();
        try {
            String date = item.getString("date");
            activity.loadMonthlyOutgoings(extractYear(date), extractMonth(date));
        } catch (JSONException e) {
            Log.e(getTag(), e.getMessage());
        }
    }

    public void fillList() {
        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new NameValuePair(SpendyUtils.MONTH, ""));
        params.add(new NameValuePair(SpendyUtils.YEAR, ""));
        SpendyUtils.getRows(getBaseUrl(), params, this::fillListCallback);
    }

    protected abstract String getBaseUrl();

    private Void fillListCallback(List<Expense> expenses) {
        Gson gson = new Gson();
        JsonArray expensesArray = gson.toJsonTree(expenses).getAsJsonArray();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(expensesArray.toString());
        } catch (JSONException e) {
            Log.e(CLASS_TAG, "fillListCallback");
        }
        setJsonArray(jsonArray);
        swipeRefreshLayout.setRefreshing(false);
        return null;
    }

    public void addExpense(View view) {
        ((MainActivity) getActivity()).addExpense(view);
    }

}
