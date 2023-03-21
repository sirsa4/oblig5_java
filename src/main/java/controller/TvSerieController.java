package controller;

import data.TvSerieRepository;
import io.javalin.http.Context;
import model.TVSerie;

import java.util.ArrayList;

public class TvSerieController {
    private TvSerieRepository repo;

    //oppgave 2.6
    public TvSerieController(TvSerieRepository repo) {
        this.repo = repo;
    }



    //get all TVSerie: TVSerie ArrayList
    public void getTVSeries(Context context){

        //get an ArrayList of TVSerie using

        ArrayList<TVSerie> allSeries = repo.getAllTVSerie();

        context.json(allSeries);

    }

    //get single TVserie
    public void getTVSerie(Context context){
        //TVserie name from URL parameter in web browser
        String serie = context.pathParam("tvserie-id");

        //get TVSerie which user requests
        TVSerie correctTVSerie = repo.getSingleTVSerie(serie);

        //send response as json data
        context.json(correctTVSerie);
    }


}
