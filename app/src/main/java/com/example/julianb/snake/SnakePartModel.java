package com.example.julianb.snake;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

/**
 * Created by Viro on 02.02.2018.
 */

public class SnakePartModel {
    private int posX = 0; // aktuelle Koordinaten
    private int posY = 0;

    private int oldX = 0; // alte Koordinaten
    private int oldY = 0;

    private ShapeDrawable shape;

    SnakePartModel() {
        posX = 1;
        posY = 1;

        shape = new ShapeDrawable(new RectShape());
    }

    SnakePartModel(int x, int y) {
        posX = x;
        posY = y;

        shape = new ShapeDrawable(new RectShape());
    }

    public void setNewPosition(int x, int y) {
        oldX = posX;
        oldY = posY;
        posX = x;
        posY = y;

        //System.out.println("OldX:" + oldX + ", OldY:" + oldY + "newX:" + posX + ", newY:" + posY);
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public ShapeDrawable getShape() {
        return shape;
    }
}
