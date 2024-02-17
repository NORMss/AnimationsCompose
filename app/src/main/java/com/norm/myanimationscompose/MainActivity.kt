package com.norm.myanimationscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.norm.myanimationscompose.ui.theme.MyAnimationsComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAnimationsComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var isVisible by remember {
                        mutableStateOf(false)
                    }
                    var isRound by remember {
                        mutableStateOf(false)
                    }
                    Column(Modifier.fillMaxSize()) {
                        Button(
                            onClick = {
                                isVisible = !isVisible
                                isRound = !isRound
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Toggle"
                            )
                        }

                        val transition = updateTransition(
                            targetState = isRound,
                            label = null
                        )
//                        val borderRadius by animateIntAsState(
//                            targetValue = if(isRound) 40 else 20,
//                            animationSpec =
//                            tween(
//                                durationMillis = 3000,
//                                delayMillis = 500,
//                            ),
////                            spring(
////                                dampingRatio = Spring.DampingRatioHighBouncy,
////                                stiffness = Spring.StiffnessVeryLow
////                            )
//                            label = "",
//                        )

//                        val borderRadius by transition.animateInt(
//                            transitionSpec = {
//                                tween(
//                                    durationMillis = 2000
//                                )
//                            },
//                            label = "borderRadius",
//                            targetValueByState = { isRound ->
//                                if (isRound) 100 else 0
//                            }
//                        )
//                        val color by transition.animateColor(
//                            transitionSpec = {
//                                tween(
//                                    durationMillis = 1000
//                                )
//                            },
//                            label = "color",
//                            targetValueByState = { isRound ->
//                                if (isRound) Color.Red else Color.Blue
//                            }
//                        )

//                        val repeatable = rememberInfiniteTransition(label = "")
//                        val color by repeatable.animateColor(
//                            initialValue = Color.Red,
//                            targetValue = Color.Green,
//                            animationSpec = infiniteRepeatable(
//                                animation = tween(
//                                    durationMillis = 2000
//                                ),
//                                repeatMode = RepeatMode.Reverse
//                            ),
//                            label = ""
//                        )


//                        Box(
//                            modifier = Modifier
//                                .size(256.dp)
//                                .clip(RoundedCornerShape(borderRadius))
//                                .background(color)
//                        )

//                        Box(
//                            modifier = Modifier
//                                .size(256.dp)
//                                .background(color)
//                        )

//                        AnimatedVisibility(
//                            visible = isVisible,
//                            enter = slideInHorizontally() + fadeIn(),
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .weight(1f)
//                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .background(Color.Red)
//                            )
//                        }

                        AnimatedContent(
                            targetState = isVisible,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            content = { isVisible ->
                                if (isVisible) {
                                    Box(modifier = Modifier.background(Color.Green))
                                } else {
                                    Box(
                                        modifier = Modifier.background(Color.Red)
                                    )
                                }
                            },
                            label = "",
                            transitionSpec = {
                                slideInHorizontally(
                                    initialOffsetX = {
                                        if (isVisible) it else -it
                                    }
                                ) togetherWith slideOutHorizontally(
                                    targetOffsetX = {
                                        if (isVisible) it else -it
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}