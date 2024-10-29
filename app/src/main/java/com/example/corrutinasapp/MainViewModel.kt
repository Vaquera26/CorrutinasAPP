package com.example.corrutinasapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    var resultState by mutableStateOf("")
    private set

    var isLoading by mutableStateOf(false)
    private set

    fun BloqueoApp() {
        Thread.sleep(5000)
        resultState = "Bloqueo terminado"
    }

    private suspend fun llamarAPI (){
        val result = withContext(Dispatchers.IO) {
            // Simulate a long running operation
            delay(5000)
            "Respuesta de la API"
        }

        resultState = result;
    }



    fun fetchData(){
        viewModelScope.launch {

            try {
                isLoading = true
                llamarAPI()
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                isLoading = false
            }
            }
        }

    }




