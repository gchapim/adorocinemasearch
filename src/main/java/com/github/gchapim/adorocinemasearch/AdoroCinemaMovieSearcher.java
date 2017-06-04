package com.github.gchapim.adorocinemasearch;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.gchapim.adorocinemasearch.util.Util;
import com.github.gchapim.adorocinemasearch.util.UtilDate;


public class AdoroCinemaMovieSearcher {

	private static final String BASE_URL_CINEMA = "http://www.adorocinema.com";
	private static final String URL_CINEMA = "http://www.adorocinema.com/filmes/agenda/";
	private static final String URL_CINEMA_MONTHLY = "http://www.adorocinema.com/filmes/agenda/mes/";
	private static final String ELEMENT_MOVIE = "div.data_box";
	private static final String ELEMENT_URL_MOVIE = "href";
	private static final String ELEMENT_DATABOX = "div.meta-body"; 
	private static final String ELEMENT_DIRECTOR = "span[itemprop=director]";
	private static final String ELEMENT_NAME = "span[itemprop=name]";
	private static final String ELEMENT_GENRE = "span[itemprop=genre]";
	private static final String ELEMENT_DESCRIPTION = "div[itemprop=description]";
	private static final String ELEMENT_MOVIE_TABLE ="div.ovw-synopsis-info";
	private static final String ELEMENT_SCHEDULE = "div.schedule";
	private static final String ELEMENT_SCHEDULE_ITEM = "li";
	private static final String SEARCH_URL = "http://www.adorocinema.com/busca/1/?q=";
	private static final String ELEMENT_COLCONTENT = "div.colcontent";
	
	/**
	 * This method returns all the movies from the Upcoming Movies section of the website starting from today
	 * @param page Page starting from 1
	 * @return a list of all the movies from the Upcoming Movies section of the website starting from today for a page
	 * @throws IOException 
	 */
	public static List<AdoroCinemaMovie> getUpcomingMoviesFromToday(int page) throws IOException{
			Document doc = Jsoup.connect(URL_CINEMA).get();
			Elements movies = doc.select(ELEMENT_MOVIE);
			
			List<AdoroCinemaMovie> acMovies = new ArrayList<AdoroCinemaMovie>();
			for(Element movie : movies){			
					Element a = movie.select("a").first();
					String movieUrl = a.attr(ELEMENT_URL_MOVIE);
					
					if(Util.filled(movieUrl)){
						String[] cuttedUrl = movieUrl.split("/filme-");
						if(cuttedUrl.length > 0){
							
							AdoroCinemaMovie acMovie = getMovieInfo(BASE_URL_CINEMA + movieUrl, false);
							
							if(Util.filled(acMovie)){
								String id = StringUtils.remove(cuttedUrl[cuttedUrl.length-1], "/");
								String title = a.html().replace("\n", "")
										.replace("<strong>", "").replace("</strong>", "");
								if(!title.contains("VIDEOGAME")){
									acMovie.setId(id);
									acMovie.setTitle(title);
							
								
									acMovies.add(acMovie);
								}
							}else{
								//do something
							}
						}else{
						//do something	
						}
					}else{
						//do something
					}				
				
			}
			
			return acMovies;
	}
	
	/**
	 * This method is used for populating the AdoroCinemaMovie object
	 * @param movieUrl Url found for AdoroCinema movie
	 * @param title 
	 * @return instance of AdoroCinemaMovie found
	 * @throws IOException 
	 * @throws ResponseException
	 */
	private static AdoroCinemaMovie getMovieInfo(String movieUrl, boolean title) throws IOException{
		Document doc = Jsoup.connect(movieUrl).get();
			
			AdoroCinemaMovie acMovie = new AdoroCinemaMovie();
			Element movieBox = null;
			if(title){
					
				Element divTitle = doc.getElementsByClass("col-xs-12").first();
				Element metaName = divTitle.select("meta[itemprop=name]").first();
					
				if(Util.filled(metaName)){
					String name = metaName.attr("content");
					
					if(Util.filled(name)){
						String[] nameSplitted = name.split("/");
						if(Util.filled(nameSplitted)){
							name = nameSplitted[nameSplitted.length-1];
						}
						acMovie.setTitle(name);
					}
				}
			}
			movieBox = doc.select(ELEMENT_DATABOX).first();

			
			
			
			if(Util.filled(movieBox)){
				
				//Data de lancamento
				String date;
				Element dateBox = movieBox.select("div.meta-body-item").first().getElementsByTag("span").last();
					
				date = dateBox.html()
							.replace("\n", "").split("\\(+")[0];
				acMovie.setRelease(UtilDate.fullDatetoGregorianCalendar(date));
				
				//Imagem Poster
				String imgUrl = doc.select("img[itemprop=image]").first().attr("src").replaceAll("r_[0-9]{3}_[0-9]{3}", "cx_400_600");
				acMovie.setImgUrl(imgUrl);
				
				
						
				//Director
				Elements directorsBox = movieBox.select(ELEMENT_DIRECTOR);
				if(Util.filled(directorsBox)){
					Elements directorBox = directorsBox.first().select(ELEMENT_NAME);
	
					if(Util.filled(directorBox)){
						acMovie.setDirector(directorBox.first().html());
					}
				}
				//Genre
				Element genreBox = movieBox.select(ELEMENT_GENRE).first();
				
				if(Util.filled(genreBox)){
					acMovie.setGenre(genreBox.html());
				}
				

				//Description
				Element descElement = doc.select(ELEMENT_DESCRIPTION).first();

				if(Util.filled(descElement)){
					acMovie.setDesc(descElement.text());
				}
				
				//Movie Table
				Element movieTable = doc.select(ELEMENT_MOVIE_TABLE).first();
				
				if(Util.filled(movieTable)){
					//for(Element tableElement : movieTable.getChildElements()){
						Element elementTable = movieTable.select("div.item").first();

							
						for(Element element : elementTable.children()){
							if(element.html().contains("original")){
							
								Element field = element.nextElementSibling();
								acMovie.setOriginalTitle(field.text().replace("\n", ""));
								break;
							}
						}
						
						
					//}
				}
				
				return acMovie;
			}
			
		
		return null;
	}

