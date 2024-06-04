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
    var title by remember { mutableStateOf("資源地圖") }
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
                    painter = painterResource(id = R.drawable.wall),
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
                Column {
                Image(
                    painter = painterResource(id = R.drawable.food),
                    contentDescription = "mee"
                )
                    Text(
                        text = "食物銀行是一種非營利機構,主要收集社會剩餘食物並加以分類存放,再透過慈善團體將食物分發至弱勢群體,如低收入家庭、無家可歸者等。食物銀行的運作需要民眾企業捐贈及志願者協助,不僅減少食物浪費,更提供必要的食物援助。",
                        fontSize = 16.sp
                    )
                    }
            }
            Button(
                onClick = { appear = !appear }
            ) {
                if (appear) {
                    Text(text = "食物銀行簡介")
                    title = "食物銀行"
                } else {
                    Text(text = "回首頁")
                    title = "食物銀行簡介"
                }
            }
        }
    }
}
@Composable
fun SecondScreen(navController: NavController) {
    var selected by remember { mutableStateOf("1919食物銀行台中園區") }
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "主要機構", color = Color.Red, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { selected = "1919食物銀行台中園區" }) {
                Text(text = "1919食物銀行台中園區")
            }
            Button(onClick = { selected = "家扶好鄰居 愛心物資銀行" }) {
                Text(text = "家扶好鄰居 愛心物資銀行")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (selected) {
            "1919食物銀行台中園區" -> {
                Column {
                    Text(
                        text = "2017年3月，在台中開設全台首座融合「惜食5環」的「1919食物銀行台中園區」 (「惜食5環」含：中央倉儲、實體食物銀行、惜食處理、中央廚房，及培力食育教室)。",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "如何去1919食物銀行台中園區,長按下方圖片",
                        fontSize = 16.sp,
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.foodbank1919),
                        contentDescription = "Love Home",
                        modifier = Modifier
                            .size(200.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        val gmmIntentUri = Uri.parse("geo:0,0?q=台中市西屯區西平南巷2-5號")
                                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                        mapIntent.setPackage("com.google.android.apps.maps")
                                        context.startActivity(mapIntent)
                                    }
                                )
                            }
                    )
                }
            }
            "家扶好鄰居 愛心物資銀行" -> {
                Column {
                    Text(
                        text = "家扶好鄰居愛心物資銀行是家扶基金會的專案,透過民眾及企業捐贈物資,收集食品、生活用品等剩餘物資,再由家扶將這些物資分類、包裝,提供給遭逢經濟困境的弱勢家庭使用。此外,物資銀行也積極號召企業及民眾捐贈中古物資,落實資源再利用及環保概念,讓愛心從這裡傳遞開來。",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "家扶好鄰居 愛心物資銀行,長按下方圖片",
                        fontSize = 16.sp,
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.foodbank02),
                        contentDescription = "Campus",
                        modifier = Modifier
                            .size(200.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onDoubleTap = {
                                        val gmmIntentUri = Uri.parse("geo:0,0?q=台中市西屯區臺灣大道二段761號")
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
                    painter = painterResource(id = R.drawable.logo),
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
                        text = { Text("首頁") },
                        onClick = { navController.navigate("JumpFirst") })

                    DropdownMenuItem(
                        text = { Text("食物銀行地圖") },
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