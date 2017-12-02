package app.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import app.models.Movie
import app.repositories.MovieRepository


/**
 * @author Krishna Reddy
 * Rest controller to handle the movie related requests
 * */
@RestController
@CrossOrigin
@RequestMapping(Array("/api/list"))
class MovieController(@Autowired movieRepository: MovieRepository) {

  /**
   * To get the list of movies information
   * */
  @GetMapping
  def getAllMovies() = {
    movieRepository.findAll().iterator()
  }

  /**
   * To get movie information based on movie id
   * */
  @GetMapping(Array("/{id}"))
  def getMovie(@PathVariable id: Long): Movie = {
    movieRepository.findOne(id)
  }

  /**
   * To add new movie information 
   * */
  @PostMapping
  def addMovie(@RequestBody movie: Movie): String = {
    movieRepository.save(movie)
    "Saved: " + movie.toString
  }

  /**
   * To delete movie information based on movie id
   * */
  @DeleteMapping(Array("/{id}"))
  def deleteMovie(@PathVariable id: Long): String = {
    movieRepository.delete(id)
    "Deleted: " + id
  }

}