	/**
	 * This method returns all the movies from the Upcoming Movies section of the website starting from month to month
	 * @param page
	 * @return a list of all the movies from the Upcoming Movies section of the website
	 * @throws IOException 
	 */
	public static List<AdoroCinemaMovie> getUpcomingMoviesMonthly(int page) throws IOException{
		
		
		GregorianCalendar date = UtilDate.addMonths(UtilDate.now(), page-1);
		
		Integer mes = date.get(GregorianCalendar.MONTH) + 1;
		String month = "?month=" + date.get(GregorianCalendar.YEAR) 
					+ "-" + String.format("%02d", mes);

		Document doc = Jsoup.connect(URL_CINEMA_MONTHLY + month).get();
			
			
		Element weeks = doc.select(ELEMENT_SCHEDULE).first();
			
		List<AdoroCinemaMovie> acMovies = new ArrayList<AdoroCinemaMovie>();
		Elements movies = weeks.getElementsByTag(ELEMENT_SCHEDULE_ITEM);
				
				
		for(Element movie : movies){			

			Element a = movie.getElementsByTag("a").first();
			String movieUrl = a.attr(ELEMENT_URL_MOVIE);
						
			if(Util.filled(movieUrl)){
				String[] cuttedUrl = movieUrl.split("/filme-");
				if(cuttedUrl.length > 0){
							
					AdoroCinemaMovie acMovie = getMovieInfo(BASE_URL_CINEMA + movieUrl, false);
								
					if(Util.filled(acMovie)){
						String id = StringUtils.remove(cuttedUrl[cuttedUrl.length-1], "/");
						String title = a.html().replace("\n", "")
								.replace("<strong>", "").replace("</strong>", "");
						if(!title.contains("VIDEOGAME")){
							acMovie.setId(id);
							acMovie.setTitle(title);
						
									
							acMovies.add(acMovie);
						}
					}else{
									//do something
					}
				}else{
					//	do something	
				}
			}else{
				//do something
			}			
		}
		return acMovies;
	}
	
	/**
	 * Main method for searching the movie by it's title
	 * @param movieName name of the movie
	 * @return list of all the movies matching that name
	 * @throws IOException 
	 */
	public static List<AdoroCinemaMovie> search(String movieName) throws IOException {
		if(Util.filled(movieName)){
			
			String busca = SEARCH_URL;
			busca = busca + URLEncoder.encode(movieName, "UTF-8");
			Document doc = Jsoup.connect(busca).get();
			
			
			Element content = doc.select(ELEMENT_COLCONTENT).first();
				
			//Element table = content.getElementsByTag("table").first();
				
			Elements trs = content.getElementsByTag("a");
				
			LinkedHashMap<String, String> hrefList = new LinkedHashMap<String, String>();
				
			for(Element tr : trs){
				if(tr.hasAttr(ELEMENT_URL_MOVIE)){
					String href = tr.attr(ELEMENT_URL_MOVIE);
					
					if(!hrefList.containsKey(href)){
						hrefList.put(href, tr.text());
					}
				}
			}
				
			List<AdoroCinemaMovie> acMovies = new ArrayList<AdoroCinemaMovie>();
			for(Entry<String, String> movieUrl : hrefList.entrySet()){
				if(Util.filled(movieUrl.getKey())){
					String[] cuttedUrl = movieUrl.getKey().split("/filme-");
					if(cuttedUrl.length > 0){
						
						AdoroCinemaMovie acMovie = getMovieInfo(BASE_URL_CINEMA + movieUrl.getKey(), true);
						
						if(Util.filled(acMovie)){
							String id = StringUtils.remove(cuttedUrl[cuttedUrl.length-1], "/");
							
							if(Util.filled(acMovie.getTitle()) && !acMovie.getTitle().contains("VIDEOGAME")){
								acMovie.setId(id);
						
							
								acMovies.add(acMovie);
							}
						}else{
							//do something
						}
					}else{
					//do something	
					}
				}else{
					//do something
				}
			}
			return acMovies;
		}else{
			return null;
		}
	}
}