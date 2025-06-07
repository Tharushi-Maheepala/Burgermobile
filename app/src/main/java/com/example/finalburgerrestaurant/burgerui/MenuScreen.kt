package com.example.finalburgerrestaurant.ui

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.finalburgerrestaurant.model.Burger
import com.example.finalburgerrestaurant.viewmodel.BurgerViewModel
import com.example.finalburgerrestaurant.viewmodel.BurgerViewModelFactory
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import coil.request.ImageRequest
import coil.compose.AsyncImagePainter

@Composable
fun MenuScreen() {
    val context = LocalContext.current
    val viewModel: BurgerViewModel = viewModel(factory = BurgerViewModelFactory(context.applicationContext as Application))

    val burgers = viewModel.burgers.filter { it.category == "burger" }
    val beverages = viewModel.burgers.filter { it.category == "beverage" }
    val desserts = viewModel.burgers.filter { it.category == "dessert" }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFD966) // melted cheese background 🍔🧀
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "Category",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

            // Burgers Section
            item { SectionTitle("Burger") }
            items(burgers) { item -> MenuItemCard(item) }

            // Beverages Section
            item { SectionTitle("Beverage") }
            items(beverages) { item -> MenuItemCard(item) }

            // Desserts Section
            item { SectionTitle("Dessert") }
            items(desserts) { item -> MenuItemCard(item) }

            // Offline message
            if (viewModel.isOffline.value) {
                item {
                    Text(
                        "You're now in offline mode. Data loaded using local storage.",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .padding(vertical = 8.dp)
    )
}

@Composable
fun MenuItemCard(item: Burger) {
    val context = LocalContext.current

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(context)
            .data("file:///android_asset/${item.image}")
            .build()
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(item.name, style = MaterialTheme.typography.titleMedium)
                Text(item.description, style = MaterialTheme.typography.bodySmall)
                Text("Price: $${item.price}", style = MaterialTheme.typography.bodySmall)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { /* TODO: Add to cart logic */ },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Cart")
                    }
                }
            }
        }
    }
}
