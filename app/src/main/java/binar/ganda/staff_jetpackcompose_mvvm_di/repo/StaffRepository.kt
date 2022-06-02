package binar.ganda.staff_jetpackcompose_mvvm_di.repo

import binar.ganda.staff_jetpackcompose_mvvm_di.model.GetAllStaffItem
import binar.ganda.staff_jetpackcompose_mvvm_di.service.StaffService
import javax.inject.Inject

class StaffRepository @Inject constructor(private val service : StaffService) {

    suspend fun getStaff(): List<GetAllStaffItem> {
        return service.getAllStaff()
    }
}