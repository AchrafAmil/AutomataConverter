/*
 * CouleStat.java
 */
package com.neogineer.automataconverter.nfa_to_dfa;

import java.util.Objects;

/**
 *
 * @author DO
 */
public class CoupleStat {
    private Stat etat1;
    private Stat etat2;

    /**
     * Constucter
     * @param etat1
     * @param etat2 
     */
    public CoupleStat(Stat etat1, Stat etat2) {
        this.etat1 = etat1;
        this.etat2 = etat2;
    }

    /**
     * getters and setters
     * @return 
     */
    public Stat getEtat1() {
        return etat1;
    }

    public void setEtat1(Stat etat1) {
        this.etat1 = etat1;
    }

    public Stat getEtat2() {
        return etat2;
    }

    public void setEtat2(Stat etat2) {
        this.etat2 = etat2;
    }
    
    /**
     * method equals
     * @param obj
     * @return 
     */
    public boolean equals(CoupleStat obj) {
        if (obj == null) {
            return false;
        }
        if (this.etat1.equals(obj.etat1) && this.etat2.equals(obj.etat2)) {
            return true;
        }
        else
        {
            return false;
        }
    }
  
}
