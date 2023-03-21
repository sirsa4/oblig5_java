package model;

//oppgave2.5 - create "Rolle" class
public class Rolle {
    //oppgave2.5 instance variables
    private String rolleFornavn;
    private String rolleEtternavn;

    private Person skuespiller;

    public Rolle() {
    }

    public Rolle(String rolleFornavn, String rolleEtternavn, Person skuespiller){
        this.rolleFornavn = rolleFornavn;
        this.rolleEtternavn = rolleEtternavn;
        this.skuespiller = skuespiller;
    }

    //getter and setter methods
    public String getRolleFornavn(){
        return this.rolleFornavn;
    }
    public void setRolleFornavn(String rolleFornavn){
        this.rolleFornavn = rolleFornavn;
    }
    public String getRolleEtternavn(){
        return this.rolleEtternavn;
    }
    public void setRolleEtternavn(String rolleEtternavn){
        this.rolleEtternavn = rolleEtternavn;
    }

    public Person getSkuespiller(){
        return this.skuespiller;
    }
    public void setSkuespiller(Person skuespiller){
        this.skuespiller = skuespiller;
    }

    //oppgave2,6 - toString()
    //by default toString() method from Object return the memory address location of the instance.
    //we can override this method to return something else
    //here toString() returns generic information of the object.
    @Override
    public String toString(){
        return "Rolle navn: "+ this.rolleFornavn+" "+this.rolleEtternavn+ ". skuespiller: "+this.skuespiller.getFullName();
    }


}//end of class
