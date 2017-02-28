package com.neogineer.automataconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class EndStatesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_states);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);

        final LinearLayout ll = (LinearLayout) findViewById(R.id.endStatesList);

        final ArrayList<String> states = getIntent().getStringArrayListExtra("states");
        final String start = getIntent().getStringExtra("start");

        for(String state : states){
            CheckBox checkBox = new CheckBox(this);
            checkBox.setLayoutParams(params);
            checkBox.setText(state);
            checkBox.setId(states.indexOf(state));
            checkBox.setTextSize(20);
            ll.addView(checkBox);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> ends = new ArrayList<String>();

                for(int i=0; i<ll.getChildCount(); i++){
                    CheckBox checkBox = (CheckBox) ll.getChildAt(i);
                    if(!checkBox.isChecked())
                        continue;
                    ends.add(checkBox.getText().toString());
                }

                if(ends.size()<=0) {
                    Snackbar.make(view, "At least one end state", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                Intent intent = new Intent(EndStatesActivity.this, SymbolsActivity.class);
                intent.putExtra("states", states);
                intent.putExtra("start", start);
                intent.putExtra("ends", ends);
                startActivity(intent);

            }
        });
    }

}
