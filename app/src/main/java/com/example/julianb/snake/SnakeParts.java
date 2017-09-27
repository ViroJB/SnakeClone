package com.example.julianb.snake;


import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;


/**
 * Created by julianb on 23.03.17.
 */

public class SnakeParts  {

    private int posX = 0; // aktuelle Koordinaten
    private int posY = 0;
    private int oldX = 0; // alte Koordinaten
    private int oldY = 0;


    public ShapeDrawable shape;


    public SnakeParts (int x, int y) {
        posX = x;
        posY = y;
        shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setColor(Color.DKGRAY);
        shape.setBounds(posX, posY, posX+30, posY+30);
    }



    public void setNewPosition(int x, int y) {
        oldX = posX;
        oldY = posY;
        posX = x;
        posY = y;
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

}