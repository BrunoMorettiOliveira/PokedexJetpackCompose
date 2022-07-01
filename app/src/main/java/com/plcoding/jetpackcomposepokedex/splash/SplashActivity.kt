package com.plcoding.jetpackcomposepokedex.splash

import android.os.Bundle
import android.view.MotionEvent
import android.view.animation.OvershootInterpolator
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.plcoding.jetpackcomposepokedex.R
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect as LaunchedEffect1


class SplashActivity : AppCompatActivity() {
    @Composable
    fun splashScreen(
        navController: NavController,
        ) {
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

            delay(4000L)
            scaleLogo.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = {
                        OvershootInterpolator(0f).getInterpolation(it)
                    }
            ))

            navController.navigate("pokemon_list_screen")
            this@SplashActivity.finish()

        }
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
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
                        .offset(y = screenHeight/10),
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
                )

            }

        }
    }

    @Preview
    @Composable
    fun splashPreview() {
        splashScreen(NavController(this@SplashActivity))
    }
}
