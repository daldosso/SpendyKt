package eu.lbaconsulting.estendoapp.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * The Room database that contains the Users table
 */
@Database(entities = [User::class], version = 2)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile private var INSTANCE: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, UsersDatabase::class.java, "EstendoApp.db")
//                .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                .build()

        @JvmField
        val MIGRATION_1_2 = Migration1To2()
    }
}
