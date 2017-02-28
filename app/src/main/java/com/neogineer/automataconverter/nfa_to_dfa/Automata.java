/*
 * Automata.java
 */
package com.neogineer.automataconverter.nfa_to_dfa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @author DO
 */
public class Automata {
    private ArrayList<Stat> Q = new ArrayList<Stat>();
    private ArrayList<Symbole> E = new ArrayList<Symbole>();
    private Transitions T = new Transitions();
    private Stat q0;
    private ArrayList<Stat> F = new ArrayList<Stat>();

    /**
     * visualising the automata
     */
    public void AfficheAuto(){
        System.out.println("Automata: qo="+q0.toString());
        System.out.print("Q=");
        for(Stat a:Q){System.out.print("{"+a.toString()+"} ");} 
        System.out.println();
        System.out.print("E= | ");
        for(Symbole a:E){System.out.print(a.toString()+" | ");} 
        System.out.println();
        System.out.print("F=");
        for(Stat a:F){System.out.print("{"+a.toString()+"} ");} 
        System.out.println();
        this.T.AfficheTrans();
    }
    
    /**
     * returning the automata as a string
     * @return 
     */
    public String toString(){
        String t = "Automata: qo="+q0.toString();
        t += "\n Q=";
        for(Stat a:Q){ t+= "{"+a.toString()+"} ";} 
        t += "\n E= | ";
        for(Symbole a:E){t += a.toString()+" | ";} 
        t += "\n F=";
        for(Stat a:F){t += "{"+a.toString()+"} ";} 
        t += this.T.toString();
        return t;
    }

    /**
     * initialising the automata
     * @param q0 
     */
    public Automata(Stat q0) {
        this.q0 = q0;
    }

    /**
     * getters and setters for the automata's elements
     * @return 
     */
    public ArrayList<Stat> getQ() {
        return Q;
    }

    public void setQ(ArrayList<Stat> Q) {
        this.Q.add(q0);
        this.Q.addAll(Q);
    }

    public ArrayList<Symbole> getE() {
        return E;
    }

    public void setE(ArrayList<Symbole> E) {
        this.E = E;
    }

    public Transitions getT() {
        return T;
    }

    public void setT(Transitions T) {
        this.T = T;
    }


    public Stat getQ0() {
        return q0;
    }

    public void setQ0(Stat q0) {
        this.q0 = q0;
    }
    
    public ArrayList<Stat> getF() {
        return F;
    }

    public void setF(ArrayList<Stat> F) {
        this.F = F;
    }

    
    /**
     * factorial function 
     * @param N
     * @return 
     */
    public static long factorial(int N)
    {
        long multi = 1;
        for (int i = 1; i <= N; i++) {
            multi = multi * i;
        }
        return multi;
    }
    
    /**
     * method checking if a the Stat set of the automata contains a certain stat
     * personnalised "contains()":
     * it checks if a stat(no matter how its components are organised) is indeed
     * in the Stats set
     * 
     * @param q
     * @return 
     */
    public boolean Qcontient(Stat q)
    {
        boolean t = false;int i = 0; int j = 0;
        
        while(!t && i<this.Q.size())
        {
            Stat m = this.Q.get(i);
            
            if(m.equals(q))
            {
                t = true;
            }
            else
            {
                Collections.shuffle(m.getValeur());
                j++;if(j==factorial(q.getValeur().size())){i++;j=0;}
            }
            
        }
        return t;
    }

    
        
