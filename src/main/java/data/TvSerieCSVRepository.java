package data;

import model.Episode;
import model.Person;
import model.TVSerie;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TvSerieCSVRepository implements TvSerieRepository {
    //oppgave 2.2-a Tvserie from csv
    private ArrayList<TVSerie> serieCSV;
    //used LinkedHashMap to get guaranteed order of elements in hashmap
    LinkedHashMap<String, TVSerie> serieHash = new LinkedHashMap<>();
  //  private HashMap<String, TVSerie> serieHash = new HashMap<>();
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
                //Here in first iteration of loop, the serieHash<title> is null. There is no value in it.
                //"title" is the key. So it has corresponding value.
                TVSerie serie = serieHash.get(title);

                //When there is no value in "title", new TVserie object is created, also title with value is put() in serieHash
                //this means as long serieHash<title> is in not empty/null, no duplicate of current <title> will be created this.
                //this way below the if-block, episodes can be added to their correct TVSerie object
                //
                if (serie == null) {
                    //create tvserie from current iteration
                    serie = new TVSerie(title,beskrivelse,LocalDate.parse(serieDato),serieBildeurl);
                    //add serie object as value to hashmap
                    serieHash.put(title, serie);
                    //add serie object to the ArrayList
                  //  serieCSV.add(serie);
                  //  System.out.println(title);
                }
                //Add episode to serie
                Episode episode = new Episode(epTitle,Integer.parseInt(episodeNr),Integer.parseInt(sesongNr),Double.parseDouble(spilleTid),episodeBeskrivelse,LocalDate.parse(episodeDato),episodeBildeurl);
                Person regissor = new Person(skueSpillerFornavn,skuespillerEtternavn,LocalDate.parse(skuespiller_birthday));
                episode.setRegissor(regissor);
                serie.leggTilEpisode(episode);





            } // while loop ends here

            serieCSV = new ArrayList<>(serieHash.values());
            System.out.println(serieHash.keySet());

            //oppgave 2.2-C not working well
         writeToCSV(serieCSV,"mycsv.csv",splitter);

        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }


    } //end of constructor


    //oppgave 2.2-C: write data from TVSerie ArrayList to csv file
    public void writeToCSV(ArrayList<TVSerie> series, String filePath,String split){

        //column split
       // String split = ";";


        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){


            for(TVSerie serie : series){
                //not done - add remaning data
                for(Episode ep : serie.getEpisoder()){
                    //Tv serie
                    writer.write(serie.getTitle()+ split+ serie.getBeskrivelse()+split+ serie.getUtgivelsesdato()+split+serie.getBildeUrl()+split);

                    //episode
                    writer.write(ep.getTitle()+split+ep.getBeskrivelse()+split+ep.getEpisodeNr()+split+ep.getSesongNr()+split+ep.getSpilletid()+split+ep.getUtgivelsesdato()+split+ep.getBildeUrl()+split+ep.getRegissor().getFornavn()+split+ep.getRegissor().getEtternavn()+split+ep.getRegissor().getFodselsDato());

                   // new line
                    writer.newLine();

                }


               // System.out.println(count+ " "+ serie.getTitle());
            }


        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    //oppgave 2.3 - a create, update, delete methods


    @Override
    public void createEpisode(String tvserie, String title, int sesonNr, int episodeNr, String beskrivelse, double spilletid, LocalDate utgivelsesdato, String bildeurl) {

    }

    @Override
    public void updateEpisode(String tvserie, int sesongNr, int episodeNr) {

    }

    @Override
    public void deleteEpisode(String tvserie, int sesongNr, int episodeNr) {

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
