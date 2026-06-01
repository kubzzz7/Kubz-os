package com.kubzos.launcher.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kubzos.launcher.model.AppInfo
import androidx.core.graphics.drawable.toBitmap

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(apps: List<AppInfo>, onToggleTheme: () -> Unit, isDark: Boolean) {
    var query by remember { mutableStateOf("") }
    val filtered = remember(query, apps) {
        if (query.isBlank()) apps else apps.filter { it.label.contains(query, ignoreCase = true) }
    }
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        TopAppBar(
            title = { Text("KubzOS") },
            actions = {
                IconButton(onClick = onToggleTheme) {
                    Icon(Icons.Default.Palette, contentDescription = "Theme")
                }
            }
        )
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search apps") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filtered) { app ->
                Column(
                    modifier = Modifier
                        .width(72.dp)
                        .clickable {
                            val launchIntent = context.packageManager.getLaunchIntentForPackage(app.packageName)
                            if (launchIntent != null) {
                                context.startActivity(launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                            }
                        },
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val bmp = app.icon.toBitmap(72, 72)
                    Image(bitmap = bmp.asImageBitmap(), contentDescription = app.label, modifier = Modifier.size(72.dp))
                    Text(app.label, maxLines = 1, style = MaterialTheme.typography.caption)
                }
            }
        }
    }
}
