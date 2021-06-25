package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(
            this,
            MainViewModel.Factory(activity.application)
        ).get(MainViewModel::class.java)
    }

    private val asteroids = arrayListOf(
        Asteroid(
            id = 1L,
            codename = "Asteroid 1",
            isPotentiallyHazardous = true,
            absoluteMagnitude = 32.3213123,
            closeApproachDate = "2021-06-12",
            estimatedDiameter = 23124.12312,
            relativeVelocity = 2312.23123,
            distanceFromEarth = 231.21
        ),
        Asteroid(
            id = 2L,
            codename = "Asteroid 2",
            isPotentiallyHazardous = false,
            absoluteMagnitude = 32.3213123,
            closeApproachDate = "2021-06-12",
            estimatedDiameter = 23124.12312,
            relativeVelocity = 2312.23123,
            distanceFromEarth = 231.21
        ),
        Asteroid(
            id = 3L,
            codename = "Asteroid 3",
            isPotentiallyHazardous = false,
            absoluteMagnitude = 32.3213123,
            closeApproachDate = "2021-06-12",
            estimatedDiameter = 23124.12312,
            relativeVelocity = 2312.23123,
            distanceFromEarth = 231.21
        ),
        Asteroid(
            id = 4L,
            codename = "Asteroid 4",
            isPotentiallyHazardous = true,
            absoluteMagnitude = 32.3213123,
            closeApproachDate = "2021-06-12",
            estimatedDiameter = 23124.12312,
            relativeVelocity = 2312.23123,
            distanceFromEarth = 231.21
        ),
        Asteroid(
            id = 5L,
            codename = "Asteroid 5",
            isPotentiallyHazardous = true,
            absoluteMagnitude = 32.3213123,
            closeApproachDate = "2021-06-12",
            estimatedDiameter = 23124.12312,
            relativeVelocity = 2312.23123,
            distanceFromEarth = 231.21
        ),
        Asteroid(
            id = 6L,
            codename = "Asteroid 6",
            isPotentiallyHazardous = true,
            absoluteMagnitude = 32.3213123,
            closeApproachDate = "2021-06-12",
            estimatedDiameter = 23124.12312,
            relativeVelocity = 2312.23123,
            distanceFromEarth = 231.21
        ),
        Asteroid(
            id = 7L,
            codename = "Asteroid 7",
            isPotentiallyHazardous = false,
            absoluteMagnitude = 32.3213123,
            closeApproachDate = "2021-06-12",
            estimatedDiameter = 23124.12312,
            relativeVelocity = 2312.23123,
            distanceFromEarth = 231.21
        ),
        Asteroid(
            id = 8L,
            codename = "Asteroid 8",
            isPotentiallyHazardous = false,
            absoluteMagnitude = 32.3213123,
            closeApproachDate = "2021-06-12",
            estimatedDiameter = 23124.12312,
            relativeVelocity = 2312.23123,
            distanceFromEarth = 231.21
        ),
        Asteroid(
            id = 9L,
            codename = "Asteroid 9",
            isPotentiallyHazardous = true,
            absoluteMagnitude = 32.3213123,
            closeApproachDate = "2021-06-12",
            estimatedDiameter = 23124.12312,
            relativeVelocity = 2312.23123,
            distanceFromEarth = 231.21
        ),
        Asteroid(
            id = 10L,
            codename = "Asteroid 10",
            isPotentiallyHazardous = false,
            absoluteMagnitude = 32.3213123,
            closeApproachDate = "2021-06-12",
            estimatedDiameter = 23124.12312,
            relativeVelocity = 2312.23123,
            distanceFromEarth = 231.21
        ),
        Asteroid(
            id = 11L,
            codename = "Asteroid 11",
            isPotentiallyHazardous = false,
            absoluteMagnitude = 32.3213123,
            closeApproachDate = "2021-06-12",
            estimatedDiameter = 23124.12312,
            relativeVelocity = 2312.23123,
            distanceFromEarth = 231.21
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = AsteroidAdapter()
        binding.asteroidRecycler.adapter = adapter

        adapter.submitList(asteroids)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
