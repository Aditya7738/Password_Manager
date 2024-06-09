@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MovableContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.passwordmanager.ui.theme.PasswordManagerTheme
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Serializable
class MainActivity : ComponentActivity() {

//    @Serializable
//    object ScreenA
//
//    @Serializable
//    object ScreenB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordManagerTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = Color.Black
//                ) {
//                    PinLockScreen()
//                }


                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenA
                ) {
//                    composable<ScreenA> {
//                        PinLockScreen(navController)
//                    }
//
//                    composable<ScreenB> {
//                        PasswordListScreen()
//                    }

                    composable<ScreenA> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = {
                                navController.navigate(ScreenB(
                                    name = null,
                                    age = 25
                                ))
                            }) {
                                Text(text = "Go to screen B")
                            }
                        }
                    }
                    composable<ScreenB> {
                        val args = it.toRoute<ScreenB>()
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "${args.name}, ${args.age} years old")
                        }
                    }

                }
            }
        }
    }
}

@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val name: String?,
    val age: Int
)


const val password = "1234"
const val pinSize = 4


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinLockScreen(navController: NavHostController) {

    val inputPin = remember { mutableStateListOf<Int>() }

    val error = remember { mutableStateOf<String>("") }

    val showSuccess = remember { mutableStateOf<Boolean>(false) }

    val context = LocalContext.current

    if (inputPin.size == 4) {
        LaunchedEffect(key1 = true) {
            delay(300)

            if (inputPin.joinToString("") == password) {
                showSuccess.value = true
                error.value = ""
            } else {
                inputPin.clear()
                error.value = "Wrong pin, Please retry!"
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(
            "Enter pin to unlock", color = Color.White,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = "", onValueChange = {

            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            (0 until pinSize).forEach {

                Icon(
                    painter =
                    if (inputPin.size > it)
                        painterResource(id = R.drawable.new_moon)
                    else
                        painterResource(id = R.drawable.oval), contentDescription = "pin",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp),
                    tint = Color.White
                )
            }
        }


        Text(
            text = error.value,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .wrapContentSize()

                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                (1..3).forEach {
                    PinKeyItem(onClick = { inputPin.add(it) }) {
                        Text(
                            text = it.toString(),
                            style = typography.headlineMedium,
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                (4..6).forEach {
                    PinKeyItem(onClick = { inputPin.add(it) }) {
                        Text(
                            text = it.toString(),
                            style = typography.headlineMedium,
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                }
            }

            Row(

                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                (7..9).forEach {
                    PinKeyItem(onClick = { inputPin.add(it) }) {
                        Text(
                            text = it.toString(),
                            style = typography.headlineMedium,
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                }
            }

//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ) {
//
//                PinKeyItem(onClick = { inputPin.add(0) }) {
//                    Text(
//                        text = "0",
//                        style = typography.headlineMedium,
//                        modifier = Modifier.padding(4.dp)
//                    )
//                }
//
//
//            }

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),

                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Success",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                           // navController.navigate(ScreenB)
                        },
                    tint = Color.White
                )

                PinKeyItem(onClick = { inputPin.add(0) }) {
                    Text(
                        text = "0",
                        style = typography.headlineMedium,
                        modifier = Modifier.padding(4.dp)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.baseline_backspace_24),
                    contentDescription = "Clear",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            if (inputPin.isNotEmpty()) {
                                inputPin.removeLast()
                            }
                        }
                )


            }


        }


    }

}

@Composable
fun PinKeyItem(
    onClick: () -> Unit,
    // modifier: Modifier = Modifier.padding(8.dp),
    shape: Shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
    backgroundColor: Color = Color.Gray,
    //MaterialTheme.colorScheme.onPrimary,
    contentColor: Color = Color.White,
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick },
//            .indication(interactionSource = Button(onClick = {}), indication = rememberRipple())
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        tonalElevation = elevation,
//        onClick = onClick,
//        role = Role.Button,
//        indication = rememberRipple()

    ) {

        CompositionLocalProvider(
//            LocalContentColor provides contentColor.alpha
        ) {
            ProvideTextStyle(
                MaterialTheme.typography.displayMedium
            ) {
                Box(
                    modifier = Modifier.defaultMinSize(minWidth = 64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    content()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    PinLockScreen(navController)
}