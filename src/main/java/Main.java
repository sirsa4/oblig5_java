//model classes
import data.TvSerieCSVRepository;
import data.TvSerieDataRepository;
import data.TvSerieJSONRepository;
import data.TvSerieRepository;
import model.*;

import java.time.LocalDate;
import java.util.*;


public class Main {


    public static void main(String[] args) {


        //oppgave2.1
        System.out.println();
        System.out.println("==============oppgave2.1================");
        System.out.println("Produksjon class is not a natural class to create objects from");
        //Produksjon class is not a natural class to create objects from. It is a abstract class which can be inherited by other classes like Episode and Film.

        System.out.println();
        System.out.println("==============oppgave2.2================");
        //TVSerie objekt instance.
        TVSerie futurama = new TVSerie("futurama","Fry is frozen in time and wakes up 100 years into future in 2100", LocalDate.of(1999,03,28));
        TVSerie theSimpsons = new TVSerie("the simpsons"," a serie about homer simpsons and his family", LocalDate.of(1989,12,17));

        futurama.leggTilEpisode(new Episode("Episode 1", 1, 1, 22));
        futurama.leggTilEpisode(new Episode("Episode 2", 2, 1, 25));
        futurama.leggTilEpisode(new Episode("Episode 1", 1, 2, 21));
        futurama.leggTilEpisode(new Episode("Episode 2", 2, 2, 23));

        theSimpsons.leggTilEpisode(new Episode("Episode 1", 1, 1, 24));
        theSimpsons.leggTilEpisode(new Episode("Episode 2", 2, 1, 23));
        theSimpsons.leggTilEpisode(new Episode("Episode 3", 3, 1, 44));
        theSimpsons.leggTilEpisode(new Episode("Episode 4", 4, 1, 23));
        theSimpsons.leggTilEpisode(new Episode("Episode 5", 5, 1, 23));

        //comapre futurama series number of seasons to the simpsons
        System.out.println("TVSerie result shows -1 since theSimpsons has 1 more episode than futurama: "+futurama.compareTo(theSimpsons));

        //compare Episode objects
        //first get episodes from a TVSerie
        ArrayList<Episode> toBeSortedEpisodes = theSimpsons.getEpisoder();

        System.out.println("======Before episode sort");
        System.out.println(toBeSortedEpisodes);

        System.out.println("========After episode sort========");
        //resource for inner class interface is something we got from lecture in dyrepark.
        toBeSortedEpisodes.sort(new Comparator<Episode>() {
            @Override
            public int compare(Episode ep1, Episode ep2) {
                return ep1.compareTo(ep2);
            }
        });

        //Collections.sort(toBeSortedEpisodes);
        System.out.println(toBeSortedEpisodes);


        System.out.println("====================Oblig 5 Test==================");

      //  TvSerieJSONRepository series = new TvSerieJSONRepository("tvshows_10_with_roles.json");
     //   TvSerieCSVRepository csvserie = new TvSerieCSVRepository("tvshows_10.csv",";");



     //main method end
    }
}