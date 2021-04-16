package com.example.quizbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    TextView score;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        score = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        //Grabbing the intent from previous page to display the user's score.
        Intent intent = getIntent();
        String scoreIntent = intent.getStringExtra("Score");

        score.setText(scoreIntent);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //return back to the start of the application.
                finish();
            }
        });
    }
}