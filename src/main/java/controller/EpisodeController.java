package controller;

import data.TvSerieRepository;
import io.javalin.http.Context;
import model.Episode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class EpisodeController {
    //oppgave 2.7
    private TvSerieRepository episodeRepo;

    public EpisodeController(TvSerieRepository episodeRepo) {
        this.episodeRepo = episodeRepo;
    }

    //oppgave 2.3: delete episode from TVserie
    public void deleteEpisodeController(Context context){
        String serie = context.pathParam("tvserie-id");
        int sesong = Integer.parseInt(context.pathParam("sesong-nr"));
        int episode = Integer.parseInt(context.pathParam("episode-nr"));

        episodeRepo.deleteEpisode(serie,sesong,episode);

        context.redirect("/tvserie/" + serie + "/sesong/" + sesong);
    }

    //oppgave 2.4: create a new episode
    public void createEpisodeController(Context context){
        //params to get data submitted from form in create episode vue component
        //names for each value is gotten from episode-create.vue data() method in script tag
        //anything that is number from url need to parsed like usually since everything in url is strings
        //order value are is same as they are shown in form from front-end
        String title = context.formParam("tittel");
        int sesongNummer = Integer.parseInt(context.formParam("sesongNummer"));
        int episodeNummer = Integer.parseInt(context.formParam("episodeNummer"));
        String beskrivelse = context.formParam("beskrivelse");
        double spilletid = Double.parseDouble(Objects.requireNonNull(context.formParam("spilletid")));
        LocalDate  utgivelsesdato = LocalDate.parse(context.formParam("utgivelsesdato"));
        String  bildeUrl = context.formParam("bildeUrl");

        //we also need find correct TVSerie object to add episode to
        String tvserie = context.pathParam("tvserie-id");
        /*
        System.out.println("title: "+title);
        System.out.println("sesongNr: "+sesongNummer);
        System.out.println("episodeNummer: "+episodeNummer);
        System.out.println("beskrivelse: "+beskrivelse);
        System.out.println("spilletid: "+spilletid);
        System.out.println("ugivelsedato: "+utgivelsesdato);
        System.out.println("bilde url: "+bildeUrl);

         */
        //call method to create an episode in json repository
        episodeRepo.createEpisode(tvserie,title,sesongNummer,episodeNummer,beskrivelse,spilletid,utgivelsesdato,bildeUrl);

        //finally redirect to correct tv serie page in front-end so that user can add episode
        context.redirect("/tvserie/" + tvserie + "/sesong/" + sesongNummer);


    }

    //oppgave 2.5 - update data of an already existing episode
    public void updateEpisodeController(Context context){
        String serie = context.pathParam("tvserie-id");
        int sesong = Integer.parseInt(context.pathParam("sesong-nr"));
        int episodeNr = Integer.parseInt(context.pathParam("episode-nr"));

        String title = context.formParam("tittel");
        int sesongNummer = Integer.parseInt(context.formParam("sesongNummer"));
        int episodeNummer = Integer.parseInt(context.formParam("episodeNummer"));
        String beskrivelse = context.formParam("beskrivelse");
        double spilletid = Double.parseDouble(Objects.requireNonNull(context.formParam("spilletid")));
        LocalDate utgivelsesdato = LocalDate.parse(context.formParam("utgivelsesdato"));
        String bildeUrl = context.formParam("bildeUrl");

        //method in json repository would need alot parameters. first ones to get correct episode: first tvserie, then episode
        //rest of parameters data from inputs in submit form from vue front end for updating episode
        episodeRepo.updateEpisode(serie,sesong,episodeNr,title,sesongNummer,episodeNummer,beskrivelse,spilletid,utgivelsesdato,bildeUrl);

        //redirect to updated episode page
        context.redirect("/tvserie/" + serie + "/sesong/" + sesongNummer+"/episode/"+episodeNr);

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
