package com.plcoding.jetpackcomposepokedex.splash

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.plcoding.jetpackcomposepokedex.MainActivity
import com.plcoding.jetpackcomposepokedex.R
import com.plcoding.jetpackcomposepokedex.ui.theme.JetpackComposePokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect as LaunchedEffect1

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash_screen"
                ) {
                    composable("pokemon_list_screen") {
                        startActivity(
                            Intent(
                                this@SplashActivity,
                                MainActivity::class.java
                            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        )
                    }
                    composable(route = "splash_screen") {
                        splashScreen(navController = navController)
                    }

                }
            }

        }
    }

    @Composable
    fun splashScreen(
        navController: NavController,
        ) {
        lockScreen(true)
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp
        val scaleLogoDiv = 3
        val scaleLoadingDiv = 12

        val scaleLogo = remember {
            Animatable(0f)
        }
        val scaleLoading = remember {
            Animatable(0f)
        }
        LaunchedEffect1(key1 = true) {
            scaleLogo.animateTo(
                targetValue = 1.4f,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    }
                )
            )
            scaleLoading.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    }
                )
            )

            delay(800L)
            scaleLogo.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = {
                        OvershootInterpolator(0f).getInterpolation(it)
                    }
            ))

            navController.navigate("pokemon_list_screen")
            lockScreen(true)

        }
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize().testTag("BackGround")
        ) {

            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize()
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                    modifier = Modifier
                        .scale(scaleLogo.value)
                        .width(screenWidth / scaleLogoDiv)
                        .height(screenHeight / scaleLogoDiv)
                        .offset(y = screenHeight/10)
                        .testTag("Logo"),
                    contentDescription = "splash_logo"
                )
            }

            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = -screenHeight / 20)
            )
            {
                CircularProgressIndicator(
                    color = Color.DarkGray,
                    modifier = Modifier
                        .scale(scaleLoading.value)
                        .width(screenWidth / scaleLoadingDiv)
                        .height(screenHeight / scaleLoadingDiv)
                        .testTag("CircularLoading")
                )

            }

        }
    }

    private fun lockScreen(
        isLocked: Boolean
    ) {
        if (isLocked) {
            this@SplashActivity.window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
            this@SplashActivity.window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            )
        } else {
            this@SplashActivity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            this@SplashActivity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        }
    }

    @Preview
    @Composable
    fun splashPreview() {
        splashScreen(NavController(this@SplashActivity))
    }
}
