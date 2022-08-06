package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.filmquery.data.Actor;
import com.skilldistillery.filmquery.data.DatabaseAccessorObject;
import com.skilldistillery.filmquery.data.Film;

@Controller
public class FilmController {
	@Autowired
	private DatabaseAccessorObject dao;
	@RequestMapping("SearchById.do")
	 public ModelAndView searchById(String data) {
		ModelAndView mv = new ModelAndView();
			try {
				int filmId= Integer.parseInt(data);
				Film filmById=dao.findFilmById(filmId);
				mv.setViewName("FilmById");
				mv.addObject("result", filmById);
				return mv;
				
			} catch (NumberFormatException e) {
				mv.setViewName("Error");
				//Hey that wasn't a number!
			} catch(NullPointerException e) {
				mv.setViewName("Error");
				//hey that was null!
			} catch (SQLException e) {
				mv.setViewName("Error");
			}
			return mv;
		
	}
	@RequestMapping("searchByKeyword.do")
	public ModelAndView searchByKeyword(@RequestParam("data") String s) {
		String allCaps = s.toUpperCase();
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/WEB-INF/SearchByKeyword.jsp");
		mv.addObject("result", allCaps);
		return mv;
	}
	
	
	@RequestMapping("createFilm.do")
	public ModelAndView createFilm ( String title, String description, String releaseYear, String rentalDuration, String rentalRate, String length, String replacementCost, String rating, String specialfeatures) {

		ModelAndView mv = new ModelAndView();
		
		try {
			int rYear= Integer.parseInt(releaseYear);
			int rDuration= Integer.parseInt(rentalDuration);
			double rRate= Double.parseDouble(rentalRate);
			int rLength= Integer.parseInt(length);
			double rCost= Double.parseDouble(replacementCost);
			
			Film film = new Film(title, description, rYear, 1, rDuration, rRate, rLength, rCost, rating, specialfeatures);
			
			Film newFilm=dao.createFilm(film);
			
			mv.setViewName("CreateFilm");
			mv.addObject("result", newFilm);
			return mv;
			
		} catch (NumberFormatException e) {
			mv.setViewName("Error");
			//Hey that wasn't a number!
		} catch(NullPointerException e) {
			mv.setViewName("Error");
		}
			//hey that was null!
		return mv;
	}
	@RequestMapping("deleteFilm.do")
	public ModelAndView deleteFilm(@RequestParam("data") String s) {
		String allCaps = s.toUpperCase();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/deleteFilm.jsp");
		mv.addObject("result", allCaps);
		return mv;
	}
	@RequestMapping("updateFilm.do")
	public ModelAndView updateFilm(@RequestParam("data") String s) {
		String allCaps = s.toUpperCase();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/updateFilm.jsp");
		mv.addObject("result", allCaps);
		return mv;
	}

}
