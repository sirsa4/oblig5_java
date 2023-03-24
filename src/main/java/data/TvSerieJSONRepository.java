package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Episode;
import model.TVSerie;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TvSerieJSONRepository implements TvSerieRepository {
    private ArrayList<TVSerie> tvSeries;
    public TvSerieJSONRepository(String filePath) {

        //oppgave 2.1 a

        //object mapper
        ObjectMapper mapper = new ObjectMapper();

        //filePath
        File path = new File(filePath);

        //module to be able to read LocalDate object correctly. This is needed in order to be able get LocalDate
        mapper.registerModule(new JavaTimeModule());

        //read tvseries from json file
        try {
            TVSerie[] seriesFromJson = mapper.readValue(path,TVSerie[].class);

        // add tvseries data from json to the empty array.
        // when object of this class is instantiated, it would need take json file path to create tvseries
        tvSeries = new ArrayList<>(Arrays.asList(seriesFromJson));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //this is for quick testing delete, update, create
       writeToJson(tvSeries,"myjson.json");

    }
    //oppgave 2.3 - a create, update, delete methods
    @Override
    public void createEpisode(String tvserie) {

    }

    @Override
    public void updateEpisode(String tvserie, int sesongNr, int episodeNr) {

    }

    @Override
    public void deleteEpisode(String tvserie, int sesongNr, int episodeNr) {

        //We already have from before a method which gets correct episode from tvserie in the correct season number
        //to delete this episode, we need to get correct TVserie object and then correct season in episodes
       Episode episodeToDelete = getEpisodeInSeason(tvserie,sesongNr,episodeNr);

        System.out.println(episodeToDelete.getTitle() + ": sesong: "+ episodeToDelete.getSesongNr()+ " episodeNr: "+episodeToDelete.getEpisodeNr());

        //we already have a method which gets the correct TVserie
        TVSerie correctSeason = getSingleTVSerie(tvserie);

        correctSeason.deleteEpisodeInSeason(sesongNr,episodeNr);

        //finally we can get episodes from correct so that we can delete exact needed episode
       // ArrayList<Episode> episodesInSeason = correctSeason.hentEpisoderISesong(sesongNr);



        //write to json again after episode is deleted so that on application restart the deleted episode is missing.
        writeToJson(tvSeries,"myjson.json");

    }

    //oppgave 2.1 read json again
    public ArrayList<TVSerie> readjson(String filePath){
        ObjectMapper mapper = new ObjectMapper();

        //filePath
        File path = new File(filePath);

        //module to be able to read LocalDate object correctly. This is needed in order to be able get LocalDate
        mapper.registerModule(new JavaTimeModule());

        //read tvseries from json filej
        try {
            TVSerie[] seriesFromJson = mapper.readValue(path,TVSerie[].class);

            //  ArrayList<TVSerie> serieArrayList = new ArrayList<>(Arrays.asList(seriesFromJson));
            return new ArrayList<>(Arrays.asList(seriesFromJson));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //oppgave 2.1-D - write to json
    public void writeToJson(ArrayList<TVSerie> series,String filePath){
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);

        try{
            mapper.registerModule(new JavaTimeModule());
            mapper.writerWithDefaultPrettyPrinter().writeValue(file,series);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public TVSerie getSingleTVSerie(String tvSerie) {
        for(TVSerie serie : tvSeries){
            if(serie.getTitle().equals(tvSerie)){
                return serie;
            }
        }
        return null;
    }

    @Override
    public ArrayList<TVSerie> getAllTVSerie() {
        return new ArrayList<>(tvSeries);
    }

    @Override
    public ArrayList<Episode> getEpisodesInSeason(String tvserie, int season) {
        //get correct tvserie using the method which gets serie by title()
        TVSerie correctSerie = getSingleTVSerie(tvserie);

        //get correct season in tvserie
        ArrayList<Episode> episodesInSeason = correctSerie.hentEpisoderISesong(season);

        return episodesInSeason;
    }

    @Override
    public Episode getEpisodeInSeason(String tvserie, int season, int episodeNr) {
        //first get correct season
        TVSerie correctSeason = getSingleTVSerie(tvserie);

        //get all epsiodes from correct season that can be looped through to get single correct epsiode
        ArrayList<Episode> episodesInSeason = correctSeason.hentEpisoderISesong(season);

        //loop through epsiodes in correct season and return only 1 single correct episode needed
        for(Episode episode : episodesInSeason){
            if(episode.getEpisodeNr() == episodeNr){
                return episode;
            }
        }

        return null;
    }

    //get and setter methods for episodes


    public ArrayList<TVSerie> getTvSeries() {
        return tvSeries;
    }

    public void setTvSeries(ArrayList<TVSerie> tvSeries) {
        this.tvSeries = tvSeries;
    }
}// end of class
