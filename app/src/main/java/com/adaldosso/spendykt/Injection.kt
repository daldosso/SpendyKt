package com.adaldosso.spendykt

import android.content.Context
import com.adaldosso.spendykt.login.ViewModelFactory
import com.adaldosso.spendykt.persistence.UserDao
import com.adaldosso.spendykt.persistence.UsersDatabase

/**
 * Enables injection of data sources.
 */
object Injection {

    private fun provideUserDataSource(context: Context): UserDao {
        val database = UsersDatabase.getInstance(context)
        return database.userDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}