    //this method only convert an NFA with no epsilon transition 
    public Automata Convert() {
        
        //initialising the first stat of the DFA 
        Automata DFA;
        DFA = new Automata(this.q0);
        DFA.Q.add(this.q0);
        
        //initialising a temporary set of new states formed by subsets of states of Q
        PileStat Temp = new PileStat();
        Temp.empiler(this.q0);
        
        //loop
        while(!Temp.estVide())
        { 
            Stat t = Temp.depiler();
            for(Symbole s:this.E)
            {
                if(t!=null && this.T.checkTrans(t, s))
                {
                    
                    /**
                     * getting the transitions formed by t and s
                     */
                     
                    Stat k = this.T.getStatTrans(t, s);
                    for(int l= 0; l<k.getValeur().size();l++)
                    {
                        for(int m=0; m<k.getValeur().size();m++)
                        {
                            if(k.getValeur().get(m).equals(k.getValeur().get(l)) && l!=m)
                            {
                                k.getValeur().remove(k.getValeur().get(m));
                                l=m=0;
                            }
                        }
                    }
                    /**
                     * checking if our DFA contains already the stat
                     * produced by t and s
                     */
                     
                    if(!DFA.Qcontient(k))
                    {
                        
                        /**
                         * in case it doesn't contain the stat 
                         * we add that stat to the DFA stat table
                         * and to the temporary table
                         * (we use getEtat2 because getEtat1 reffers to t) 
                         */
                         
                        DFA.Q.add(k);
                        Temp.empiler(k);
                    }   
                    
                    /**
                     * and we create transition formed by t and Etat2
                     * in the DFA
                     */
                     
                    if(!DFA.T.checkTrans1(t, s))
                    {
                        DFA.T.createTrans(s, new CoupleStat(t,k));
                        if(!DFA.E.contains(s))
                        {
                            DFA.E.add(s);
                        }
                    }
                    
                }
                
            }
        }
        
        
        //filling the table of final stats of the DFA
        for(Stat o:DFA.Q)
        {
            int i = 0;
            while(i<this.F.size())
            {
                if(o.getValeur().contains(this.F.get(i).toString()) && !DFA.F.contains(o))
                {
                    DFA.F.add(o);
                    i++;
                }
                else
                {
                    i++;
                }
            }
        }
        
        return DFA;
        
    }
    
 
       
    
    /**
     * This Method converts an NFA containing epsilon transitions
     * into a DFA using the method above
     */
    public Automata ConvertEpsilon() {
        
        //initialising the first stat of the DFA 
        Automata DFA;
        DFA = new Automata(this.q0);
        DFA.Q.add(this.q0);
        
        //initialising a temporary set of new states formed by subsets of states of Q
        PileStat Temp = new PileStat();
        Temp.empiler(this.q0);
        
        //loop
        for(int i = 0 ; i <this.Q.size() ; i++)
        { 
            Stat t =this.Q.get(i);
            if(this.T.checkTrans(t, new Symbole("epsilon")))
                {
                    //getting the transitions formed by t and epsilon and deleting them
                     //first group the epsilon stats
                    Stat k = this.T.getStatTrans(t, new Symbole("epsilon"));
                    
                    for(Stat o:this.Q)
                    {
                        if(o.equals(t))
                        {
                            o.ajout(k.toString());
                        }
                    }
                    
                    //group them in the transitions
                    Iterator<Symbole> it = this.T.getTable().keySet().iterator();
                    while(it.hasNext() )
                    { 
                	Symbole key = it.next(); 
                        CoupleStat c = this.T.getTable().get(key); 
                        if(t.getValeur().get(0).equals(c.getEtat1().toString()) )
                        {
                            c.getEtat1().ajout(k.toString());
                        }
                        if(t.getValeur().get(0).equals(c.getEtat2().toString()) )
                        {
                            c.getEtat2().ajout(k.toString());
                        }
                    }
                    
                    //remove the epsilon trans
                    for(int p = 0;p<this.T.getTable().size();p++)
                    {
                        it = this.T.getTable().keySet().iterator();
                        boolean y = false;
                        while(it.hasNext() && !y)
                        { 
                            Symbole key = it.next(); 
                            CoupleStat c = this.T.getTable().get(key); 
                            if(t.contient(c.getEtat1().toString()) && key.equals(new Symbole("epsilon")))
                            {
                                this.T.getTable().remove(key);
                                y = true;
                            }
                        }   
                    }
                    
                    //remove the epsilon symbole
                    int l = 0;boolean p = false;
                    while(l<this.E.size() && !p)
                    {
                        Symbole e = this.E.get(l);
                        if(e.equals(new Symbole("epsilon")))
                        {
                            this.E.remove(e);
                            p = true;
                        }
                        else
                        {
                            l++;
                        }
                    }
                    
                }
        }
        this.AfficheAuto();
        DFA = this.Convert();
        return DFA;
        
    }
}
