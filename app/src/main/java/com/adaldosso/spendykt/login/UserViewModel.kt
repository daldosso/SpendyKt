package eu.lbaconsulting.estendoapp.login

import android.arch.lifecycle.ViewModel
import eu.lbaconsulting.estendoapp.persistence.User
import eu.lbaconsulting.estendoapp.persistence.UserDao
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * View Model for the [UserActivity]
 */
class UserViewModel(private val dataSource: UserDao) : ViewModel() {

    /**
     * Get the user name of the user.

     * @return a [Flowable] that will emit every time the user name has been updated.
     */
    // for every emission of the user, get the user name
    fun userName(): Flowable<String> {
        return dataSource.getUserById(USER_ID)
                .map { user -> user.userName }
    }

    /**
     * Update the user name.
     * @param userName the new user name
     * *
     * @return a [Completable] that completes when the user name is updated
     */
    fun signUp(userName: String, password: String): Completable {
        return Completable.fromAction {
            val user = User(USER_ID, userName, password)
            dataSource.insertUser(user)
        }
    }

    fun login(userName: String, password: String): Completable {
        return Completable.fromAction {
            val user = dataSource.getUserByEmail(userName)
            if (user.password != password) {
                throw Exception("Utente non trovato, premi registrati")
            }
        }
    }

    companion object {
        // using a hardcoded value for simplicity
        const val USER_ID = "1"
    }
}
