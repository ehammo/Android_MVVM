package com.freelapp.myapplication

import androidx.lifecycle.ViewModel
import com.google.firebase.database.GenericTypeIndicator
import java.util.HashMap;

class UsersViewModel : ViewModel() {
    val usersLiveData =
        ResourceRepositoryImpl(
            "users",
            object : GenericTypeIndicator<HashMap<String, User>>() {}
        ).allUsers
}