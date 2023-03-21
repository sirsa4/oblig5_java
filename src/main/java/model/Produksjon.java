package model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;




public abstract class Produksjon {

    @JsonProperty("tittel")
    private String title;
    private double spilletid;


    private String beskrivelse;
    private LocalDate utgivelsesdato;

    private Person regissor;

    @JsonProperty("rolleListe")
    private ArrayList<Rolle> roller = new ArrayList<>();

    //oblig4: oppgave 2.8 - instance variable for images in Episode and Film objects
    private String bildeUrl;

    //Empty constructor is needed for the constructor in Episode class which is missing spilletid instance variable
    public Produksjon(){}

    public Produksjon(String title, double spilletid){
        this.title = title;
        this.spilletid = spilletid;
    }

    public Produksjon(String title, double spilletid,LocalDate utgivelsesdato){
        this.title = title;
        this.spilletid = spilletid;
        this.utgivelsesdato = utgivelsesdato;
    }

    public Produksjon(String title, double spilletid,String beskrivelse,LocalDate utgivelsesdato){
        this.title = title;
        this.spilletid = spilletid;
        this.beskrivelse = beskrivelse;
        this.utgivelsesdato = utgivelsesdato;
    }

    //oblig 4: oppgave 2.8
    public Produksjon(String title, double spilletid,String beskrivelse,LocalDate utgivelsesdato, String bildeUrl){
        this.title = title;
        this.spilletid = spilletid;
        this.beskrivelse = beskrivelse;
        this.utgivelsesdato = utgivelsesdato;
        this.bildeUrl = bildeUrl;
    }



    //oblig4: oppgave 2.8 - getter and setter methods for bildeUrl instance variable


    public String getBildeUrl() {
        return bildeUrl;
    }

    public void setBildeUrl(String bildeUrl) {
        this.bildeUrl = bildeUrl;
    }

    //getter and setter for title and spilletid in oppgave2.1
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public double getSpilletid(){
        return this.spilletid;
    }
    public void setSpilletid(double spilletid){
        this.spilletid = spilletid;
    }

    //oppgave2.3
    //Setter and getter methods for instance variable "utgivelsesdato".
    //setter getter methods for utgivelsesdato is moved from Film class to here.
    public LocalDate getUtgivelsesdato(){
        return this.utgivelsesdato;
    }
    public void setUtgivelsesdato(LocalDate utgivelsesdato){
        this.utgivelsesdato = utgivelsesdato;
    }

    public String getBeskrivelse(){
        return this.beskrivelse;
    }
    public void setBeskrivelse(String beskrivelse){
        this.beskrivelse = beskrivelse;
    }

    //oppgave2.4 setter and getter method for regissor instance variable
    public Person getRegissor(){
        return this.regissor;
    }
    public void setRegissor(Person regissor){
        this.regissor = regissor;
    }

    //oppgave2.5 - getter and setter methods for Episode and Film classes
    //just like in oblig2, getter method for ArrayList returns copy of the original list. The reason for security so that user of this method can't manipulate the content of the original rolle ArrayList
    public ArrayList<Rolle> getRoller(){
        return new ArrayList<>(roller);
    }

    //This method adds only 1 rolle object to roller ArrayList
    public void leggTilEnRolle(Rolle enRolle){
        roller.add(enRolle);
    }

    //This method adds several items to rolle Arraylist at once
    //source for addAll(): https://www.baeldung.com/java-add-items-array-list
    /* code example from baeldung

        List<Integer> anotherList = Arrays.asList(5, 12, 9, 3, 15, 88);
        list.addAll(anotherList);

     */
    public void leggTilMangeRoller(ArrayList<Rolle> flereRoller){
        roller.addAll(flereRoller);
    }

    //oppgave2.6 - toString

    @Override
    public String toString(){
        return  this.title +" har spilletid: "+this.spilletid+ " minutter";
    }


}//end of class
