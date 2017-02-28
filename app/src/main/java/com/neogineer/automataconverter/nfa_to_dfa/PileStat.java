/*
 * PileStat.java
 */
package com.neogineer.automataconverter.nfa_to_dfa;

/**
 *
 * @author DO
 */
import java.util.ArrayList;

public class PileStat {
	
        /**
         * pile last in first out
         */
	private ArrayList<Stat> pile = new ArrayList<Stat>();
	
        /**
         * push method
         * @param n 
         */
	public void empiler(Stat n)
	{
            pile.add(n);
	}
	
        /**
         * pop method
         * @return 
         */
	public Stat depiler()
	{
            if(!pile.isEmpty())
            {
                Stat a = pile.get(pile.size()-1);
                boolean remove = pile.remove(pile.get((pile.size())-1));
                return a;
            }
            else
            {
                return null;
            }
            
	}
	
	public boolean estVide()
	{
            return pile.isEmpty();
	}


}
