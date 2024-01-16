package com.n3t0l0b0.blogspot.mpc.view.jetpackcomposepermissions

class StoragePermissionDialogTextProvider : PermissionDialogTextProvider {
    override fun getTitle(): String = "Permission required"
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined the app to access your phone gallery. You can go to the app settings to grant it."
        } else {
            "This app need to access your camera so it can capture image and videos to use on the app."
        }
    }
    override fun getButtonLabel(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) "Go to settings" else "OK"
    }
}