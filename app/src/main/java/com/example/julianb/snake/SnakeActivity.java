package com.example.julianb.snake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SnakeActivity extends AppCompatActivity {

    GameController gameController;
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.gameView = new GameView(this);
        setContentView(this.gameView);

        this.gameController = new GameController(this.gameView);
        this.gameView.setSnake(gameController.getSnake());
    }
}