package controller;

import data.TvSerieRepository;
import io.javalin.http.Context;
import model.Episode;

import java.util.ArrayList;
import java.util.Comparator;

public class EpisodeController {
    //oppgave 2.7
    private TvSerieRepository episodeRepo;

    public EpisodeController(TvSerieRepository episodeRepo) {
        this.episodeRepo = episodeRepo;
    }

    //delete episode from TVserie
    public void deleteEpisodeController(Context context){
        String serie = context.pathParam("tvserie-id");
        int sesong = Integer.parseInt(context.pathParam("sesong-nr"));
        int episode = Integer.parseInt(context.pathParam("episode-nr"));

        episodeRepo.deleteEpisode(serie,sesong,episode);
        context.redirect("/tvserie/" + serie + "/sesong/" + sesong);
    }

    //get all episodes in a given season
    public void getEpisodes(Context context){
        //get TVSerie name from browser url
        String serie = context.pathParam("tvserie-id");

        //get season number from browser url too
        //this needs casted into int datatype since everything browser url is by default a string datatype.
        int seasonNumber = Integer.parseInt(context.pathParam("sesong-id"));


        //get the correct TVSerie and correct season number using the method defined in the datarepository
        ArrayList<Episode> episodesInSeason = episodeRepo.getEpisodesInSeason(serie,seasonNumber);

        // sortering = episodeNr, tittel, spilletid, null
        //oppgave 2.9 - sorting
        //resource for sorting: compartor we had in lecture, tested small project to sort in chatGPT and also got help from lecturer that sorting should be done in this method.
        //with queryParam(), we can get sorting type and use in if-statement which does the actual sorting before the episode list is returned
        String sortQuery = context.queryParam("sortering");
       // System.out.println(sortQuery);


        //with if-statement to sort episodes by episode number
        //Comparator interface can be used to sort without needing to implement compare class in Episode class.
        //Here we are using anon class Comparator of type Episode. The compare() method can be overriden to change what episode list is being sorted by
        if(sortQuery.equals("episodenr")){

         //   System.out.println("episodenr sort : "+sortQuery);
            episodesInSeason.sort(new Comparator<Episode>() {
                @Override
                public int compare(Episode ep1, Episode ep2) {
                  //  System.out.println("ep1: "+ep1.getEpisodeNr()+" ep2:"+ep2.getEpisodeNr());
                    //episodes are sorted from episodes with highest episodeNr to lowest episodeNr
                    if(ep1.getEpisodeNr() > ep2.getEpisodeNr()){
                        return -1;
                    }
                    return 1;
                }
            });
        }

        //sort episodes by spilletid
        //this is done similar manner to sort above with episodenr
        if(sortQuery.equals("spilletid")){

            episodesInSeason.sort(new Comparator<Episode>() {
                @Override
                public int compare(Episode ep1, Episode ep2) {
                    if(ep1.getSpilletid() > ep2.getSpilletid()){
                        return 1;
                    }
                    return -1;
                }
            });

        }

        //sort alphabetically
        //it is sorted from a - z
        if(sortQuery.equals("tittel")){

            episodesInSeason.sort(new Comparator<Episode>() {
                @Override
                public int compare(Episode ep1, Episode ep2) {
                    //string have inbuilt compareTo method we can use to compare to another string.
                    return ep1.getTitle().compareTo(ep2.getTitle());
                }
            });

        }

        //send json reponse to user
        context.json(episodesInSeason);

    }

    //get single specific episode from a given season
    public void getSingleEpisode(Context context){
        //data that is pulled from pathPram() is similar to the method above this.
        //TVSerie name from browser url
        String serie = context.pathParam("tvserie-id");

        //season number from url too
        int seasonNumber = Integer.parseInt(context.pathParam("sesong-nr"));

        //episode number from url
        int episodeNumber = Integer.parseInt(context.pathParam("episode-nr"));

        //use the method in TVSerieDataRepository class to get a single episode
        Episode singleEpisode = episodeRepo.getEpisodeInSeason(serie,seasonNumber,episodeNumber);


        //send JSON response to user which is matches the browser url
        context.json(singleEpisode);

    }

}//end of class
