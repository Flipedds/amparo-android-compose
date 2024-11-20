package com.table.tatu.amparo.ui.viewmodels

import android.location.Location
import androidx.lifecycle.ViewModel
import com.table.tatu.amparo.services.LocationService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AmparoScreenViewModel(
    private val locationService: LocationService
) : ViewModel() {
    private val _permissionGranted = MutableStateFlow(false)
    val permissionGranted: StateFlow<Boolean> = _permissionGranted

    private val _location = MutableStateFlow<Location?>(null)
    val location: StateFlow<Location?> = _location

    fun checkPermission() {
        _permissionGranted.value = locationService.hasLocationPermission()
    }

    fun fetchLocation() {
        if (_permissionGranted.value) {
            locationService.getLastLocation { _location.value = it }
        }
    }
}