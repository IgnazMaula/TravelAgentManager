/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* MovieListModel.java
* Description : create costum list model for
* movie list

* @author Ignaz Maula Ibrahim
*/

import java.util.*;
import javax.swing.*;

public class MovieListModel extends AbstractListModel {
	//ArrayList to be list
	private ArrayList<Movie> movieArray;

	public MovieListModel() {
		setMovie(new ArrayList<Movie>());
	}
	//constructor that require ArrayList that want to be display
	public MovieListModel(ArrayList<Movie> movieArray) {
		setMovie(movieArray);
	}
	//set the movie array
	public void setMovieArray(ArrayList<Movie> movieArray) {
		this.movieArray = movieArray;
	}
	//get the movie array
	public ArrayList<Movie> getMovie() {
		return movieArray;
	}
	//set the movie array
	public void setMovie(ArrayList<Movie> array) {
		movieArray = array;
		int index = this.getSize() - 1;
        fireContentsChanged(this, 0, index);
	}
	//get element at the selected row
	public Object getElementAt(int index) {
		return getMovie().get(index);
	}
	//get size of the movie array
	public int getSize() {
		return getMovie().size();
	}
	//add movie to list
	public void addElement(Movie movie) {
		int index = getSize();
		getMovie().add(movie);
		fireIntervalAdded(this, index, index);
	}
	//remove movie from the list
	public Movie removeElement(Movie movie) {
		int index = getMovie().indexOf(movie);
		if (index != -1) {
			getMovie().remove(movie);
			fireIntervalRemoved(this, index, index);
		}
		return movie;
	}
	//set movie
	public Movie set(int index, Movie movie) {
		Movie oldMovie = (Movie) getMovie().get(index);
		getMovie().set(index, movie);
		fireContentsChanged(this, index, index);
		return oldMovie;
	}
	//clear movie array
	public void clear() {
		int index = getSize() - 1;
		getMovie().clear();
		if (index >= 0)
			fireIntervalRemoved(this, 0, index);
	}
	//check if movie array content selected movie
	public boolean contains(Movie movie) {
		return getMovie().contains(movie);
	}

}