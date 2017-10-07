package com.adaldosso.spendykt.fragments;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.adaldosso.spendykt.MainActivity;
import com.adaldosso.spendykt.R;
import com.adaldosso.spendykt.utils.SpendyUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class ExpensesListFragment extends SpendyListFragment implements AbsListView.OnItemClickListener {

    @Override
    protected int getLayout() {
        return R.layout.fragment_expenses_list;
    }

    @Override
    protected int getLayoutListItem() {
        return R.layout.expense;
    }

    @Override
    protected String getBaseUrl() {
        return SpendyUtils.MONTHLY_OUTGOINGS_URL;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        JSONObject item = (JSONObject) getListView().getItemAtPosition(position);
        MainActivity activity = (MainActivity) getActivity();
        try {
            String date = item.getString("date");
            activity.loadMonthlyOutgoings(extractYear(date), extractMonth(date));
        } catch (JSONException e) {
            Log.e(getTag(), e.getMessage());
        }
    }

}
