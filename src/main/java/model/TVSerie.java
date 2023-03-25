package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/*
* samarbeidet med Mostafa Ali Haider
--Resources:
1: class in class,ArrayList: hiof: https://www.youtube.com/watch?v=E2OBcAOG4J0&list=PLEvoVHoL8DxNUskHuf1YnOctQBLnO_F_F&index=9
2: ArrayList: https://www.youtube.com/watch?v=pTAda7qU4LY&t=2s
3: LocalDate: https://www.youtube.com/watch?v=dOvYkzKfsdg
4: oracle LocalDate: https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/time/LocalDate.html
5: How to have LocalDate instance variable with parameter from simple chatGPT:

    private LocalDate date;

    public Example(LocalDate date) {
        this.date = date;
    }
6: toString method: https://www.youtube.com/watch?v=GvbdMwfjB98
7: calculate average: https://www.youtube.com/watch?v=KXuQQh6AynQ
 */
public class TVSerie implements Comparable<TVSerie> {

    //instance variables
    @JsonProperty("tittel")
    private String title;
    private String beskrivelse;
    private LocalDate utgivelsesdato;
    ArrayList<Episode> episoder;

    private double gjennomsnittligSpilletid = 0;


    private int antallSesonger;

    //oppgave 2.8 - instance which stores image url for TVSerie
    private String bildeUrl;

    public TVSerie() {
    }

    public TVSerie(String title, String beskrivelse, LocalDate utgivelsesdato){

        this.title = title;
        this.beskrivelse = beskrivelse;
        this.utgivelsesdato = utgivelsesdato;
        this.episoder = new ArrayList<>();
    }

    //oblig4: oppgave 2.8 - constructor method overload which includes "bildeUrl" instance variable
    //method overload is avoid objects from this class in other place is to break the program.
    public TVSerie(String title, String beskrivelse,LocalDate utgivelsesdato,String bildeUrl){
        this.title = title;
        this.beskrivelse = beskrivelse;
        this.utgivelsesdato = utgivelsesdato;
        this.episoder = new ArrayList<>();
        this.bildeUrl = bildeUrl;
    }

    //delete episode
    public void deleteEpisodeInSeason(int sesongNr, int episodeNr){
        //This loop did not work and I am not sure what the issue
        //but Intellij gave me 2 quick fix options that solved the issue with deleting episode
        /*
        for(Episode episode : episoder){

            if(episode.getSesongNr() == sesongNr && episode.getEpisodeNr() == episodeNr){
                episoder.remove(episode);
            }
        }

         */

        //option 1: collection removeIf()
       // episoder.removeIf(episode -> episode.getSesongNr() == sesongNr && episode.getEpisodeNr() == episodeNr);

        //option 2: iterator interface. This look more understandable compared to option 1. So i picked this in the end.
        Iterator<Episode> iterator = episoder.iterator();

        while (iterator.hasNext()) {
            Episode episode = iterator.next();
            if (episode.getSesongNr() == sesongNr && episode.getEpisodeNr() == episodeNr) {
                episoder.remove(episode);
            }
        }



    }
    //oppgave 2.4 - create and add episode to TVSerie object
    public void createAndAddEpisode(String title, int sesonNr, int episodeNr, String beskrivelse, double spilletid, LocalDate utgivelsesdato, String episodeBilde){

        //create new episode. This value are form in Vue component which have traveled all the way down to this method.
        Episode newEpisode = new Episode(title,episodeNr,sesonNr,spilletid,beskrivelse,utgivelsesdato,episodeBilde);

        episoder.add(newEpisode);

    }

    //oppgave 2.5 update and change existing episode with new that user wants
    public void updateEpisode(String title, int sesongNummer, int episodeNummer, String beskrivelse, double spilletid, LocalDate ep_utgivelsesdato, String ep_bildeurl){

        //create new episode with that provided from fornt-end
        //this episode will replace the old one
        Episode newEpisode = new Episode(title,episodeNummer,sesongNummer,spilletid,beskrivelse,ep_utgivelsesdato,ep_bildeurl);
        System.out.println("update at tv series");

        //loop through episodes and update episode from correct season and episode number
        //I had normal for each loop which never worked. I get fix for below which chatGPT will showed me how replace..
        //..an element in ArrayList with new one.
        for(int i=0; i<episoder.size(); i++){
            //get index of ArrayList
            Episode episode = episoder.get(i);
            if(episode.getSesongNr() == sesongNummer && episode.getEpisodeNr() == episodeNummer){
                //replace element at position of old episode with new one
                episoder.set(i, newEpisode);
                break;
            }
        }
    }


    public String getBildeUrl() {
        return bildeUrl;
    }

