package com.neogineer.automataconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class StartStateActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_state);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.startStateList);

        final ArrayList<String> states = getIntent().getStringArrayListExtra("states");

        for(String state : states){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setLayoutParams(params);
            radioButton.setText(state);
            radioButton.setId(states.indexOf(state));
            radioButton.setTextSize(20);
            radioGroup.addView(radioButton);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checked = radioGroup.getCheckedRadioButtonId();
                if(checked<0) {
                    Snackbar.make(view, "Select one", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                Intent intent = new Intent(StartStateActivity.this, EndStatesActivity.class);
                intent.putExtra("states",states);
                intent.putExtra("start",states.get(checked));
                startActivity(intent);

            }
        });
    }

}
