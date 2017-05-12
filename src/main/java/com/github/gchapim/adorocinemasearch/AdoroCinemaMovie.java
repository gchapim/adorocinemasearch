package com.github.gchapim.adorocinemasearch;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Model class for a movie got from AdoroCinema website
 * @author gchapim
 *
 */
public class AdoroCinemaMovie {
	private String id;
	private String originalTitle;
	private String title;
	private String desc;
	private GregorianCalendar release;
	private String genre;
	private List<String> actors;
	private String director;
	private String country;
	private String imgUrl;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOriginalTitle() {
		return originalTitle;
	}
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public GregorianCalendar getRelease() {
		return release;
	}
	public void setRelease(GregorianCalendar release) {
		this.release = release;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public List<String> getActors() {
		return actors;
	}
	public void setActors(List<String> actors) {
		this.actors = actors;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	
}
