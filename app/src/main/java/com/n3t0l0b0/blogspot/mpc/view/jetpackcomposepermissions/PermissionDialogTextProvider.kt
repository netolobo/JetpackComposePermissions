package com.n3t0l0b0.blogspot.mpc.view.jetpackcomposepermissions
interface PermissionDialogTextProvider {
    fun getTitle() : String
    fun getDescription(isPermanentlyDeclined: Boolean): String
    fun getButtonLabel(isPermanentlyDeclined: Boolean): String
}