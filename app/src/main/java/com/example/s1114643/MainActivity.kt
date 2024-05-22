package com.example.s1114643

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.s1114643.ui.theme.S1114643Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S1114643Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Main()
                }
            }
        }
    }
}
@Composable
fun FirstScreen(NavController: NavController) {
    var title by remember { mutableStateOf("瑪利亞基金會服務總覽") }
    Column(
        modifier = Modifier
    ) {
        Text(text = title, color = Color.Blue)
        var appear by remember { mutableStateOf(true) }
        Column {
            AnimatedVisibility(
                visible = appear,
                enter = fadeIn(
                    initialAlpha = 0.1f,
                    animationSpec = tween(durationMillis = 1500)
                ),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = 1500)
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.service),
                    contentDescription = "服務總覽"
                )
            }
            AnimatedVisibility(
                visible = !appear,
                enter = fadeIn(
                    initialAlpha = 0.1f,
                    animationSpec = tween(durationMillis = 1500)
                ),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = 1500)
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mee),
                    contentDescription = "mee"
                )
            }
            Button(
                onClick = { appear = !appear }
            ) {
                if (appear) {
                    Text(text = "作者:資管二A林威呈")
                    title = "瑪利亞基金會服務總覽"
                } else {
                    Text(text = "服務總覽")
                    title = "關於作者"
                }
            }
        }
    }
}
@Composable
fun SecondScreen(navController: NavController) {
    var selected by remember { mutableStateOf("台中市愛心家園") }
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "主要機構", color = Color.Red, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { selected = "台中市愛心家園" }) {
                Text(text = "台中市愛心家園")
            }
            Button(onClick = { selected = "瑪利亞學園" }) {
                Text(text = "瑪利亞學園")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (selected) {
            "台中市愛心家園" -> {
                Column {
                    Text(
                        text = "台中市愛心家園 經市政府公開評選後，委託瑪利亞基金會經營管理，於91年啟用，整棟建築物有四個樓層，目前開辦就醫、就養、就學、就業四大領域的十項業務，提供身心障礙者全方位的服務。",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "長按以下圖片，可以觀看愛心家園地圖",
                        fontSize = 16.sp,
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.lovehome),
                        contentDescription = "Love Home",
                        modifier = Modifier
                            .size(200.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        val gmmIntentUri = Uri.parse("geo:0,0?q=台中市南屯區東興路一段450號")
                                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                        mapIntent.setPackage("com.google.android.apps.maps")
                                        context.startActivity(mapIntent)
                                    }
                                )
                            }
                    )
                }
            }
            "瑪利亞學園" -> {
                Column {
                    Text(
                        text = "瑪利亞學園 提供重度以及極重度多重障礙者日間照顧服務，以健康照護為基礎，支持生活多面向參與及學習概念，輔助發展重度身心障礙者自我概念為最終服務目標。",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "雙擊以下圖片，可以觀看瑪利亞學園地圖",
                        fontSize = 16.sp,
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.campus),
                        contentDescription = "Campus",
                        modifier = Modifier
                            .size(200.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onDoubleTap = {
                                        val gmmIntentUri = Uri.parse("geo:0,0?q=台中市北屯區經貿東路365號")
                                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                        mapIntent.setPackage("com.google.android.apps.maps")
                                        context.startActivity(mapIntent)
                                    }
                                )
                            }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val navController = rememberNavController()
    var showMenu by remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            title = {
                Image(
                    painter = painterResource(id = R.drawable.maria),
                    contentDescription = "maria"
                )
            },
            actions = {
                IconButton(
                    onClick = { showMenu = true }
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")

                }
                DropdownMenu(
                    expanded = showMenu, onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("簡介") },
                        onClick = { navController.navigate("JumpFirst") })

                    DropdownMenuItem(
                        text = { Text("主要機構") },
                        onClick = { navController.navigate("JumpSecond") })
                }

            }
        )

        NavHost(navController = navController, startDestination = "JumpFirst") {
            composable("JumpFirst") {
                FirstScreen(NavController = navController)
            }
            composable("JumpSecond") {
                SecondScreen(navController = navController)
            }
        }
    }
}