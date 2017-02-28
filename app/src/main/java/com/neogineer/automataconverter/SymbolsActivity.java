package com.neogineer.automataconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class SymbolsActivity extends AppCompatActivity {

    int count = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symbols);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);


        final LinearLayout ll = (LinearLayout) findViewById(R.id.symbolsFields);

        Button more = (Button) findViewById(R.id.moreButton);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit = new EditText(SymbolsActivity.this);
                edit.setLayoutParams(params);
                edit.setHint("symbol name");
                edit.setId(++count);
                ll.addView(edit);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> symbols = new ArrayList<>();

                for(int i=0; i<ll.getChildCount(); i++){
                    String text = String.valueOf(((EditText)ll.getChildAt(i)).getText());
                    if(text.length()<=0)
                        continue;
                    symbols.add(text);
                }

                if(symbols.size()<=0){
                    Snackbar.make(view, "Define symbol(s) name(s)", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                symbols.add("epsilon");

                Intent intent = new Intent(SymbolsActivity.this, TransitionsActivity.class);
                intent.putExtra("states", getIntent().getStringArrayListExtra("states"));
                intent.putExtra("start", getIntent().getStringExtra("start"));
                intent.putExtra("ends", getIntent().getStringArrayListExtra("ends"));
                intent.putExtra("symbols", symbols);
                startActivity(intent);
            }
        });
    }

}
