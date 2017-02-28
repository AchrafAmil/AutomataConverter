package com.neogineer.automataconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TransitionsActivity extends AppCompatActivity {

    int count = 0 ;

    final ArrayList<ArrayList<Spinner>> allSpinners = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transitions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);

        final LinearLayout.LayoutParams spinnerParams = new LinearLayout.LayoutParams(
                0, LinearLayoutCompat.LayoutParams.WRAP_CONTENT, 1);




        final LinearLayout ll = (LinearLayout) findViewById(R.id.transitionsFields);


        final ArrayList<String> states = getIntent().getStringArrayListExtra("states");
        final String start = getIntent().getStringExtra("start");
        final ArrayList<String> ends = getIntent().getStringArrayListExtra("ends");
        final ArrayList<String> symbols = getIntent().getStringArrayListExtra("symbols");

        final String[] statesArray = new String[states.size()];
        for(String state:states){
            statesArray[states.indexOf(state)] = state;
        }
        final String[] symbolsArray = new String[symbols.size()];
        for(String symbol:symbols){
            symbolsArray[symbols.indexOf(symbol)] = symbol;
        }

        Button more = (Button) findViewById(R.id.moreButton);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout hll = new LinearLayout(TransitionsActivity.this);
                hll.setLayoutParams(params);
                hll.setOrientation(LinearLayout.HORIZONTAL);



                ArrayAdapter<String> statesAdapter = new ArrayAdapter<>(TransitionsActivity.this,
                        R.layout.spinner_item, statesArray);

                ArrayAdapter<String> symbolsAdapter = new ArrayAdapter<>(TransitionsActivity.this,
                        R.layout.spinner_item, symbolsArray);

                Spinner states1 = new Spinner(TransitionsActivity.this);
                states1.setLayoutParams(spinnerParams);
                states1.setAdapter(statesAdapter);

                Spinner symbols = new Spinner(TransitionsActivity.this);
                symbols.setLayoutParams(spinnerParams);
                symbols.setAdapter(symbolsAdapter);

                Spinner states2 = new Spinner(TransitionsActivity.this);
                states2.setLayoutParams(spinnerParams);
                states2.setAdapter(statesAdapter);

                hll.addView(states1);
                hll.addView(symbols);
                hll.addView(states2);
                ArrayList<Spinner> spinners = new ArrayList<>();
                spinners.add(states1);
                spinners.add(symbols);
                spinners.add(states2);
                allSpinners.add(spinners);



                hll.setId(++count);
                ll.addView(hll);
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Transition> transitions = new ArrayList<>();
                for(ArrayList<Spinner> spinners: allSpinners ){
                    Transition trans = new Transition();
                    trans.from = String.valueOf(spinners.get(0).getSelectedItem());
                    trans.symbol = String.valueOf(spinners.get(1).getSelectedItem());
                    trans.to = String.valueOf(spinners.get(2).getSelectedItem());
                    transitions.add(trans);

                }

                Intent intent = new Intent(TransitionsActivity.this, ResultActivity.class);
                intent.putExtra("states", getIntent().getStringArrayListExtra("states"));
                intent.putExtra("start", getIntent().getStringExtra("start"));
                intent.putExtra("ends", getIntent().getStringArrayListExtra("ends"));
                intent.putExtra("symbols", getIntent().getStringArrayListExtra("symbols"));
                intent.putExtra("transitions", transitions );

                startActivity(intent);

                String toasted = "" ;
                for(Transition trans: transitions){
                    toasted += trans.toString() + "\n" ;
                }

                Toast.makeText(TransitionsActivity.this, toasted, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
