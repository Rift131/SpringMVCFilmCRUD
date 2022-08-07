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
	private static int updatedFilmId;
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
		ModelAndView mv = new ModelAndView();
		try {
			s = "%" + s + "%";
			List<Film> keywordFilm = dao.findFilmByKeyword(s);
			mv.setViewName("SearchByKeyword");
			System.out.println("FILM CONTROLLER" + keywordFilm);
			mv.addObject("results", keywordFilm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
	public ModelAndView deleteFilm(@RequestParam("DeleteThisFilm") String s) {
		int filmId = Integer.parseInt(s);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("NoDelete");
		
		if (dao.deleteFilm(filmId)) {
			mv.setViewName("deleteFilm");
		} 
		return mv;
	}
	@RequestMapping("updateFilm.do")
	public ModelAndView updateFilm(@RequestParam("data") String s) {
		int filmId = Integer.parseInt(s);
		updatedFilmId = filmId;
		ModelAndView mv = new ModelAndView();
		Film filmById;
		try {
			filmById = dao.findFilmById(filmId);
			mv.setViewName("updateFilm");
			mv.addObject("result", filmById);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
		
	}
	@RequestMapping("updateFilmForReal.do")
	public ModelAndView updateFilm ( String title, String description, String releaseYear, String rentalDuration, String rentalRate, String length, String replacementCost, String rating, String specialfeatures) {

		ModelAndView mv = new ModelAndView();
		int rYear = 0;
		int rDuration = 0;
		double rRate = 0;
		double rCost = 0;
		int rLength = 0;
		try {
			if(releaseYear != null && !releaseYear.isEmpty()) {
				rYear= Integer.parseInt(releaseYear);
			}
			if(rentalDuration != null && !rentalDuration.isEmpty()) {
			rDuration= Integer.parseInt(rentalDuration);
			}
			if(rentalRate != null && !rentalRate.isEmpty()) {
			 rRate= Double.parseDouble(rentalRate);
			}
			if(length != null && !length.isEmpty()) {
			rLength= Integer.parseInt(length);
			}
			if(replacementCost != null && !replacementCost.isEmpty()) {
			rCost= Double.parseDouble(replacementCost);
			}
			
			Film film = new Film(updatedFilmId, title, description, rYear, 1, rDuration, rRate, rLength, rCost, rating, specialfeatures);
			
			if(dao.updateFilm(film)){
				Film newFilm = dao.findFilmById(updatedFilmId);
				mv.setViewName("goodFilmUpdate");
				mv.addObject("result", newFilm);
			}
			return mv;
			
		} catch (NumberFormatException e) {
			mv.setViewName("Error");
			//Hey that wasn't a number!
		} catch(NullPointerException e) {
			mv.setViewName("Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//hey that was null!
		return mv;
	}

}
