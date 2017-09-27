package com.example.julianb.snake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SnakeActivity extends AppCompatActivity {

    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        controller = new Controller(this);

        setContentView(controller);
    }
}