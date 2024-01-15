package com.n3t0l0b0.blogspot.mpc.view.jetpackcomposepermissions

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.n3t0l0b0.blogspot.mpc.view.jetpackcomposepermissions.ui.theme.JetpackComposePermissionsTheme

class MainActivity : ComponentActivity() {

    private val permissionRequired = Manifest.permission.WRITE_EXTERNAL_STORAGE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePermissionsTheme {

                var showPermissionDialog by remember { mutableStateOf(false) }

                val storagePermissionResultLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { isGranted ->
                        showPermissionDialog = !isGranted
                    }
                )

                Scaffold { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                        Button(onClick = {
                            if (shouldShowRequestPermissionRationale(permissionRequired)) {
                                showPermissionDialog = true
                            } else {
                                storagePermissionResultLauncher.launch(permissionRequired)
                            }
                        }) {
                            Text(text = "Request one permission")
                        }
                    }
                }

                if (showPermissionDialog) {
                    PermissionDialog(StoragePermissionTextProvider(),
                        isPermanentlyDeclined = !shouldShowRequestPermissionRationale(permissionRequired),
                        dialogTitle = "Permission required",
                        onDismiss = { showPermissionDialog = false },
                        onOkClick = {
                            storagePermissionResultLauncher.launch(permissionRequired)
                            showPermissionDialog = false
                        },
                        onGoToAppSettingsClick = {
                            openAppSettings()
                            showPermissionDialog = false
                        }
                    )
                }
            }
        }
    }
    private fun openAppSettings() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        )
        startActivity(intent)
    }
}



