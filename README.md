Adoro Cinema Searcher
==============

An **unofficial** application for searching movies through Adoro Cinema database.

## How to use

### Searching for a movie by name

<pre><code>#Get a list of movies based on a movie's name:
List&#060;AdoroCinemaMovie&#062; searchResults = AdoroCinemaMovieSearcher.getMovieInfo("The Secret Life of Walter Mitty");
</code></pre>

### Get upcoming movies from today on

<pre><code>#Get a list of movies based on movies releasing from today (page 1):
int page = 1;
List&#060;AdoroCinemaMovie&#062; upcomingMoviesFromToday = AdoroCinemaMovieSearcher.getUpcomingMoviesFromToday(page);
</code></pre>

### Get upcoming movies by month

<pre><code>#Get a list of movies based on movies releasing by month (page 1):
int page = 1;
List&#060;AdoroCinemaMovie&#062; upcomingMoviesMonthly = AdoroCinemaMovieSearcher.getUpcomingMoviesMonthly(page);
</code></pre>

### AdoroCinemaMovie attributes:

- String **id**: the id for the movie on AdoroCinema database
- String **original title**: the original title for the movie in native language
- String **title**: the portuguese title for the movie
- String **desc**: sinopsis
- GregorianCalendar **release**: release date
- String **genre**
- String **director**
- String **imgUrl**: image url for movie poster from Adoro Cinema. ***Be careful:** you may not have authorization to use their images*

More attributes coming soon.
