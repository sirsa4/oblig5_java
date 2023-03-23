package data;

import model.Episode;
import model.Person;
import model.TVSerie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TvSerieCSVRepository implements TvSerieRepository {
    //oppgave 2.2-a Tvserie from csv
    private ArrayList<TVSerie> serieCSV = new ArrayList<>();
    private HashMap<String, TVSerie> serieHash = new HashMap<>();
    public TvSerieCSVRepository(String filePath,String splitter){



        try(BufferedReader readCSV = new BufferedReader(new FileReader(filePath))){
            //line which stores each line in csv
            String line;
            //skip first line since header is text
        // readCSV.readLine();
            //while loop read each line in csv
            while((line = readCSV.readLine()) != null){
                String[] values = line.split(splitter);
             //   System.out.println(values[0] + " "+ values[1] + " "+values[2]+ " "+ values[3]+ " "+ values[4]+ " "+values[5]+ " "+values[6] + " "+values[7]+ " "+values[8]+ " "+values[9] + " "+values[10] + " "+values[11]+ " "+values[12] + " "+values[13]);

                //Tvserie
                String title = values[0];
                String beskrivelse = values[1];
                String serieDato = values[2];
                String serieBildeurl = values[3];

                //episodes
                String epTitle = values[4];
                String episodeBeskrivelse = values[5];
                String episodeNr = values[6]; //usikker litt
                String sesongNr = values[7]; //sesongNr
                String spilleTid = values[8]; //spilleTid?
                String episodeDato = values[9];
                String episodeBildeurl = values[10];
                //skue spiller
                String skueSpillerFornavn = values[11];
                String skuespillerEtternavn = values[12];
                String skuespiller_birthday = values[13];


                // I struggled to get HashMap to work. So i got help from student and chatGPT that i needed to store TVSerie title in
                //HashMap
                TVSerie serie = serieHash.get(title);

                if (serie == null) {
                    serie = new TVSerie(title,beskrivelse,LocalDate.parse(serieDato),serieBildeurl);
                    //add serie to hashmap
                    serieHash.put(title, serie);
                    //add serie object to the ArrayList
                    serieCSV.add(serie);
                  //  System.out.println(title);
                }
                //Add episode to serie
                Episode episode = new Episode(epTitle,Integer.parseInt(episodeNr),Integer.parseInt(sesongNr),Double.parseDouble(spilleTid),episodeBeskrivelse,LocalDate.parse(episodeDato),episodeBildeurl);
                Person regissor = new Person(skueSpillerFornavn,skuespillerEtternavn,LocalDate.parse(skuespiller_birthday));
                episode.setRegissor(regissor);
                serie.leggTilEpisode(episode);





            } // while loop ends here



        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }


    }

    @Override
    public TVSerie getSingleTVSerie(String tvSerie) {
        for(TVSerie serie : serieCSV){
            if(serie.getTitle().equals(tvSerie)){
                return serie;
            }
        }
        return null;
    }

    @Override
    public ArrayList<TVSerie> getAllTVSerie() {
        return new ArrayList<>(serieCSV);
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
}
