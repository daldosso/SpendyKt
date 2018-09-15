package com.adaldosso.spendykt.persistence

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

/**
 * Created by Alberto Dal Dosso on 14/09/2018.
 */
class Migration1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        val TABLE_NAME = "users"
        database.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN password VARCHAR")

    }
}