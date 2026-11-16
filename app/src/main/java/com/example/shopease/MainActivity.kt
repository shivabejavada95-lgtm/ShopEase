package com.example.shopease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shopease.ui.screens.DashboardScreen
import com.example.shopease.ui.theme.ShopEaseTheme
import kotlinx.coroutines.delay

private object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT
            )
        )

        setContent {
            ShopEaseTheme {
                ShopEaseApp()
            }
        }
    }
}

@Composable
fun ShopEaseApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Routes.SPLASH) {
            LaunchedEffect(Unit) {
                delay(2500L)

                navController.navigate(Routes.HOME) {
                    // Prevent returning to splash with the Back button.
                    popUpTo(Routes.SPLASH) {
                        inclusive = true
                    }

                    launchSingleTop = true
                }
            }

            ShopEaseSplashScreen()
        }

        composable(route = Routes.HOME) {
            DashboardScreen()
        }
    }
}

@Composable
fun ShopEaseSplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF172554),
                        Color(0xFF4338CA),
                        Color(0xFF9333EA),
                        Color(0xFFDB2777)
                    )
                )
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color.White.copy(alpha = 0.07f),
                radius = size.width * 0.65f,
                center = Offset(
                    x = size.width * 0.95f,
                    y = size.height * 0.12f
                )
            )

            drawCircle(
                color = Color.White.copy(alpha = 0.06f),
                radius = size.width * 0.55f,
                center = Offset(
                    x = size.width * 0.05f,
                    y = size.height * 0.85f
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(112.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.14f),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.30f),
                        shape = RoundedCornerShape(30.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                ShoppingBagLogo()
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "ShopEase",
                fontSize = 44.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-1).sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Discover more. Shop with ease.",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.90f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            CircularProgressIndicator(
                modifier = Modifier.size(28.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
        }
    }
}

@Composable
private fun ShoppingBagLogo() {
    Canvas(modifier = Modifier.size(64.dp)) {
        val lineWidth = 3.dp.toPx()

        drawRoundRect(
            color = Color.White,
            topLeft = Offset(
                x = size.width * 0.18f,
                y = size.height * 0.32f
            ),
            size = Size(
                width = size.width * 0.64f,
                height = size.height * 0.56f
            ),
            cornerRadius = CornerRadius(
                x = 7.dp.toPx(),
                y = 7.dp.toPx()
            ),
            style = Stroke(width = lineWidth)
        )

        drawArc(
            color = Color.White,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(
                x = size.width * 0.34f,
                y = size.height * 0.10f
            ),
            size = Size(
                width = size.width * 0.32f,
                height = size.height * 0.44f
            ),
            style = Stroke(width = lineWidth)
        )
    }
}