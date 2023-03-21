package model;
import java.time.LocalDate;


public class Film extends Produksjon {

    //oppgave2.3
   // private LocalDate utgivelsesdato;

    //oppgave2.1 and oppgave 2.2
    //in oppgave2.3 utgivelsesdato instance variable is added to constructor
    public Film(String title, double spilletid, LocalDate utgivelsesdato){
        super(title,spilletid,utgivelsesdato);
    }

    //oppgave2.3
    //method overall which has all 5 needed instance variables so far
    public Film(String title, double spilletid,String beskrivelse, LocalDate utgivelsesdato){
        super(title,spilletid,beskrivelse,utgivelsesdato);
    }





}//end of class
