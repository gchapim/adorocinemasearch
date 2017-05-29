Adoro Cinema Searcher
==============

An **unofficial** application for searching movies through Adoro Cinema database.

## How to use

### Searching for a movie by name

```java
// Get a list of movies based on a movie's name:
List<AdoroCinemaMovie> searchResults = AdoroCinemaMovieSearcher.getMovieInfo("The Secret Life of Walter Mitty");
```

### Get upcoming movies from today on

```java
// Get a list of movies based on movies releasing from today (page 1):
int page = 1;
List<AdoroCinemaMovie> upcomingMoviesFromToday = AdoroCinemaMovieSearcher.getUpcomingMoviesFromToday(page);
```

### Get upcoming movies by month

```java
// Get a list of movies based on movies releasing by month (page 1):
int page = 1;
List<AdoroCinemaMovie> upcomingMoviesMonthly = AdoroCinemaMovieSearcher.getUpcomingMoviesMonthly(page);
```

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
