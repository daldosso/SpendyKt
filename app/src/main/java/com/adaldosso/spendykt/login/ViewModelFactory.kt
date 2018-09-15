package com.adaldosso.spendykt.login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.adaldosso.spendykt.persistence.UserDao

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val dataSource: UserDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
