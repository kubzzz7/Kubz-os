package com.kubzos.launcher

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.kubzos.launcher.model.AppInfo
import com.kubzos.launcher.ui.HomeScreen
import com.kubzos.launcher.ui.theme.KubzOSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apps = loadInstalledApps()
        setContent {
            var dark by remember { mutableStateOf(false) }
            KubzOSTheme(darkTheme = dark) {
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen(apps = apps, onToggleTheme = { dark = !dark }, isDark = dark)
                }
            }
        }
    }

    private fun loadInstalledApps(): List<AppInfo> {
        val pm = packageManager
        val launchIntent = android.content.Intent(android.content.Intent.ACTION_MAIN, null)
            .addCategory(android.content.Intent.CATEGORY_LAUNCHER)
        val list = pm.queryIntentActivities(launchIntent, 0)
        return list.map {
            val label = it.loadLabel(pm).toString()
            val packageName = it.activityInfo.packageName
            val icon = it.loadIcon(pm)
            AppInfo(packageName = packageName, label = label, icon = icon)
        }.sortedBy { it.label.lowercase() }
    }
}
