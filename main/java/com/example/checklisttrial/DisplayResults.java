package com.example.checklisttrial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);
        TextView text = findViewById(R.id.resultsText);
        text.setText(getCheckBoxString());
    }

    public String getCheckBoxString(){
        String allResults = "";
        String[] allResultsArray = getIntent().getStringArrayExtra("CHECKED_BOXES");


        for(String line : allResultsArray){
            if(allResults.length() > 0) allResults += "\n";
            allResults += line;
        }

        return allResults;
    }


}
