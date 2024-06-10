package com.example.tmdbmovies.ui.movies.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tmdbmovies.R
import com.example.tmdbmovies.databinding.FragmentMoviesListBinding
import com.example.tmdbmovies.ui.movies.list.composable.MoviesListScreen
import com.example.tmdbmovies.ui.theme.TmdbMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    // ViewBinding
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    //ViewModel
    private val viewModel: MoviesListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        viewModel.initMoviesList()
    }

    private fun setUpViews() {
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                TmdbMoviesTheme {
                    MoviesListScreen(viewModel)
                }
            }
        }
    }

    private fun navigateToListFragment() {
        findNavController().navigate(R.id.action_moviesListFragment_to_movieDetailsFragment)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}