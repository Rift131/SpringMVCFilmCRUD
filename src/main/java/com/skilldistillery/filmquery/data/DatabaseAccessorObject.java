package com.skilldistillery.filmquery.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	String user = "student";
	String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {

		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT f.*, l.* FROM film f JOIN language l ON f.language_id=l.id WHERE f.id=?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		Film film = null;
		if (rs.next()) {
			// This do while loop is probably useless because there will only be one result
			do {
//				System.out.println(rs.getString("title") + " " + rs.getString("release_year") + " "
//						+ rs.getString("rating") + " " + rs.getString("description") + " " + rs.getString("name"));
				int filmID = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description") + ".";
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				String language = rs.getString("name");
				film = new Film(filmID, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, findActorsByFilmId(filmId), language, findFilmCategory(filmId));

			} while (rs.next());
		}
		stmt.close();
		conn.close();
		rs.close();
		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {

		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT * FROM actor a WHERE a.id=?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet rs = stmt.executeQuery();
		Actor actor = null;
		if (rs.next()) {
			// This do while loop is probably useless because there will only be one result
			do {
				System.out.println(
						rs.getString("id") + " " + rs.getString("first_name") + " " + rs.getString("last_name"));
				int actorID = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				actor = new Actor(actorID, firstName, lastName);

			} while (rs.next());
		} else {
			System.out.println("That id does not go to one of our actors");
		}
		stmt.close();
		conn.close();
		rs.close();
		return actor;
	}

	@Override
	public StringBuilder findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actorList = new ArrayList<>();
		StringBuilder actors = new StringBuilder();
		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT f.*, a.* FROM film f JOIN film_actor fa ON f.id=fa.film_id JOIN actor a ON fa.actor_id=a.id WHERE f.id=?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		Actor actor = null;
		while (rs.next()) {
			// This do while loop is probably useless because there will only be one result
			do {
			//	int actorID = rs.getInt("a.id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				actor = new Actor(firstName, lastName);
				actorList.add(actor);


				// doesn't show what language the film is in
//				System.out.println(rs.getString("title") + " " + rs.getString("release_year") + " "
//						+ rs.getString("rating") + " " + rs.getString("description"));
//				System.out.println(
//						rs.getString("a.id") + " " + rs.getString("first_name") + " " + rs.getString("last_name"));
				//int actorID = rs.getInt("a.id");
				
			} while (rs.next());
		}
		
		for (Actor actorArr : actorList) {
			
			String space = ", ";
			//actor = new Actor(actorID, firstName, lastName);
			actors.append(actorArr);
			actors.append(space);
		
		}
		
		if (actors.length() > 0) {
		actors.replace(actors.length() -2, actors.length(), ".");
		}
		
		
		
		
		
		stmt.close();
		conn.close();
		rs.close();
		System.out.println(actors);
		return actors;
	}

	public List<Film> findFilmByKeyword(String keyword) throws SQLException {
		List<Film> filmList = new ArrayList<>();

		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, keyword);
		stmt.setString(2, keyword);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			filmList.add(findFilmById(id));
		}
		System.out.println("dao ARRAYLIST" + filmList);
		stmt.close();
		conn.close();
		rs.close();
		return filmList;

	}

	public String findFilmCategory(int id) throws SQLException {
		String category = "";

		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT f.*, c.* FROM film f JOIN film_category fc ON f.id=fc.film_id "
				+ "JOIN category c ON fc.category_id=c.id WHERE f.id=?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			// This do while loop is probably useless because there will only be one result
			do {
				// doesn't show what language the film is in
//		System.out.println(rs.getString("title") + " " + rs.getString("release_year") + " "
//				+ rs.getString("rating") + " " + rs.getString("description"));
//		System.out.println(
//				rs.getString("a.id") + " " + rs.getString("first_name") + " " + rs.getString("last_name"));
				category = rs.getString("c.name");

			} while (rs.next());
		}
		stmt.close();
		conn.close();
		rs.close();
		return category;

	}

	public Actor createActor(Actor actor) {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO actor (first_name, last_name) " + " VALUES (?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newActorId = keys.getInt(1);
					actor.setId(newActorId);
					if (actor.getFilms() != null && actor.getFilms().size() > 0) {
						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
						stmt = conn.prepareStatement(sql);
						for (Film film : actor.getFilms()) {
							stmt.setInt(1, film.getId());
							stmt.setInt(2, newActorId);
							updateCount = stmt.executeUpdate();
						}
					}
				}
			} else {
				actor = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting actor " + actor);
		}
		return actor;
	}

	public boolean saveActor(Actor actor) {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE actor SET first_name=?, last_name=? " + " WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			stmt.setInt(3, actor.getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				// Replace actor's film list
				sql = "DELETE FROM film_actor WHERE actor_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, actor.getId());
				updateCount = stmt.executeUpdate();
				sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
				stmt = conn.prepareStatement(sql);
				for (Film film : actor.getFilms()) {
					stmt.setInt(1, film.getId());
					stmt.setInt(2, actor.getId());
					updateCount = stmt.executeUpdate();
				}
				conn.commit(); // COMMIT TRANSACTION
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	public boolean deleteActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE FROM film_actor WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());
			sql = "DELETE FROM actor WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	@Override
	public Film createFilm(Film film) {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO Film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());

			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);
					
					
					System.out.println(newFilmId + "<- SUCCESS FOR FILM ADDED!");

					}
				
			} else {
				film = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				System.out.println("SQL Exception, rolled back. No DB update.");
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			//throw new RuntimeException("Error inserting actor " + film);
		}
		return film;
	}

	public boolean deleteFilm(int filmId) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE FROM film_actor WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			sql = "DELETE FROM film WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	public boolean updateFilm(Film updatedFilm) {
		Connection conn = null;
		try {
			Film databaseFilm = findFilmById(updatedFilm.getId());
		    conn = DriverManager.getConnection(URL, user, pass);
		    conn.setAutoCommit(false); // START TRANSACTION
		    String sql = "UPDATE film SET title=?, description=?, release_year=?, rental_duration=?, rental_rate=?, length=?, replacement_cost=?, rating=?, special_features=? "
		               + " WHERE id=?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(10, updatedFilm.getId());
		    
		    if(updatedFilm.getTitle() != null && !updatedFilm.getTitle().isEmpty() ) {
		    	stmt.setString(1, updatedFilm.getTitle());
		    }else {
		    	stmt.setString(1, databaseFilm.getTitle());
		    }
		    if(updatedFilm.getDescription() != null && !updatedFilm.getDescription().isEmpty() ) {
		    	stmt.setString(2, updatedFilm.getDescription());
		    }else {
		    	stmt.setString(2, databaseFilm.getDescription());
		    }
		    if(updatedFilm.getReleaseYear() > 0 ) {
		    	stmt.setInt(3, updatedFilm.getReleaseYear());
		    }else {
		    	stmt.setInt(3, databaseFilm.getReleaseYear());
		    }
		    if(updatedFilm.getRentalDuration() > 0 ) {
		    	stmt.setInt(4, updatedFilm.getRentalDuration());
		    }else {
		    	stmt.setInt(4, databaseFilm.getRentalDuration());
		    }
		    if(updatedFilm.getRentalDuration() > 0 ) {
		    	stmt.setDouble(5, updatedFilm.getRentalRate());
		    }else {
		    	stmt.setDouble(5, databaseFilm.getRentalRate());
		    }
		    if(updatedFilm.getLength() > 0 ) {
		    	stmt.setInt(6, updatedFilm.getLength());
		    }else {
		    	stmt.setInt(6, databaseFilm.getLength());
		    }
		    if(updatedFilm.getReplacementCost() > 0 ) {
		    	stmt.setDouble(7, updatedFilm.getReplacementCost());
		    }else {
		    	stmt.setDouble(7, databaseFilm.getReplacementCost());
		    }
		    if(updatedFilm.getRating() != null && !updatedFilm.getRating().isEmpty() ) {
		    	stmt.setString(8, updatedFilm.getRating());
		    }else {
		    	stmt.setString(8, databaseFilm.getRating());
		    }
		    if(updatedFilm.getSpecialFeatures() != null && !updatedFilm.getSpecialFeatures().isEmpty() ) {
		    	stmt.setString(9, updatedFilm.getSpecialFeatures());
		    }else {
		    	stmt.setString(9, databaseFilm.getSpecialFeatures());
		    }
		 
		      conn.commit();           // COMMIT TRANSACTION
		    
		  } catch (SQLException sqle) {
		    sqle.printStackTrace();
		    if (conn != null) {
		      try { conn.rollback(); } // ROLLBACK TRANSACTION ON ERROR
		      catch (SQLException sqle2) {
		        System.err.println("Error trying to rollback");
		      }
		    }
		    return false;
		  }
		  return true;	
		}
	
	
}
