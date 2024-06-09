package com.example.passwordmanager

import android.annotation.SuppressLint
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.ui.theme.BlackPrimary
import com.example.passwordmanager.ui.theme.PasswordManagerTheme
import com.example.passwordmanager.ui.theme.WhitePrimary

//import androidx.compose.material3.remem


class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordManagerTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BlackPrimary
                ) {
                    PasswordListScreen()

                    // Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar() {
    TopAppBar(
        title = {
            Text(
                "Password Manager",

                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = WhitePrimary,
            titleContentColor = Color.Black
        )
    )
}

@Composable
fun CustomFABBtn(showBottomSheet: Boolean) {

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordListScreen() {
    var dataOfList: List<Array<String>> =
        listOf(
            arrayOf(
                "Google", "*****"
            ),
            arrayOf(
                "LinkedIn", "*****"
            ),
            arrayOf(
                "Twitter", "*****"
            ),
            arrayOf(
                "Facebook", "*****"
            ),
            arrayOf(
                "Instagram", "*****"
            ),
        )

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = { CustomTopAppBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showBottomSheet = true
                },
                modifier = Modifier.padding(10.dp),
                shape = RoundedCornerShape(10.dp),
                containerColor = Color.Blue,
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = "add password",
                    modifier = Modifier.size(size = FloatingActionButtonDefaults.LargeIconSize)
                )
            }
        }
    ) {
        LazyColumn {

            items(dataOfList) { dataArray ->
                Card(
                    onClick = {},
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,

                        )
//                    TopAppBarDefaults.mediumTopAppBarColors(
//                        containerColor = WhitePrimary,
//                        titleContentColor = Color.Black
//                    ),
                    ,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(width = 1.dp, color = Color.Gray)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = dataArray[0],
                                modifier = Modifier.padding(10.dp),
                                style = MaterialTheme.typography.labelLarge
                            )
                            Text(
                                text = dataArray[1],
                                color = Color.Gray,
                                modifier = Modifier.padding(10.dp),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }

                        Icon(painter = painterResource(R.drawable.baseline_chevron_right_24),
                            contentDescription = "Options",
                            modifier = Modifier.clickable { })

                    }

                }
            }
        }

    }

    if (showBottomSheet) {
        val placeHolder1 = "Account Name"
        val placeHolder2 = "Username/ Email"
        val placeHolder3 = "Password"
        var textState1 = remember { mutableStateOf("") }
        var textState2 = remember { mutableStateOf("") }
        var textState3 = remember { mutableStateOf("") }
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
//            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
//                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 5.dp)
//                ) {
//                items(placeHolders){placeHolder->
//                    BottomSheetItem(placeHolder)
//                }
//            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                TextField(
                    modifier = Modifier
                        .border(width = 2.dp, color = Color.Gray)
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                    value = textState1.value,
                    onValueChange = {
                        textState1.value = it
                    },
                    placeholder = { Text(text = placeHolder1, color = Color.Gray) },
                    shape = RoundedCornerShape(10.dp)

                )

                TextField(
                    modifier = Modifier
                        .border(width = 2.dp, color = Color.Gray)
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                    value = textState2.value,
                    onValueChange = {
                        textState2.value = it
                    },
                    placeholder = { Text(text = placeHolder2, color = Color.Gray) },
                    shape = RoundedCornerShape(10.dp)

                )

                TextField(
                    modifier = Modifier
                        .border(width = 2.dp, color = Color.Gray)
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                    value = textState3.value,
                    onValueChange = {
                        textState3.value = it
                    },
                    placeholder = { Text(text = placeHolder3, color = Color.Gray) },
                    shape = RoundedCornerShape(10.dp)

                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {

                    },
                    colors = ButtonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        disabledContentColor = Color.White,
                        disabledContainerColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Add New Account",
                        fontWeight = FontWeight.Bold,

                        )
                }
            }


        }
    }


}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BottomSheetItem(placeHolder: String) {
//
//}


@Preview(showBackground = true)
@Composable
fun PasswordListScreenPreview() {
    PasswordManagerTheme() {
        PasswordListScreen()
    }
}