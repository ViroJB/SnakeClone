package com.example.julianb.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Viro on 25.03.2017.
 */

public class Controller extends View {

    Paint paint = new Paint();

    private ArrayList<SnakeParts> parts = new ArrayList<>();

    private int width = 0;
    private int height = 0;

    private int oldX = 0; // alte x- und y-werte eines snakeparts, zum übergeben an die hintere snakepart
    private int oldY = 0;

    private boolean isInitialized = false;


    public Controller(Context context) {
        super(context);

        setFocusable(true);
        setFocusableInTouchMode(true);


        snakeHead();

        addSnakePart(0);
        addSnakePart(0);
        addSnakePart(0);
        addSnakePart(0);
        addSnakePart(0);
        addSnakePart(0);
        addSnakePart(0);
        addSnakePart(2);
        addSnakePart(2);
        addSnakePart(2);
        addSnakePart(2);
        addSnakePart(1);
        addSnakePart(1);
        addSnakePart(2);
        addSnakePart(2);
        addSnakePart(2);
        addSnakePart(0);
        addSnakePart(0);
        addSnakePart(0);
        addSnakePart(3);
    }



    private void init() {

        paint.setAntiAlias(true);
        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);


        // getWidth() in die init, um sicher zu stellen, dass die Canvas bereits existiert und es ein width-wert gibt
        width = getWidth();
        height = width;
    }



    // rumspielen, wie man die schlange bewegt...
    // Problem: die schlange wird beim ersten mal gezeichnet, zeichnet sich aber nicht neu bei neuen Koordinaten
    //
    private void moveSnake() {
        oldX = parts.get(0).getPosX();
        oldY = parts.get(0).getPosY();
        parts.get(0).setNewPosition(parts.get(0).getPosX()+32, parts.get(0).getPosY()); // zuerst dem Kopf eine neue position geben
        for(int i = 1; i < parts.size()-1; i++) { // dann jedem teil des Schwanzes

            parts.get(i).setNewPosition(oldX, oldY);
            oldX = parts.get(i).getOldX();
            oldY = parts.get(i).getOldY();
        }
    }



    public void snakeHead() {
        parts.add(new SnakeParts(20,20));
    }



    public void addSnakePart(int direction) {

        // runter
        if(direction == 0)
            parts.add(new SnakeParts(parts.get(parts.size()-1).getPosX(), parts.get(parts.size()-1).getPosY()+32));

        // hoch
        else if(direction == 1)
            parts.add(new SnakeParts(parts.get(parts.size()-1).getPosX(), parts.get(parts.size()-1).getPosY()-32));

        // rechts
        else if(direction == 2)
            parts.add(new SnakeParts(parts.get(parts.size()-1).getPosX()+32, parts.get(parts.size()-1).getPosY()));

        // links
        else if(direction == 3)
            parts.add(new SnakeParts(parts.get(parts.size()-1).getPosX()-32, parts.get(parts.size()-1).getPosY()));

    }



    public ArrayList<SnakeParts> getSnakeParts() {
        return parts;
    }



    public void drawSnake(Canvas canvas) {
        for(int i = 0; i < parts.size(); ++i) {
            parts.get(i).shape.draw(canvas);

        }
    }



    public void drawBorder(Canvas canvas, int width, int height, Paint paint) {
        canvas.drawRect(5,5,width-5,height-5, paint);
    }



    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInitialized) {

            init();
            isInitialized = true;
        }

        canvas.drawColor(Color.GRAY);
        drawBorder(canvas, width, height, paint);

        drawSnake(canvas);

        moveSnake();

        invalidate(); // postInvalidate(); funktioniert auch nicht...
    }

}