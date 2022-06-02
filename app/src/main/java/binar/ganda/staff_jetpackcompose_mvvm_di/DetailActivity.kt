package binar.ganda.staff_jetpackcompose_mvvm_di

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import binar.ganda.staff_jetpackcompose_mvvm_di.model.GetAllStaffItem
import binar.ganda.staff_jetpackcompose_mvvm_di.ui.theme.Staff_JetpackCompose_MVVM_DITheme
import coil.compose.rememberImagePainter
import androidx.annotation.Nullable as Nullable

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Staff_JetpackCompose_MVVM_DITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val detail = intent.getSerializableExtra("detail") as GetAllStaffItem
                    DetailStaff(detail)
                }
            }
        }
    }
}

@Composable
fun DetailStaff(staff : GetAllStaffItem) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val newsDetail =

            Image(
                painter = rememberImagePainter(data = staff.image),
                contentDescription = "img",
                modifier = Modifier.size(400.dp)
            )
        Text(
            text = staff.name,
            modifier = Modifier.padding(bottom = 5.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Text(
            text = staff.email,
            modifier = Modifier.padding(bottom = 5.dp),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )

        Text(
            text = staff.createdAt,
            modifier = Modifier.padding(bottom = 5.dp),
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Red
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    Staff_JetpackCompose_MVVM_DITheme {
        DetailStaff(GetAllStaffItem("","","","","","",""))
    }
}