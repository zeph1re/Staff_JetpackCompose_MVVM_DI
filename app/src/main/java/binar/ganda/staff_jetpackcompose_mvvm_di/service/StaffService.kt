package binar.ganda.staff_jetpackcompose_mvvm_di.service

import binar.ganda.staff_jetpackcompose_mvvm_di.model.GetAllStaffItem
import retrofit2.http.GET

interface StaffService {

    @GET ("staf")
    suspend fun getAllStaff() : List<GetAllStaffItem>
}