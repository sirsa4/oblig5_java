package model;

/*
* samarbeidet med Mostafa Ali Haider
--Resources:
1: class in class,ArrayList: hiof: https://www.youtube.com/watch?v=E2OBcAOG4J0&list=PLEvoVHoL8DxNUskHuf1YnOctQBLnO_F_F&index=9
2: ArrayList: https://www.youtube.com/watch?v=pTAda7qU4LY&t=2s
3: LocalDate: https://www.youtube.com/watch?v=dOvYkzKfsdg
4: toString method: https://www.youtube.com/watch?v=GvbdMwfjB98
 */



import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;


public class    Episode extends Produksjon implements Comparable<Episode>{
    //instance variables

    @JsonProperty("episodeNummer")
    private int episodeNr;

    @JsonProperty("sesongNummer")
    private int sesongNr;

    public Episode() {
    }

    //contructor with all instance variables
    public Episode(String title,int episodeNr, int sesongNr,double spilletid){
        super(title,spilletid);
        this.episodeNr = episodeNr;
        this.sesongNr = sesongNr;


    }

    public Episode(String title, int episodeNr, int sesongNr, double spilletid, String beskrivelse, LocalDate utgivelsesdato){
        super(title,spilletid,beskrivelse,utgivelsesdato);
        this.episodeNr = episodeNr;
        this.sesongNr = sesongNr;
    }
    //oblig 4: oppgave 2.8 - contructor overload where episode can have bildUrl
    public Episode(String title, int episodeNr, int sesongNr, double spilletid, String beskrivelse, LocalDate utgivelsesdato,String bildeUrl){
        super(title,spilletid,beskrivelse,utgivelsesdato,bildeUrl);
        this.episodeNr = episodeNr;
        this.sesongNr = sesongNr;
    }

    //oblig4 - compareTo()
    @Override
    public int compareTo(Episode ep) {
        return (int) (this.getSpilletid() - ep.getSpilletid());
    }


    public String toString(){
        return "Episode number: "+this.episodeNr + " \""+this.getTitle() + "\" is from season "+this.sesongNr+" and lasts for "+this.getSpilletid()+" min.\n";
    }

    //getter and setter methods
    public int getEpisodeNr(){
        return this.episodeNr;
    }
    public void setEpisodeNr(int episodeNr){
        this.episodeNr = episodeNr;
    }
    public int getSesongNr(){
        return this.sesongNr;
    }
    public void setSesongNr(int sesongNr){
        this.sesongNr = sesongNr;
    }

}
