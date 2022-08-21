package io.javabrains.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
//import io.javabrains.moviecatalogservice.models.CatalogItem;
//import io.javabrains.moviecatalogservice.models.Movie;
//import io.javabrains.moviecatalogservice.models.Rating;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class MovieInfo {
//    @Autowired
//    private RestTemplate restTemplate;
//
//    ///@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
//    public CatalogItem getCatalogItem(Rating rating) {
//        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
//        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
//    }
//
//    public CatalogItem getFallbackCatalogItem(Rating rating) {
//        return new CatalogItem("Movie Name not found", "", rating.getRating());
//    }
//}
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class MovieInfo {

        @Value("${movie.url}")
        private String url;
    @Autowired
    private RestTemplate restTemplate;

   // @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
       // Movie movie = restTemplate.getForObject(url + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie Name not found", "", rating.getRating());
    }
}