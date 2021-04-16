package com.example.quizbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.buttonD);
        userName = findViewById(R.id.userName);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Validation to ensure there are no empty fields.
                if (TextUtils.isEmpty(userName.getText().toString())){
                    Toast.makeText(MainActivity.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                }
                else{
                    openActivity2();
                }
            }
        });
    }

    public void openActivity2() {
        //Setting string variable that will be passed to second activity via an intent.
        String userInput = userName.getText().toString();

        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("userInput", userInput);
        startActivity(intent);
    }
}