    public void setBildeUrl(String bildeUrl) {
        this.bildeUrl = bildeUrl;
    }

    //oblig 4: oppgave 2.2 - comparable
    @Override
    public int compareTo(TVSerie anotherTVSerie){
        return this.episoder.size() - anotherTVSerie.episoder.size();
    }




    public int getAntallSesonger(){
        return this.antallSesonger;
    }


    public double getGjennomsnittligSpilletid() {
        return gjennomsnittligSpilletid;
    }


    private void oppdaterGjennomsnittligSpilletid(){
        //variable starting at 0 to store total spilletid
        double totalSpilleTid = 0;
        //loop through episode ArrayList and get spillestid from each episode
        for(int i = 0; i < episoder.size(); i++){
            totalSpilleTid = totalSpilleTid + episoder.get(i).getSpilletid();
        }

        //average is: totalSpillTid / total episodes number.
        gjennomsnittligSpilletid = totalSpilleTid / episoder.size();
    }


    public ArrayList<Episode> hentEpisoderISesong(int sesong){

        //new ArrayList to hold the episodes from specified sesong
        ArrayList<Episode> sesongEpisodes = new ArrayList<>();

        //loop through entire episode ArrayList first, then return only episodes which season specified by the user in this methods parameter(sesong)
        for(int i = 0; i < episoder.size(); i++){

            //with if-statement get only episodes which correct sesong number
            if(episoder.get(i).getSesongNr() == sesong){

                //add the episodes with correct sesong number to new empty array "sesongEpisodes"
                sesongEpisodes.add(episoder.get(i));
            }
        }

        //return new ArrayList from this method. The list returned can looped through. This is done in the Main class running the program.
        return sesongEpisodes;

    //method end
    }


    public String toString(){
        return "TVSerie \""+this.title +"\" aired on the television for the first time in "+this.utgivelsesdato + ". This program has a basic data of "+ this.episoder.size() + " random episodes across different seasons in "+this.title+".\n";
    }

    //getter and setter methods
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getBeskrivelse(){
        return beskrivelse;
    }
    public void setBeskrivelse(String beskrivelse){
        this.beskrivelse = beskrivelse;
    }

    //setter and getter methods for
    public LocalDate getUtgivelsesdato(){
        return this.utgivelsesdato;
    }
    public void setUtgivelsesdato(LocalDate utgivelsesdato){
        this.utgivelsesdato = utgivelsesdato;
    }

    //Episode array instance variable setter and methods. They are a bit different. This we have learned from hiof prog2 video "klasser i klasser" from 2021

    //get a new copy of episodes. This is to avoid giving the user ability to change data inside the original ArrayList of episodes. Returning new instance basically.
    public ArrayList<Episode> getEpisoder(){
        return new ArrayList<Episode>(episoder);
    }

    //legg til method
    public void leggTilEpisode(Episode episode){

        //oppgave2.7
        //In oppgave2.7, we are limiting the episodes that can be added only if episode has in part season that already exists or seasons that is 1 more than current highest season number.
        //In if-statement we have 2 conditions with OR-operator. One of them has to be true for condition to be fulfilled. The first one checks that epsiode number(episode.getSesongNr()) is not bigger than newly added season. The second condition checks that the current added episode is equal to 1 season higher only.
        if(episode.getSesongNr() <= antallSesonger || episode.getSesongNr() == antallSesonger + 1){

            //ArrayList class has method add() to add new item the array.
            episoder.add(episode);

            //This method updates the total average spillestid of episodes.
            //it runs everytime new episode is added to TVSerie object(futurama).
            oppdaterGjennomsnittligSpilletid();

            /*
            Here I struggled to update antallSesonger instance variable properly. I got help from student assistant to another if-statement just for the antallSesonger.
            * */
            if(episode.getSesongNr() == antallSesonger + 1){
                antallSesonger++;
            }




        } else {

            System.out.printf("Invalid season number. You're try to add episode in season %d. Episode must in already existing season or atleast season: %d\n",episode.getSesongNr(),(antallSesonger + 1));
        }

    }


    public ArrayList<Rolle> hentRollebesetning(){

        //empty array to roller from episodes
         ArrayList<Rolle> roller = new ArrayList<>();


         //loop through episoder ArrayList to have access to each single Episode object
         for(Episode ep : episoder){
             //getRoller() in Production class returns an ArrayList<roller> since each episode can have more than 1 Rolle.
             //so we need to ArrayList into the empty Arraylist above,roller.
             //this is done with .addAll() method which we get from the ArrayList class in java
             roller.addAll(ep.getRoller());
         }
         //finally return roller ArrayList which is no longer empty and now holds all ArrayList<Rolle> from all Episode objects.
        return roller;
    }



}//end of class

