package com.skilldistillery.filmquery.data;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseAccessor {
	public Film findFilmById(int filmId) throws SQLException;

	public Actor findActorById(int actorId) throws SQLException;

	public List<Actor> findActorsByFilmId(int filmId) throws SQLException;

	public List<Film> findFilmByKeyword(String keyword) throws SQLException;

	public Actor createActor(Actor actor);

	public boolean saveActor(Actor actor);

	public boolean deleteActor(Actor actor);

	public Film createFilm(Film film);
	
	public boolean deleteFilm(Film film);

}
