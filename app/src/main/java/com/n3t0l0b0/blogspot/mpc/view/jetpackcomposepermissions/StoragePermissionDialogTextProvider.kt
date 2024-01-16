package com.n3t0l0b0.blogspot.mpc.view.jetpackcomposepermissions

class StoragePermissionDialogTextProvider : PermissionDialogTextProvider {
    override fun getTitle(): String = "Permission required"
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined the app to save image on your phone gallery. You can go to the app settings to grant it."
        } else {
            "This app need to access to you phone gallery so it can save the images you create"
        }
    }
    override fun getButtonLabel(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) "Go to settings" else "OK"
    }
}