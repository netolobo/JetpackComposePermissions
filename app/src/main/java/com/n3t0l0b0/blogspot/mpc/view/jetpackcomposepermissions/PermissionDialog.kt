package com.n3t0l0b0.blogspot.mpc.view.jetpackcomposepermissions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.n3t0l0b0.blogspot.mpc.view.jetpackcomposepermissions.ui.theme.JetpackComposePermissionsTheme

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(shape = RoundedCornerShape(20.dp)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Permission required",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = permissionTextProvider.getDescription(isPermanentlyDeclined)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = {
                    if (isPermanentlyDeclined) {
                        onGoToAppSettingsClick()
                    } else {
                        onOkClick()
                    }
                }) {
                    Text(text = if (isPermanentlyDeclined) "Go to settings" else "OK")
                }


            }
        }
    }

}

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}

class StoragePermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined the app to save image on your phone gallery. You can go to the app settings to grant it."
        } else {
            "This app need to access to you phone gallery so it can save the images you create"
        }
    }
}



@Preview
@Composable
fun PermissionDialogPreview() {
    JetpackComposePermissionsTheme {
        PermissionDialog(
            permissionTextProvider = StoragePermissionTextProvider(),
            isPermanentlyDeclined = false,
            onDismiss = { /*TODO*/ },
            onOkClick = { /*TODO*/ }) {

        }
    }
}