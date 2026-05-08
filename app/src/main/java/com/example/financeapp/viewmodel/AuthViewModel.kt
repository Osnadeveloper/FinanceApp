package com.example.financeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.data.model.database.AppDatabase
import com.example.financeapp.data.model.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(context = application).userDao()

    private val _currentUser = MutableStateFlow<User?>(value = null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _authState = MutableStateFlow<AuthState>(value = AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            try {
                val user = userDao.getUserByEmail(email)
                if (user != null && user.passwordHash == hashPassword(password))
                    _currentUser.value = user
                    _authState.value = AuthState.Success


            } catch (e: Exception){

            }

        }
    }

    sealed class AuthState{

        object Idle : AuthState()
        object Loading : AuthState()
        object Success : AuthState()
        data class Error(val message: String) : AuthState()
    }
}