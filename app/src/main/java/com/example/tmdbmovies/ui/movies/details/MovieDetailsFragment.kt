package com.example.tmdbmovies.ui.movies.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tmdbmovies.databinding.FragmentMovieDetailsBinding
import com.example.tmdbmovies.ui.movies.details.composable.MovieDetailsScreen
import com.example.tmdbmovies.ui.theme.TmdbMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    // ViewBinding
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    //ViewModel
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val receivedMovieId = arguments?.getInt("movieId") ?: 0
        viewModel.initMovieDetails(receivedMovieId)
        setUpViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.navigateBack.observe(viewLifecycleOwner) {navigateBack ->
            if (navigateBack) {
                findNavController().navigateUp()
                viewModel.turnOff()
            }
        }
    }

    private fun setUpViews() {
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                TmdbMoviesTheme {
                    MovieDetailsScreen(viewModel = viewModel)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}