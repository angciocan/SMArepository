package com.example.proiectsma

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthentificationViewModel : ViewModel(){

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val auth_state = MutableLiveData<AuthState>()

    val authState : LiveData<AuthState> = auth_state

    init{
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser == null){
            auth_state.value = AuthState.Unauthenticated

        }else{
            auth_state.value = AuthState.Authenticated
        }
    }

    fun login(email : String, password : String){

        if(email.isEmpty() || password.isEmpty()){
            auth_state.value = AuthState.Error("The user e-mail or user password is empty!")
            return
        }

        auth_state.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                task ->
            if(task.isSuccessful){
                auth_state.value = AuthState.Authenticated
            }

            else{
                auth_state.value = AuthState.Error(task.exception?.message?:"The user e-mail or user password is not correct!")
            }
        }
    }

    fun signUp(email: String, password: String){

        if(email.isEmpty() || password.isEmpty()){
            auth_state.value = AuthState.Error("The user e-mail or user password is empty!")
            return
        }

        if(password.length < 6){
            auth_state.value = AuthState.Error("The password length must hase at least 6 characters")
        }

        if(!email.contains('@')) {
            auth_state.value = AuthState.Error("The user e-mail must contains the '@' character!")
            return
        }

        auth_state.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                task ->
            if(task.isSuccessful){
                auth_state.value = AuthState.Authenticated
            }

            else{
                auth_state.value = AuthState.Error(task.exception?.message?:"The user e-mail or user password is not correct!")
            }
        }
    }

    fun signOut(){
        auth.signOut()
        auth_state.value = AuthState.Unauthenticated
    }
}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()
}