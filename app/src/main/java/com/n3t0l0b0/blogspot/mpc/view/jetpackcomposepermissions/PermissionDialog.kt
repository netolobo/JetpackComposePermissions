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
    permissionDialogTextProvider: PermissionDialogTextProvider,
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
                    text = permissionDialogTextProvider.getTitle(),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = permissionDialogTextProvider.getDescription(isPermanentlyDeclined)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = {
                    if (isPermanentlyDeclined) {
                        onGoToAppSettingsClick()
                    } else {
                        onOkClick()
                    }
                }) {
                    Text(text = permissionDialogTextProvider.getButtonLabel(isPermanentlyDeclined))
                }
            }
        }
    }
}


@Preview
@Composable
fun PermissionDialogPreview() {
    JetpackComposePermissionsTheme {
        PermissionDialog(
            permissionDialogTextProvider = StoragePermissionDialogTextProvider(),
            isPermanentlyDeclined = false,
            onDismiss = {  },
            onOkClick = {  },
            onGoToAppSettingsClick = {}
        )
    }
}