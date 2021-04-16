package com.example.quizbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Activity2 extends AppCompatActivity {

    ArrayList questionList = new ArrayList<>();

    String[] splitStrings;
    ArrayList correctAnswer = new ArrayList<>();

    ArrayList possibleAnswers = new ArrayList<>();

    HashMap<String, String> map = new HashMap<String, String>();

    Button buttonA, buttonB, buttonC, buttonD;
    TextView userDisplay;
    TextView questionView;

    InputStream inStream;
    BufferedReader fileReader;

    int score;
    int questionNum;
    int counter;

    String line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        score = 0;
        questionNum = 0;
        counter = 0;

        try {
            inStream = this.getResources().openRawResource(R.raw.quiz);
            fileReader = new BufferedReader(new InputStreamReader(inStream));

            //While loop to read through the file and appropriately place questions and answers respectively.
            while((line = fileReader.readLine())!=null){
                splitStrings = line.split(":");
                questionList.add(splitStrings[0]);
                possibleAnswers.add(splitStrings[1]);
                map.put(splitStrings[0],splitStrings[1]);
            }
            inStream.close();
        }

        catch (IOException e){
            Log.e("IOException", e.getMessage());
        } catch (Exception e){
            Log.e("Error", e.getMessage());
        }

        userDisplay = findViewById(R.id.textView);
        questionView = findViewById(R.id.qView);

        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);

        //Receiving the intent from Main Activity
        Intent intent = getIntent();
        String userInput = intent.getStringExtra("userInput");
        userDisplay.setText(userInput);


        //Loading the questions before user interface can begin
        loadQuestion();

        //If selected button's text matches the appropriate answer in the list, toast a correct alert, increment the score, then run the checkFinish() method
        //to see if question count has been met
        //else checkFinish() to see if finished, then display wrong toast.
        buttonA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(buttonA.getText() == map.get(questionList.get(questionNum))){
                    Toast.makeText(Activity2.this, "Nailed it!", Toast.LENGTH_SHORT).show();
                    score += 1;
                    checkFinish();
                }
                else{
                    checkFinish();
                    Toast.makeText(Activity2.this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(buttonB.getText() == map.get(questionList.get(questionNum))){
                    Toast.makeText(Activity2.this, "Nailed it!", Toast.LENGTH_SHORT).show();
                    score += 1;
                    checkFinish();
                }
                else{
                    checkFinish();
                    Toast.makeText(Activity2.this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(buttonC.getText() == map.get(questionList.get(questionNum))){
                    Toast.makeText(Activity2.this, "Nailed it!", Toast.LENGTH_SHORT).show();
                    score += 1;
                    checkFinish();
                }
                else{
                    checkFinish();
                    Toast.makeText(Activity2.this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(buttonD.getText() == map.get(questionList.get(questionNum))){
                    Toast.makeText(Activity2.this, "Nailed it!", Toast.LENGTH_SHORT).show();
                    score += 1;
                    checkFinish();
                }
                else{
                    checkFinish();
                    Toast.makeText(Activity2.this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void loadQuestion() {
        //Clearing the correctAnswer list
        correctAnswer.clear();

        //Putting the correct answer in the list
        correctAnswer.add(map.get(questionList.get(questionNum)));

        //Setting the next question to the view
        questionView.setText((CharSequence) questionList.get(questionNum));

        //Removing the answer from questions left, then shuffling and adding other options
        possibleAnswers.remove(map.get(questionList.get(questionNum)));
        Collections.shuffle(possibleAnswers);
        correctAnswer.add(possibleAnswers.get(0));
        correctAnswer.add(possibleAnswers.get(1));
        correctAnswer.add(possibleAnswers.get(2));

        //Shuffling the new list with the correct answer
        Collections.shuffle(correctAnswer);

        //Loading the buttons
        buttonA.setText((CharSequence) correctAnswer.get(0));
        buttonB.setText((CharSequence) correctAnswer.get(1));
        buttonC.setText((CharSequence) correctAnswer.get(2));
        buttonD.setText((CharSequence) correctAnswer.get(3));

        //Incrementing the question count
        this.counter += 1;
    }

    private void checkFinish(){
        if(counter == 10)
        {
            String lastScore = Integer.toString(score);
//            Intent scoreIntent = new Intent("Activity3");

            Intent scoreIntent = new Intent(this, Activity3.class);
            Bundle results = new Bundle();
            results.putString("Score", lastScore);
            scoreIntent.putExtras(results);
            startActivityForResult(scoreIntent, 1);
        }
        else{
            this.questionNum += 1;
            loadQuestion();
        }
    }

}