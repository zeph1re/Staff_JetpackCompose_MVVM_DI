package binar.ganda.staff_jetpackcompose_mvvm_di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import binar.ganda.staff_jetpackcompose_mvvm_di.model.GetAllStaffItem
import binar.ganda.staff_jetpackcompose_mvvm_di.repo.StaffRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StaffViewModel  @Inject constructor(val repo : StaffRepository) : ViewModel() {

    private val staffState = MutableStateFlow(emptyList<GetAllStaffItem>())

    val dataState : StateFlow<List<GetAllStaffItem>>
        get() = staffState

    init {
        viewModelScope.launch {
            val staff = repo.getStaff()
            staffState.value = staff
        }
    }
}