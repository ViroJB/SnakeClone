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

    private ArrayList<SnakePart> snakeParts = new ArrayList<>();

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
    private void moveSnake() {
        oldX = snakeParts.get(0).getPosX();
        oldY = snakeParts.get(0).getPosY();
        snakeParts.get(0).setNewPosition(snakeParts.get(0).getPosX()+32, snakeParts.get(0).getPosY()); // zuerst dem Kopf eine neue position geben
        for(int i = 1; i < snakeParts.size()-1; i++) { // dann jedem teil des Schwanzes

            snakeParts.get(i).setNewPosition(oldX, oldY);
            oldX = snakeParts.get(i).getOldX();
            oldY = snakeParts.get(i).getOldY();
        }
    }

    public void snakeHead() {
        snakeParts.add(new SnakePart(20,20));
    }

    public void addSnakePart(int direction) {

        // runter
        if(direction == 0)
            snakeParts.add(new SnakePart(snakeParts.get(snakeParts.size()-1).getPosX(), snakeParts.get(snakeParts.size()-1).getPosY()+32));
        // hoch
        else if(direction == 1)
            snakeParts.add(new SnakePart(snakeParts.get(snakeParts.size()-1).getPosX(), snakeParts.get(snakeParts.size()-1).getPosY()-32));
        // rechts
        else if(direction == 2)
            snakeParts.add(new SnakePart(snakeParts.get(snakeParts.size()-1).getPosX()+32, snakeParts.get(snakeParts.size()-1).getPosY()));
        // links
        else if(direction == 3)
            snakeParts.add(new SnakePart(snakeParts.get(snakeParts.size()-1).getPosX()-32, snakeParts.get(snakeParts.size()-1).getPosY()));
    }

    public ArrayList<SnakePart> getSnakeParts() {
        return snakeParts;
    }

    public void drawSnake(Canvas canvas) {
        for(int i = 0; i < snakeParts.size(); ++i) {
            snakeParts.get(i).shape.draw(canvas);
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

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        invalidate(); // postInvalidate(); funktioniert auch nicht...
    }

}