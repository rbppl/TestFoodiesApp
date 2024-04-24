package com.rbppl.testfoodiesapp.main
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rbppl.testfoodiesapp.R
import com.rbppl.testfoodiesapp.main.domain.model.LoadState
import com.rbppl.testfoodiesapp.main.ui.component.Buttons
import com.rbppl.testfoodiesapp.main.ui.screen.cart.CartScreen
import com.rbppl.testfoodiesapp.main.ui.screen.catalog.CatalogScreen
import com.rbppl.testfoodiesapp.main.ui.screen.catalog.CatalogViewModel
import com.rbppl.testfoodiesapp.main.ui.screen.details.InfoScreen
import com.rbppl.testfoodiesapp.main.ui.theme.FoodiesTestTheme
import com.rbppl.testfoodiesapp.main.ui.theme.StatusBarHelper
import com.rbppl.testfoodiesapp.main.util.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val avm: ActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply { setKeepOnScreenCondition { false } }
        setContent {
            FoodiesTestTheme {
                val loading by avm.updateState.collectAsStateWithLifecycle()
                val progress = remember { avm.splashProgress }
                val catalogViewModel: CatalogViewModel = hiltViewModel()

                Box(modifier = Modifier.fillMaxSize()) {
                    if (progress.floatValue == 1f) Content(catalogViewModel)
                    if (progress.floatValue != 1f || loading !is LoadState.Loaded) {
                        SplashScreen(progress, loading is LoadState.Error) { avm.update() }
                        StatusBarHelper(backgroundColor = MaterialTheme.colorScheme.primary)
                    } else {
                        StatusBarHelper(backgroundColor = MaterialTheme.colorScheme.background)
                    }
                }
            }
        }
    }
    @Composable
    private fun Content(catalogViewModel: CatalogViewModel) {
        val navController = rememberNavController()
        val navigator = Navigator(navController)
        NavHost(navController = navController, startDestination = navigator.catalogRoute) {
            composable(navigator.catalogRoute) { CatalogScreen.Show(navigator = navigator, catalogViewModel) }
            composable(
                navigator.infoRoute,
                arguments = listOf(navArgument(navigator.itemIdArg) { type = NavType.IntType })
            ) {
                val itemID = it.arguments?.getInt(navigator.itemIdArg)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    InfoScreen.Show(itemID = itemID ?: 0, navigator = navigator)
                }
            }
            composable(navigator.cartRoute) { CartScreen.Show(navigator = navigator) }
        }
    }
    @Composable
    private fun SplashScreen(
        progress: MutableFloatState,
        retryButton: Boolean = false,
        onUpdate: () -> Unit
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.splash_screen_animation_new)
        )
        val anim = rememberLottieAnimatable()
        var retry by remember { mutableIntStateOf(0) }
        LaunchedEffect(key1 = retry) {
            delay(200)
            anim.animate(composition)
        }
        val animationProgress by animateLottieCompositionAsState(composition = composition)
        progress.floatValue = animationProgress
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            LottieAnimation(
                composition = anim.composition,
                progress = { animationProgress },
                renderMode = RenderMode.HARDWARE,
            )
            if (retryButton) {
                Buttons.BasicButton(
                    text = "Retry", onClick = {
                        progress.floatValue = 0f
                        retry++
                        onUpdate()
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(16.dp),
                    containerColor = MaterialTheme.colorScheme.background
                )
            }
        }
    }
    override fun onDestroy() {
        if (isFinishing) runBlocking { avm.clear() }
        super.onDestroy()
    }
}
