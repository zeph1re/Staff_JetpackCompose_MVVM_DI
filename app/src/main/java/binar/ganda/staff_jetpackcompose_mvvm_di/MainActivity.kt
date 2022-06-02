package binar.ganda.staff_jetpackcompose_mvvm_di

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import binar.ganda.staff_jetpackcompose_mvvm_di.model.GetAllStaffItem
import binar.ganda.staff_jetpackcompose_mvvm_di.ui.theme.Staff_JetpackCompose_MVVM_DITheme
import binar.ganda.staff_jetpackcompose_mvvm_di.viewmodel.StaffViewModel
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Staff_JetpackCompose_MVVM_DITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val staffViewModel = viewModel(modelClass = StaffViewModel::class.java)
                    val dataStaff by staffViewModel.dataState.collectAsState()

                    LazyColumn(){
                        if (dataStaff.isEmpty()){
                            item{
                            }
                        }

                        items(dataStaff){
                            StaffCard(staff = it)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun StaffCard(staff : GetAllStaffItem) {
    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        val mContext = LocalContext.current
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val intent = Intent(mContext, DetailActivity::class.java)
                    intent.putExtra("detail", staff)
                    mContext.startActivity(intent)
                },
            backgroundColor = Color.LightGray,
            shape = RoundedCornerShape(10.dp)
        ) {
            Row() {
                Image(
                    painter = rememberImagePainter(data = staff.image),
                    contentDescription = "img",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(100.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = staff.name,
                        modifier = Modifier.padding(bottom = 5.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = staff.email,
                        modifier = Modifier.padding(bottom = 5.dp),
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                    Text(
                        text = staff.createdAt,
                        modifier = Modifier.padding(bottom = 5.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )

                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Staff_JetpackCompose_MVVM_DITheme {
       StaffCard(GetAllStaffItem("","","","","","",""))
    }
}