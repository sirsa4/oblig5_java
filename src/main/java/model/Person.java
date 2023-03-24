package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

//oppgave2.4
public class Person {
    private String fornavn;
    private String etternavn;
    private LocalDate fodselsDato;

    public Person() {
    }

    public Person(String fornavn, String etternavn){
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    public Person(String fornavn, String etternavn, LocalDate fodselsDato) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.fodselsDato = fodselsDato;
    }

    public LocalDate getFodselsDato() {
        return fodselsDato;
    }

    public void setFodselsDato(LocalDate fodselsDato) {
        this.fodselsDato = fodselsDato;
    }

    //getter and setter methods
    public String getFornavn(){
        return this.fornavn;
    }
    public void setFornavn(String fornavn){
        this.fornavn = fornavn;
    }
    public String getEtternavn(){
        return this.etternavn;
    }
    public void setEtternavn(String etternavn){
        this.etternavn = etternavn;
    }

    //oppgave2.4 full name method
    @JsonIgnore
    public String getFullName(){
        return this.fornavn+" "+this.etternavn;
    }

    //oppgave2,6 - toString()
    //by default toString() method from Object return the memory address location of the instance.
    //we can override this method to return something else
    //here toString() returns generic information of the object.
    @Override
    public String toString(){
        return "Person fullnavn: "+this.getFullName();
    }

}
