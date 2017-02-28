/*
 * Stat.java
 */
package com.neogineer.automataconverter.nfa_to_dfa;

import java.util.ArrayList;

/**
 *
 * @author DO
 */
public class Stat {
    private ArrayList<String> valeur;

    /**
     * Stat constructers
     */
    public Stat() {
        this.valeur = new ArrayList<String>();
    }
    
    public Stat(String valeur) {
        this.valeur = new ArrayList<String>();
        this.valeur.add(valeur);
    }

    /**
     * toString method
     * @return 
     */
    @Override
    public String toString() {
        String a = "";
        for(int i = 0;i<(this.valeur.size())-1;i++)
        {
            a+=this.valeur.get(i)+",";
        }
        a+=this.valeur.get(this.valeur.size()-1)+"";
        return a;
    }

    /**
     * getter and setter
     * @return 
     */
    public ArrayList<String> getValeur() {
        return valeur;
    }

    public void setValeur(ArrayList<String> valeur) {
        this.valeur = valeur;
    }

    /**
     * method equals
     * 
     * @param obj
     * @return 
     */
    public boolean equals(Stat obj) {
        if(obj==null)
        {
            return false;
        }
        else
        {
            if(obj.valeur.size()== this.valeur.size())
            {
                boolean t = true; int i = 0;
                while(t && i<this.valeur.size() )
                {
                    if(!this.valeur.get(i).equals(obj.valeur.get(i)))
                    {
                        t = false;
                    }
                    else
                    {
                        i++;
                    }
                }
                return t;
            }
            else
            {
                return false;
            }
        }
        
    }
    
    /**
     * customised method adding for the stat elements to another stat
     * Concatenate the stat object and the enterred String
     * 
     * @param a 
     */
    public void ajout(String a)
    {
        for(String h:a.split(","))
            this.valeur.add(h);
    }
    
    /**
     * method checking if a String is contained in the stat's elements
     * 
     * @param a
     * @return 
     */
    public boolean contient(String a)
    {
        return this.toString().contains(a);
    }

}
