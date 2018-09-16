package com.adaldosso.spendykt.login

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.adaldosso.spendykt.Injection
import com.adaldosso.spendykt.R
import io.reactivex.disposables.CompositeDisposable

/**
 * Main screen of the app. Displays a user name and gives the option to update the user name.
 */
class UserActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: UserViewModel

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        viewModelFactory = Injection.provideViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
//        update_user_button.setOnClickListener { updateUserName() }
    }

    override fun onStart() {
        super.onStart()
        // Subscribe to the emissions of the user name from the view model.
        // Update the user name text view, at every onNext emission.
        // In case of error, log the exception.
/*
        disposable.add(viewModel.userName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.user_name.text = it },
                        { error -> Log.e(TAG, "Unable to get username", error) }))
*/
    }

    override fun onStop() {
        super.onStop()

        // clear all the subscription
        disposable.clear()
    }

    private fun updateUserName() {
/*
        val userName = user_name_input.text.toString()
        // Disable the update button until the user name update has been done
        update_user_button.isEnabled = false
*/
        // Subscribe to updating the user name.
        // Enable back the button once the user name has been updated
/*
        disposable.add(viewModel.updateUserName(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ update_user_button.isEnabled = true },
                        { error -> Log.e(TAG, "Unable to update username", error) }))
*/
    }

    companion object {
        private val TAG = UserActivity::class.java.simpleName
    }
}
