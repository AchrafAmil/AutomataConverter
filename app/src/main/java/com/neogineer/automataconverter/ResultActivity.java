package com.neogineer.automataconverter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.neogineer.automataconverter.nfa_to_dfa.Automata;
import com.neogineer.automataconverter.nfa_to_dfa.CoupleStat;
import com.neogineer.automataconverter.nfa_to_dfa.Stat;
import com.neogineer.automataconverter.nfa_to_dfa.Symbole;
import com.neogineer.automataconverter.nfa_to_dfa.Transitions;

import java.util.ArrayList;
import java.util.Hashtable;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ResultActivity.this)
                        .setTitle("Automata Converter")
                        .setMessage("Made with love by ENSA Kenitra, Morocco students: \n Dounia Outaleb \nKhaoula El Ayoubi \n Achraf Amil")
                        .setIcon(R.drawable.ic_group_black_48dp)
                        .show();
            }
        });

        TextView nfaText = (TextView) findViewById(R.id.nfaOutput);
        TextView dfaText = (TextView) findViewById(R.id.dfaOutput);

        final ArrayList<String> states = getIntent().getStringArrayListExtra("states");
        final String start = getIntent().getStringExtra("start");
        final ArrayList<String> ends = getIntent().getStringArrayListExtra("ends");
        final ArrayList<String> symbols = getIntent().getStringArrayListExtra("symbols");
        final ArrayList<Transition> transitions = getIntent().getParcelableArrayListExtra("transitions");

        ArrayList<Stat> Q = new ArrayList<Stat>();
        ArrayList<Symbole> E = new ArrayList<Symbole>();
        ArrayList<Stat> F = new ArrayList<Stat>();

        //entered automata with stat S0
        Automata A = new Automata(new Stat(start));

        for(String s : states){
            if(!s.equals(start))
                Q.add(new Stat(s));
        }
        A.setQ(Q);

        for(String s : symbols){
            E.add(new Symbole(s));
        }
        A.setE(E);


        Hashtable<Symbole, CoupleStat> t = new Hashtable<>();
        for(Transition trans : transitions){
            t.put(new Symbole(trans.symbol), new CoupleStat(new Stat(trans.from),new Stat(trans.to)));
        }
        Transitions T = new Transitions(t);
        A.setT(T);

        for(String s : ends){
            F.add(new Stat(s));
        }
        A.setF(F);

        nfaText.setText(A.toString());

        // convert automata and print results
        dfaText.setText(A.ConvertEpsilon().toString());



    }

}
