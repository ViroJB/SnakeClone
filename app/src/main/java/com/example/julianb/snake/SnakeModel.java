package com.example.julianb.snake;

import java.util.ArrayList;

/**
 * Created by Viro on 02.02.2018.
 */

public class SnakeModel {

    private int direction = 1;

    public static final int SNAKE_UP = 0;
    public static final int SNAKE_RIGHT = 1;
    public static final int SNAKE_DOWN = 2;
    public static final int SNAKE_LEFT = 3;

    private ArrayList<SnakePartModel> snakeParts;

    SnakeModel () {
        this.snakeParts = new ArrayList<>();
        snakeHead();
    }

    public ArrayList<SnakePartModel> getSnakeParts () {
        return this.snakeParts;
    }

    private void snakeHead() {
        this.snakeParts.add(new SnakePartModel());
    }

    public void addSnakePart(int direction) {
        switch(direction) {
            case SNAKE_UP: // hoch
                    this.snakeParts.add(new SnakePartModel(
                            snakeParts.get(snakeParts.size()-1).getPosX(),
                            snakeParts.get(snakeParts.size()-1).getPosY()-1)
                    );
                    break;
            case SNAKE_RIGHT: // rechts
                    this.snakeParts.add(new SnakePartModel(
                            snakeParts.get(snakeParts.size()-1).getPosX()+1,
                            snakeParts.get(snakeParts.size()-1).getPosY()
                    ));
                    break;
            case SNAKE_DOWN: // unten
                    this.snakeParts.add(new SnakePartModel(
                            snakeParts.get(snakeParts.size()-1).getPosX(),
                            snakeParts.get(snakeParts.size()-1).getPosY()+1
                    ));
                    break;
            case SNAKE_LEFT: // links
                    this.snakeParts.add(new SnakePartModel(
                            snakeParts.get(snakeParts.size()-1).getPosX()-1,
                            snakeParts.get(snakeParts.size()-1).getPosY()
                    ));
                    break;
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
