package data;

import model.Episode;
import model.Person;
import model.TVSerie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;


public class TvSerieDataRepository implements TvSerieRepository {
    //oppgave 2.5
    //instance variable which stores TVseries.
    private ArrayList<TVSerie> series = new ArrayList<>();

    public TvSerieDataRepository(){

        //TVSerie objects
        TVSerie futurama = new TVSerie("futurama","Fry is frozen in time and wakes up 100 years into future in 2100", LocalDate.of(1999,03,28),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUeQORj8jYJR7apcBXOBJyflA_Zp3WvW_bEA&usqp=CAU");
        TVSerie theSimpsons = new TVSerie("the simpsons"," a serie about homer simpsons and his family", LocalDate.of(1989,12,17),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSnwhKOtAmFSFmB5-Z2woj96xJmrSUI-xd6WA&usqp=CAU");

        //add few episodes on each TVSerie object
        //episodes in futurama
        futurama.leggTilEpisode(new Episode("second episode", 2, 1, 22,"It is actually not year 2100 yet",LocalDate.of(1999,1,12),"https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/b179b54a-c6c2-4d81-8417-1bb908ea5ec1/d9nhva1-3ae58f36-062a-43ad-ae65-d0837fc37526.png"));
        futurama.leggTilEpisode(new Episode("first episode", 1, 1, 25,"I dont know why second episode has such a bad title name",LocalDate.of(2002,5,28),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgCwgmvs-57ka7sNEXP-ZMD8P2eLQF_3C3iuha5SZ_Yc9U112erSSfpDm2CNlnDVooQE0&usqp=CAU"));
        futurama.leggTilEpisode(new Episode("episode which is third", 3, 2, 21,"It took 3 years for third episode to release. Imagine that.",LocalDate.of(2003,12,21),"https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/4031709e-3e82-4022-9d23-7a295ce73501/d62d246-9848260e-7476-4984-9a36-c9d19d4d4dbd.png"));
        futurama.leggTilEpisode(new Episode("fourth is interesting", 4, 2, 23,"What a wonderful day",LocalDate.of(2004,8,5),"https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/5b4cc51c-50b3-435d-a6f1-6d424d707746/ddgikyk-c1966470-94a7-4bc2-83c2-46528646e10e.png"));
        futurama.leggTilEpisode(new Episode("fifth episode",5,2,22,"too much gathering is not easy at all",LocalDate.of(2007,4,26),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQiRTEwCv-Oe28uP7N7hlv_2MwrGyjT30dP2SpVUTG3X_et2pftl0hNWComflD5Mesq3c&usqp=CAU"));
        futurama.leggTilEpisode(new Episode("sixth episode",6,3,25,"life is very expensive game",LocalDate.of(2008,9,2),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQP8eYCyGaJhgYB2-rdUYg9q5Wi3vvajTDz5KH4qDvWj49LBsMLpBwKjqnTjvugAKu35Co&usqp=CAU"));
        futurama.leggTilEpisode(new Episode("seventh heaven",7,3,27,"so much a do about nothing",LocalDate.of(2007,4,26),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8dUNiWHLsK5yOATCqw8f-KKB_RNE2v7xzpkjLg531Dh-ihMQWAUBS6UQuir_lcJ0l7S4&usqp=CAU"));
        futurama.leggTilEpisode(new Episode("eithght episode",8,3,44,"great things happen to not so great people sometimes",LocalDate.of(2007,4,26),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4dbIH2Y4SSAXtEKg7kyBQXVU_7fFOn4CAsA&usqp=CAU"));

        //episodes in the simpons
        theSimpsons.leggTilEpisode(new Episode("this was it", 2, 1, 24,"This was bit long time ago",LocalDate.of(1989,12,15),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQc48NBTq0dnDj9rXIViPtrTdE_PYsEsKvX0Wnef3mtDbKRnnjtjaBRX82w2mi6JyLXeiY&usqp=CAU"));
        theSimpsons.leggTilEpisode(new Episode("how does this work", 1, 1, 23,"Improvement with each passing episode",LocalDate.of(1991,10,1),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJpxy_s8spSx79CWV4zJ8FfqwOu8YilY0Z5g&usqp=CAU"));
        theSimpsons.leggTilEpisode(new Episode("kkk is not what you think", 3, 2, 44,"There is an OL in Lille Hammer",LocalDate.of(1994,6,9),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcROywiO7iIwPaX8YAsA7flBQZ152kFC2f6DOg&usqp=CAU"));
        theSimpsons.leggTilEpisode(new Episode("the last dance", 4, 2, 23,"PlayStation is rather impressive",LocalDate.of(1996,7,25),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRq56vLiNtiMYNjwN8qx1L5khqGkaciyuEkyQ&usqp=CAU"));
        theSimpsons.leggTilEpisode(new Episode("always that person", 5, 2, 25,"was this on ps2 or psp?",LocalDate.of(2013,4,27),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRnalDchXLY0NQoRRa7YtjfBtNTQtk_Homuw&usqp=CAU"));
        theSimpsons.leggTilEpisode(new Episode("rather", 6, 3, 32,"it was difficult day for homer and marge",LocalDate.of(2013,4,27),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS49wiviypYs78fRLFrnZ8_qocm3GHzMq4L9w&usqp=CAU"));
        theSimpsons.leggTilEpisode(new Episode("welp", 7, 3, 21,"biggest crossover since bread and butter",LocalDate.of(2013,4,27),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmM-eExvwkwGP_fwuUtpO9jv0LS_LIMD6C0w&usqp=CAU"));
        theSimpsons.leggTilEpisode(new Episode("landroper", 8, 3, 44,"somethings always stay the same",LocalDate.of(2013,4,27),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Ml9kMzD0ctKMGfxpA2NCp9OzYfXS_KdyRw&usqp=CAU"));
        theSimpsons.leggTilEpisode(new Episode("hihi", 9, 3, 23,"sunday morning at springfield",LocalDate.of(2013,4,27),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2Ge8O8Tk71GMAHr3vgC8zfLudE6-QHlbe3w&usqp=CAU"));

        //Rolle for for each TVserie episode list. All futurama episode Fry regissor. All simpons episodes have homer regissor.
        Person fry = new Person("Phlipp","Fry");
        Person homer = new Person("homer","simpson");

        //ArrayList for each TVserie episode to help add regissor easier
        ArrayList<Episode> futuramaEpisodes = futurama.getEpisoder();
        ArrayList<Episode> simpsonsEpisodes = theSimpsons.getEpisoder();

        //add Person to each episode with loop
        for(Episode ep : futuramaEpisodes){
            ep.setRegissor(fry);
        }
        for(Episode ep : simpsonsEpisodes){
            ep.setRegissor(homer);
        }


        //add both TVserie objects to the empty ArrayList
        series.add(futurama);
        series.add(theSimpsons);

    }
    //oppgave 2.5
    //method which gets single TVserie objects
    @Override
    public TVSerie getSingleTVSerie(String tvSerie) {
        //to get desired TVSerie, we can loop through the ArrayList<TVSerie> series
        for(TVSerie serie : series){
            //to get correct Tvserie, we can use if-statement to compare title of TVserie object with the string parameter in this method.
            if(serie.getTitle().equals(tvSerie)){
                //TVserie title which matches user input when the method is used is returned in end.
                return serie;
            }
        }
        //if no matching TVserie title is not found, null is returned.
        return null;
    }
    //oppgave 2.5
    //This method returns a copy ArrayList<TVSerie> series.
    //we have learned to return copy instead of the original ArrayList from previous obligs.
    @Override
    public ArrayList<TVSerie> getAllTVSerie() {
        return new ArrayList<>(series);
    }

    //oppgave 2.7 - get all episodes in a given season
    @Override
    public ArrayList<Episode> getEpisodesInSeason(String tvserie,int season) {
        //first get correct TVSerie using the getSingleTVSerie() method from oppgave 2.5
        TVSerie correctSerie = getSingleTVSerie(tvserie);

        //get episodes in season from the correctSerie
        //hentEpisoderISesong() is a finished method from previous oblig which returns an ArrayList<Episodes> in a given season.
        ArrayList<Episode> episodesInSeason = correctSerie.hentEpisoderISesong(season);

        //finally return the correct TVserie and all it's episodes in correct season.
        return episodesInSeason;
    }

    //implementation of method to get specific episode from specific season nr
    //This is for Vue file epsiode-detail.vue
    @Override
    public Episode getEpisodeInSeason(String tvserie, int season, int episodeNr) {

        //first correct TVSerie
        TVSerie correctSerie = getSingleTVSerie(tvserie);


        //get correct episodes from specific season number of the series
        ArrayList<Episode> correctSeasonEpisodes = correctSerie.hentEpisoderISesong(season);

        //loop through the ArrayList<Episode> to get single specific episode which matches episodeNr from the method parameter
        for(Episode episode : correctSeasonEpisodes){

            //if-statement to get exactly episode which user requests
            if(episode.getEpisodeNr() == episodeNr){
                return episode;
            }

        }

        return null;
    }


}
