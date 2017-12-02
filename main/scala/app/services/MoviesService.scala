package app.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import app.repositories.MovieRepository

/**
 * @author Krishna Reddy
 * Service to provide the movie repository
 */
@Service
class MoviesService(@Autowired val movieRepository: MovieRepository)