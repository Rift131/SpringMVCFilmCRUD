package com.skilldistillery.filmquery.data;

import java.util.List;
import java.util.Objects;

public class Actor {
	private int Id;
	private String firstName;
	private String lastName;
	private List<Film> films;

	public Actor(int actorId, String firstName, String lastName) {
		super();
		this.Id = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Actor() {
		super();
	}
	
	

	public Actor(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return Id;
	}

	public void setId(int actorId) {
		this.Id = actorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return Id == other.Id && Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
	

}
