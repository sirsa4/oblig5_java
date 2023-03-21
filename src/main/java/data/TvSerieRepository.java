package data;

import model.Episode;
import model.TVSerie;

import java.util.ArrayList;

//oppgave 2.5
public interface TvSerieRepository {
    //method to get a single TVSerie object
    TVSerie getSingleTVSerie(String tvSerie);

    //method to get all TVSerie objects
    ArrayList<TVSerie> getAllTVSerie();

    //oppgave 2.7
    //method to get specific season number and all it's episodes
    ArrayList<Episode> getEpisodesInSeason(String tvserie,int season);

    //method to get single specific episode from specific season
    public Episode getEpisodeInSeason(String tvserie, int season, int episodeNr);



}//end of interface
