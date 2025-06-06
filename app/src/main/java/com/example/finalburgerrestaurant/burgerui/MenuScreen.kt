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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.finalburgerrestaurant.viewmodel.BurgerViewModel
import com.example.finalburgerrestaurant.viewmodel.BurgerViewModelFactory

@Composable
fun MenuScreen() {
    val context = LocalContext.current
    val viewModel: BurgerViewModel = viewModel(factory = BurgerViewModelFactory(context.applicationContext as Application))

    LazyColumn {
        items(viewModel.burgers) { burger ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = rememberAsyncImagePainter(burger.image),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(burger.name, style = MaterialTheme.typography.titleLarge)
                        Text(burger.description, style = MaterialTheme.typography.bodyMedium)
                        Text("$${burger.price}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}