package com.adaldosso.spendykt.fragments

import android.app.Fragment
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.adaldosso.spendykt.R
import com.adaldosso.spendykt.api.BaseExpense
import com.adaldosso.spendykt.utils.BaseExpensesAdapter
import com.adaldosso.spendykt.utils.SpendyUtils.showMessage

abstract class SpendyListFragment : Fragment(), AdapterView.OnItemClickListener, View.OnClickListener {

    protected var recyclerView: RecyclerView? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    protected abstract val layout: Int

    protected abstract val layoutResourceId: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout = activity.findViewById(R.id.swiperefresh)
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout!!.setOnRefreshListener({ this.fillList() })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layout, container, false)
        recyclerView = view.findViewById(R.id.expensesList)
        recyclerView!!.layoutManager = LinearLayoutManager(view.context)
        return view
    }

    abstract fun fillList()

    protected fun fillListCallback(expenses: List<BaseExpense>): Void? {
        setExpenses(expenses)
        swipeRefreshLayout!!.isRefreshing = false
        swipeRefreshLayout!!.visibility = View.VISIBLE
        //        getActivity().findViewById(R.id.progressBar).setVisibility(View.GONE);
        hideProgressBar()
        return null
    }

    protected abstract fun hideProgressBar()

    private fun setExpenses(expenses: List<BaseExpense>) {
        val expensesAdapter = createExpensesAdapter(expenses, recyclerView)
        /*
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
*/
        if (recyclerView != null) {
            recyclerView!!.setOnClickListener(this)
            recyclerView!!.adapter = expensesAdapter
        }
        expensesAdapter
                .onClickSubject
                .subscribe({ this.onExpenseClick(it) })
    }

    protected abstract fun createExpensesAdapter(expenses: List<BaseExpense>, recyclerView: RecyclerView?): BaseExpensesAdapter

    protected open fun onExpenseClick(expense: BaseExpense) {}

    override fun onResume() {
        super.onResume()
        fillList()
    }

    override fun onClick(view: View) {
        showMessage(context, "onClick")
    }

    override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
        showMessage(context, "onItemClick")
    }

}
