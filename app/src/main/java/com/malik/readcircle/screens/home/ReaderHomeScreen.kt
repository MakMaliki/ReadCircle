package com.malik.readcircle.screens.home

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.malik.readcircle.navigation.ReaderScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ReaderHomeScreen(navController: NavController){

    Scaffold(topBar = {

                      ReadAppBar(title = "A.Reader", navController = navController )
    }, floatingActionButton = {
        FABContent {
            
        }
    }) {
//Content
        Surface( modifier = Modifier.fillMaxSize()) {
            //HomeContent
        }
    }

}

@Composable
fun FABContent(onTap: () -> Unit) {

    FloatingActionButton(onClick = { onTap()},
        shape = RoundedCornerShape(56.dp),
        backgroundColor = Color(0xFF008EfF)
    ){
        
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add a book", tint = Color.White)
    }

}

@Composable

fun ReadAppBar(title:String,showProfile:Boolean=true,navController: NavController){

    TopAppBar(
        title = {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    if (showProfile){
                        Icon(imageVector = Icons.Default.Face,
                            contentDescription = "Logo",
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .scale(1f),
                            )
                    }
                    
                    Text(text = title, color = Color.Red.copy(alpha = 0.7f),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )
                    
                    Spacer(modifier = Modifier.width(150.dp))


                }
        },
        actions = {
            IconButton(onClick = {

                FirebaseAuth.getInstance().signOut().run {
                    navController.navigate(ReaderScreens.LoginScreen.name)
                }
            }) {

                Icons.Default.Close


            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )


}
