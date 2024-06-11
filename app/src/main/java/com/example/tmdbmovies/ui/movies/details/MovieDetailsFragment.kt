package com.example.tmdbmovies.ui.movies.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import com.example.tmdbmovies.databinding.FragmentMovieDetailsBinding
import com.example.tmdbmovies.ui.movies.details.composable.MovieDetailsScreen
import com.example.tmdbmovies.ui.theme.TmdbMoviesTheme

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
        setUpViews(receivedMovieId)
    }

    private fun setUpViews(id: Int) {
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                TmdbMoviesTheme {
                    MovieDetailsScreen(viewModel = viewModel, movieId = id)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}