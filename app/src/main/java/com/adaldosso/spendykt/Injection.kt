package com.adaldosso.spendykt

import android.content.Context
import eu.lbaconsulting.estendoapp.login.ViewModelFactory
import eu.lbaconsulting.estendoapp.persistence.UserDao
import eu.lbaconsulting.estendoapp.persistence.UsersDatabase

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
