package com.example.finalburgerrestaurant

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
import androidx.compose.ui.draw.clip
import coil.compose.rememberAsyncImagePainter





@Composable
fun SplashScreen(navController: NavController) {
    val backgroundColor = Color(0xFFFBC02D) // melted cheese yellow
    val circleColor = Color.Black
    val textColor = Color.Black

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        /* 🔵 Quarter Circle – Top Right Corner
        Canvas(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopEnd)
        ) {
            // Black outline
            drawArc(
                color = Color.Black,
                startAngle = 270f,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(width = 12f)
            )
            // Background fill
            drawArc(
                color = backgroundColor,
                startAngle = 270f,
                sweepAngle = 90f,
                useCenter = true
            )
        }

        // 🔵 Quarter Circle – Bottom Left Corner
        Canvas(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomStart)
        ) {
            // Black outline
            drawArc(
                color = Color.Black,
                startAngle = 90f,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(width = 12f)
            )
            // Background fill
            drawArc(
                color = backgroundColor,
                startAngle = 90f,
                sweepAngle = 90f,
                useCenter = true
            )
        }*/

        // 👇 Splash Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Logo circle with big 'B'
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(circleColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "B",
                    fontSize = 100.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = backgroundColor
                )
            }

            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Welcome ",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "where burgers meet bold flavor",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(70.dp))

            Button(
                onClick = { navController.navigate("signup") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .padding(horizontal = 70.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = backgroundColor
                )
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                        navController.navigate("home")
                    } else {
                        Toast.makeText(context, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("signup") }) {
            Text("Don't have an account? Sign up")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val meltedCheese = Color(0xFFFBC02D)
    val black = Color.Black

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(meltedCheese)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Back Arrow
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = meltedCheese,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(black)
                        .padding(8.dp)
                        .clickable { navController.navigate("splash") }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Logo
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(black),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "B",
                    fontSize = 100.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = meltedCheese
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Sign Up Text
            Text(
                text = "SIGN UP",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = black
            )

            Spacer(modifier = Modifier.height(4.dp))

            TextButton(onClick = { navController.navigate("login") }) {
                Text(
                    text = "Already have an account? Log in",
                    fontSize = 14.sp,
                    color = black
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Email Input
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email", color = black) },
                textStyle = LocalTextStyle.current.copy(color = black),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp)),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Input
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password", color = black) },
                textStyle = LocalTextStyle.current.copy(color = black),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp)),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Sign Up Button
            Button(
                onClick = {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Registration Successful!", Toast.LENGTH_SHORT).show()
                                navController.navigate("home")
                            } else {
                                Toast.makeText(context, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                colors = ButtonDefaults.buttonColors(containerColor = black),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = meltedCheese
                )
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val meltedCheese = Color(0xFFFBC02D)
    val black = Color.Black

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(meltedCheese)
            .padding(16.dp)
    ) {
        // Profile Icon Top Right
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = rememberAsyncImagePainter("file:///android_asset/images/profile.png"),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(32.dp) // made it a bit bigger than 12.dp
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Hero Container with image, text, and button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Craving something delicious?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = black
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { /* Handle order */ },
                    colors = ButtonDefaults.buttonColors(containerColor = black),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Order Now", color = meltedCheese)
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = rememberAsyncImagePainter("file:///android_asset/images/burger-4.png"),
                contentDescription = "Burger",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Category Section
        Text(
            text = "Category",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = black
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            CategoryItem("file:///android_asset/images/burger1.jpg", "Burgers", Modifier.weight(1f))
            CategoryItem("file:///android_asset/images/drink1.jpg", "Drinks", Modifier.weight(1f))
            CategoryItem("file:///android_asset/images/cake1.png", "Desserts", Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Popular Items Title
        Text(
            text = "Popular Items",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = black
        )
        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = rememberAsyncImagePainter("file:///android_asset/images/burger2.jpg"),
            contentDescription = "Popular Item",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = rememberAsyncImagePainter("file:///android_asset/images/burger3.jpg"),
            contentDescription = "Popular Item",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = rememberAsyncImagePainter("file:///android_asset/images/burger4.jpg"),
            contentDescription = "Popular Item",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

@Composable
fun CategoryItem(imagePath: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier, // <- This will safely accept weight
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(imagePath),
            contentDescription = label,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = label, color = Color.Black, fontWeight = FontWeight.SemiBold)
    }
}

/*@Composable
fun MenuScreen() {
    CenteredBox(" Menu")
}*/

@Composable
fun DetailsScreen() {
    CenteredBox(" Details")
}

@Composable
fun CartScreen() {
    CenteredBox(" Cart")
}

@Composable
fun CheckoutScreen() {
    CenteredBox(" Checkout")
}

// Helper composable
@Composable
fun CenteredBox(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(context, "Logged out!", Toast.LENGTH_SHORT).show()
            navController.navigate("login") {
                popUpTo("home") { inclusive = true } // clears backstack
            }
        }) {
            Text("Logout")
        }
    }
}