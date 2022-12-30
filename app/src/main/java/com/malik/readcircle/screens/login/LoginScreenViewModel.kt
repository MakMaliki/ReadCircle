package com.malik.readcircle.screens.login

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.malik.readcircle.model.MUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {
    val loadingState = MutableStateFlow(LoadingState.IDLE)

    private val auth:FirebaseAuth = Firebase.auth

    private val fireStoreDatabase = FirebaseFirestore.getInstance()

    private val _loading = MutableLiveData(false)
    val laoading: LiveData<Boolean> = _loading

    fun signInWithEmailAndPassword(email:String, password:String,home: ()-> Unit)
    //I dont want all of this in main thread so
   // I better use COROUTINE to run it in another thread
    = viewModelScope.launch{

        try {
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{ task ->

                    if (task.isSuccessful){
                        Log.d("Login", "Success Login:+${task.result.toString()}")
                        home()
                    }else{
                        Log.d("Login","signInWIthEmailAnd Password ${task.result.toString()}")

                    }
                }

        }catch (error:Exception){
            Log.d("Login","signInWIthEmailAnd Password ${error.message}")
        }

    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit
    )
    =viewModelScope.launch{
        if (_loading.value==false){
            _loading.value=true

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{ task ->

                if (task.isSuccessful){
                    val displayName = task.result?.user?.email?.split('@')?.get(0)
                    createUser(displayName)
                    home()
                }
                else{
                    Log.d("Login","createUserWithEmailAndPassword ${task.result.toString()}")

                }
                _loading.value = false

            }

        }
    }

    private fun createUser(displayName: String?) {

        val userId=auth.currentUser?.uid
        val user = MUser(userId =userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "Life is beautiful",
            profession = "Android Developer",
            id = null).toMap()

        fireStoreDatabase.collection("users").add(user)
    }


}