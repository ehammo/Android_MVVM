package com.freelapp.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val usersViewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
        Log.d(TAG, "MainActivity")

//        FirebaseDatabase.getInstance().getReference("users").addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//                Log.d(TAG, "abb $p0")
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                Log.d(TAG, "aaa $p0")
//            }
//        })

        usersViewModel.usersLiveData.observe(this, Observer { resource ->
            resource.onSuccess { users ->
                usersLoaded(users)
            }.onFailure { lastValidUsers, error -> {
                usersLoadingFailed(lastValidUsers, error)
            }
        })
    }
    
    private fun usersLoaded(users : HashMap<String, User>) {
        Log.d(TAG, users.toString()) 
    }
                                             
    private fun usersLoadingFailed(lastValidUsers : HashMap<String, User>, error: String) {
        Log.d(TAG, "${error?.toString()}") 
    }
                                             

}
