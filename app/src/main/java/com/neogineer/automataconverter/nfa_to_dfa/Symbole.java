/*
 * Symbole.java
 */
package com.neogineer.automataconverter.nfa_to_dfa;


/**
 *
 * @author DO
 */
public class Symbole {
    private String valeur;

    /**
     * constructer
     * @param valeur 
     */
    public Symbole(String valeur) {
        this.valeur = valeur;
    }

    /**
     * getter and setter
     * @return 
     */
    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    /**
     * method equals
     * @param obj
     * @return 
     */
    public boolean equals(Symbole obj) {
        return this.valeur.equals(obj.valeur);
    }

    /**
     * method toString
     * @return 
     */
    @Override
    public String toString() {
        return valeur ;
    }
    
}
