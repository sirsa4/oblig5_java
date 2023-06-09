package data;

import model.Episode;
import model.TVSerie;

import java.time.LocalDate;
import java.util.ArrayList;

//oppgave 2.5
public interface TvSerieRepository {
    //method to get a single TVSerie object
    TVSerie getSingleTVSerie(String tvSerie);

    //method to get all TVSerie objects
    ArrayList<TVSerie> getAllTVSerie();


    //method to get specific season number and all it's episodes
    ArrayList<Episode> getEpisodesInSeason(String tvserie,int season);

    //method to get single specific episode from specific season
    public Episode getEpisodeInSeason(String tvserie, int season, int episodeNr);

    //oppgave 2.3 - a - create, update, delete episode methods
    public void createEpisode(String tvserie, String title, int sesonNr, int episodeNr, String beskrivelse, double spilletid, LocalDate utgivelsesdato,String bildeurl);
    public void updateEpisode(String tvserie, int sesongNr, int episodeNr, String title, int sesongNummer, int episodeNummer, String beskrivelse, double spilletid, LocalDate utgivelsesdato, String bildeurl);
    public void deleteEpisode(String tvserie, int sesongNr, int episodeNr);

}//end of interface
