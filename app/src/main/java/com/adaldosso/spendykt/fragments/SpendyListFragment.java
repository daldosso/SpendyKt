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
import org.joda.time.YearMonth;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
                    convertView = getActivity().getLayoutInflater().inflate(getLayoutListItem(), null);
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
                    if (textMonth != null) {
                        textMonth.setText(month);
                    }
                    if (textYear != null) {
                        textYear.setText(year);
                    }
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

    protected String extractMonth(String date) {
        DateTime dateTime = convertDate(date);
        YearMonth yearMonth = new YearMonth(dateTime.getYear(), dateTime.getMonthOfYear());
        return SpendyUtils.capitalize(yearMonth.monthOfYear().getAsText(new Locale("it"))) ;
    }

    protected String extractYear(String date) {
        return String.valueOf(convertDate(date).getYear());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        listView = (AbsListView) view.findViewById(android.R.id.list);
        listView.setOnItemClickListener(this);
        return view;
    }

    protected abstract int getLayout();

    protected abstract int getLayoutListItem();

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

    public AbsListView getListView() {
        return listView;
    }

    @Override
    public void onResume() {
        super.onResume();
        fillList();
    }
}
