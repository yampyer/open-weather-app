package co.jeanpidev.openweatherapp.ui.search

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.jeanpidev.openweatherapp.R
import co.jeanpidev.openweatherapp.base.BaseActivity
import co.jeanpidev.openweatherapp.databinding.ActivitySearchBinding
import co.jeanpidev.openweatherapp.model.Coordinates
import co.jeanpidev.openweatherapp.ui.current.CurrentActivity
import co.jeanpidev.openweatherapp.utils.EXTRA_CURRENT_WEATHER
import co.jeanpidev.openweatherapp.utils.extension.app
import co.jeanpidev.openweatherapp.repository.Result
import co.jeanpidev.openweatherapp.utils.ERROR_NOT_FOUND
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

class SearchActivity : BaseActivity() {

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var viewModel: SearchViewModel

    private val isLocationEnabled: Boolean
        get() {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        }

    private val binding: ActivitySearchBinding by lazy { ActivitySearchBinding.inflate(layoutInflater) }

    private val locationPermissions = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        app.component.searchComponent().create().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        initUI()
        initObservers()
    }

    private fun initUI() {
        binding.btnSearchIcon.setOnClickListener {
            viewModel.getQueryValue().value = binding.etInputCity.text.toString()
        }

        binding.btnCurrentLocation.setOnClickListener {
            if (checkLocationEnable())
                getLastKnownLocation()
        }

        binding.tvMontevideo.setOnClickListener {
            viewModel.getQueryValue().value = getString(R.string.montevideo)
        }

        binding.tvLondon.setOnClickListener {
            viewModel.getQueryValue().value = getString(R.string.london)
        }

        binding.tvBuenosAires.setOnClickListener {
            viewModel.getQueryValue().value = getString(R.string.buenos_aires)
        }

        binding.tvSaoPaulo.setOnClickListener {
            viewModel.getQueryValue().value = getString(R.string.sao_paulo)
        }

        binding.tvMunich.setOnClickListener {
            viewModel.getQueryValue().value = getString(R.string.munich)
        }
    }

    private fun initObservers() {
        viewModel.getCurrentWeather().observe(this, Observer {
            when (it) {
                is Result.Progress -> toggleProgress(it.loading)
                is Result.Failure -> {
                    toggleProgress(false)
                    if (it.e.message == ERROR_NOT_FOUND)
                        showError(getString(R.string.no_city_found))
                    else showError(getString(R.string.an_error_has_occurred))
                }
                is Result.Success -> {
                    toggleProgress(false)
                    val intent = Intent(this, CurrentActivity::class.java).apply {
                        putExtra(EXTRA_CURRENT_WEATHER, it.data)
                    }
                    startActivity(intent)
                }
            }
        })
        viewModel.getQueryValue().observe(this, Observer {
            if (it.isNotEmpty())
                viewModel.getWeatherCity(it)
            else
                showError(getString(R.string.please_enter_city_name))
        })
    }

    private fun checkLocationPermission(): Boolean {
        locationPermissions.forEach {
            if (ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION)) {
            AlertDialog.Builder(this)
                .setMessage(getString(R.string.location_needed))
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    ActivityCompat.requestPermissions(this, locationPermissions, REQUEST_LOCATION_PERMISSION)
                }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
        }
        else {
            ActivityCompat.requestPermissions(this, locationPermissions, REQUEST_LOCATION_PERMISSION)
        }
    }

    private fun getLastKnownLocation() {
        if (!checkLocationPermission()) {
            requestLocationPermission()
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val coordinates = Coordinates(it.longitude, it.latitude)
                viewModel.getWeatherLocation(coordinates)
            }
        }
    }

    private fun checkLocationEnable(): Boolean {
        if (!isLocationEnabled)
            showAlert()
        return isLocationEnabled
    }

    private fun showAlert() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(getString(R.string.enable_location))
            .setMessage(getString(R.string.location_settings_are_off))
            .setPositiveButton(getString(R.string.location_settings)) { _, _ ->
                val myIntent = Intent(ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(myIntent)
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
        dialog.show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastKnownLocation()
                } else {
                    showError(getString(R.string.permission_denied))
                }
                return
            }
        }
    }
}
