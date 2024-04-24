package com.rbppl.testfoodiesapp.main
import androidx.compose.runtime.mutableFloatStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbppl.testfoodiesapp.main.domain.repository.DatabaseRepository
import com.rbppl.testfoodiesapp.main.domain.usecase.UpdateMenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val updateMenuUseCase: UpdateMenuUseCase,
    private val databaseRepository: DatabaseRepository
): ViewModel() {
    val splashProgress = mutableFloatStateOf(0f)
    val updateState = updateMenuUseCase.updateState
    init {
        update()
    }
    fun update() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(100)
            updateMenuUseCase.update()
        }
    }
    suspend fun clear(){
        databaseRepository.clear()
    }
}
