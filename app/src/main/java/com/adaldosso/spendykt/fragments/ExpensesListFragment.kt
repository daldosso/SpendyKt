package com.adaldosso.spendykt.fragments

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adaldosso.spendykt.R
import com.adaldosso.spendykt.fragments.dummy.DummyContent

class ExpensesListFragment : Fragment() {
    private var mListener: OnListFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val recyclerView = inflater.inflate(R.layout.fragment_item_list, container, false) as RecyclerView?
        recyclerView!!.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener)
        return recyclerView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


/*
    override fun getLayout(): Int {
        return R.layout.fragment_item_list
    }

    override fun fillList() {
    }

    override fun createExpensesAdapter(expenses: MutableList<out BaseExpense>?, recyclerView: RecyclerView?): BaseExpensesAdapter {
        return ExpensesAdapter(expenses, recyclerView, getLayoutResourceId())
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.expense
    }
*/

/*
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
//        (activity.findViewById<View>(R.id.textView) as TextView).text = "ExpensesListFragment"

        if (view is RecyclerView) {
            val context = view.getContext()
            val recyclerView = view
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = MyItemRecyclerViewAdapter(DummyContent.ITEMS, object: OnListFragmentInteractionListener {
                override fun onListFragmentInteraction(item: DummyContent.DummyItem) {

                }
            })
        }

        return view
    }
*/

    /*
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) getActivity().findViewById(R.id.textView)).setText("ExpensesListFragment");
        getActivity().findViewById(R.id.textView).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.expensesList).setVisibility(GONE);
        getActivity().findViewById(R.id.expensesList).setVisibility(GONE);

    }
*/

/*
    @Override
    protected int getLayout() {
        return R.layout.fragment_item_list;
    }

    @Override
    public void fillList() {
        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new NameValuePair(SpendyUtils.MONTH, ""));
        params.add(new NameValuePair(SpendyUtils.YEAR, ""));
//        SpendyUtils.getRows(params, this::fillListCallback);
        SpendyUtils.getMonthlyExpenses(this::fillListCallback);
    }

    @Override
    protected ExpensesAdapter createExpensesAdapter(List<? extends BaseExpense> expenses, RecyclerView recyclerView) {
        return new ExpensesAdapter(expenses, recyclerView, getLayoutResourceId());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.expense;
    }

*/
//    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {}

}
