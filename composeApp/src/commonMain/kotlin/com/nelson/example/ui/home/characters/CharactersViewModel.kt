package com.nelson.example.ui.home.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nelson.example.domain.GetRandomCharacter
import com.nelson.example.domain.Repository
import com.nelson.example.domain.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//El viewModel llama al caso de Uso
//llamamos al repositorio para obtener los datos de todos los caracteres, pero si la aplicacion lo
//requiere deberiamos crear un caso de uso en este caso ya que solo mostramos informacion no
// abria problema en llamar directamente al repositorio para obtenr la data
class CharactersViewModel(val getRandomCharacter: GetRandomCharacter, private val repository: Repository):ViewModel() {
    //Nos permite que la vista escuhe cualquier cambio
    private val _state = MutableStateFlow<CharacterState>(CharacterState())
    //nos permite ver el estado sin modificarlo utilizando esta variable
    val state:StateFlow<CharacterState> = _state

    init {
        //Este es el hilo principal
        viewModelScope.launch {
            //Esta corrutina esta diseñada para hacer prosesos que no esten en el hilo principal
            // y asi evitar que tareas pesadas se ejecuten en el hilo Main
            val result: CharacterModel = withContext(Dispatchers.IO){
                //Aqui solo necesitamos añadir los parentesis ya que incluimos
                // el Operation Invoque en la clase GetRandomCharacter
                getRandomCharacter()
            }
            //Me actualiza el estado
            _state.update {state -> state.copy(
                characterOFTheDay = result)}
        }
        getAllCharacters()
    }

    //con esto ya estamos guardando los datos y el flujo en nuestro view model
    private fun getAllCharacters() {
        _state.update { state -> state.copy(
            characters = repository.getAllCharacters().cachedIn(viewModelScope)
        ) }
    }
}