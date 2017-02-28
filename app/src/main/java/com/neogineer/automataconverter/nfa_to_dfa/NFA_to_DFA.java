/*
 * This program changes an AFN to an AFD
 */
package com.neogineer.automataconverter.nfa_to_dfa;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @author DO
 */
public class NFA_to_DFA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Stat> Q = new ArrayList<Stat>();
        ArrayList<Symbole> E = new ArrayList<Symbole>();
        ArrayList<Stat> F = new ArrayList<Stat>();
        
        //entered automata with stat S0
        Automata A = new Automata(new Stat("S0"));
        
        // adding stats of the automata
        Q.add(new Stat("S1"));
        Q.add(new Stat("S2"));
        Q.add(new Stat("S3"));
        Q.add(new Stat("S4"));
        A.setQ(Q);
        
        // adding symboles of the automata
        E.add(new Symbole("0"));
        E.add(new Symbole("1"));
        E.add(new Symbole("epsilon"));
        A.setE(E);
        
        // adding transitions of the automata
        Hashtable<Symbole, CoupleStat> t = new Hashtable<Symbole, CoupleStat>();
        t.put(new Symbole("1"), new CoupleStat(new Stat("S2"),new Stat("S3")));
        t.put(new Symbole("0"), new CoupleStat(new Stat("S2"),new Stat("S3")));
        t.put(new Symbole("0"), new CoupleStat(new Stat("S0"),new Stat("S1")));
        t.put(new Symbole("epsilon"), new CoupleStat(new Stat("S1"),new Stat("S2")));
        t.put(new Symbole("epsilon"), new CoupleStat(new Stat("S1"),new Stat("S4")));
        t.put(new Symbole("epsilon"), new CoupleStat(new Stat("S3"),new Stat("S4")));
        t.put(new Symbole("epsilon"), new CoupleStat(new Stat("S3"),new Stat("S2")));
        Transitions T = new Transitions(t);
        A.setT(T);
        
        // adding the final stat/stats
        F.add(new Stat("S4"));
        A.setF(F);
        
        // print automata
        A.AfficheAuto();
        
        // convert automata and print results
        A.ConvertEpsilon().AfficheAuto();
        
    }
    
    
}
