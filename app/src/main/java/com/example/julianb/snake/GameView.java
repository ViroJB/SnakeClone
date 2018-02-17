package com.example.julianb.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by Viro on 01.02.2018.
 */

public class GameView extends View implements View.OnTouchListener {

    private int width;
    private int height;
    private int fieldsWidth = 30;
    private int fieldsHeight = 30;
    private int fieldSize = 0;
    private int borderSize = 0;

    private int eatablePosX = 0;
    private int eatablePosY = 0;

    int[][] buttonCoordinates;
    private ShapeDrawable eatable = new ShapeDrawable(new RectShape());

    private SnakeModel snake;
    private Paint paint;
    private Random rnd;

    private boolean isInitialized = false;

    /*
    TODO redo calculations for drawing ( width/100 and float or smth instead of int )
     */
    public GameView(Context context) {
        super(context);

        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(this);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInitialized) {
            init();
            isInitialized = true;
        }

        canvas.drawColor(Color.GRAY);

        drawBorder(canvas, width, height);
        drawButtons(canvas);
        drawSnake(canvas);
        drawEatable(canvas);

        invalidate();
    }

    public void drawEatable(Canvas canvas) {
        int[] coordinates = makePixelCoordinate(eatablePosX, eatablePosY);
        this.eatable.getPaint().setColor(Color.DKGRAY);
        this.eatable.setBounds(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
        this.eatable.draw(canvas);
    }

    private void init() {
        this.width = getWidth();
        this.height = this.width;

        rnd = new Random();

        eatablePosX = rnd.nextInt(fieldsWidth);
        eatablePosY = rnd.nextInt(fieldsHeight);

        this.buttonCoordinates = new int[4][4];

        this.buttonCoordinates[0][0] = borderSize+(width/100*13); // left
        this.buttonCoordinates[0][1] = width+(width/50); // top
        this.buttonCoordinates[0][2] = this.buttonCoordinates[0][0]+(width/4); // right
        this.buttonCoordinates[0][3] = width+(width/6); // bottom

        this.buttonCoordinates[1][0] = this.buttonCoordinates[0][0];
        this.buttonCoordinates[1][1] = (this.buttonCoordinates[0][1] + (width/6));
        this.buttonCoordinates[1][2] = this.buttonCoordinates[0][2];
        this.buttonCoordinates[1][3] = (this.buttonCoordinates[0][3] + (width/6));

        this.buttonCoordinates[2][0] = (this.buttonCoordinates[0][0] + (width/2));
        this.buttonCoordinates[2][1] = this.buttonCoordinates[0][1];
        this.buttonCoordinates[2][2] = (this.buttonCoordinates[0][2] + (width/2));
        this.buttonCoordinates[2][3] = this.buttonCoordinates[0][3];

        this.buttonCoordinates[3][0] = (this.buttonCoordinates[0][0] + (width/2));
        this.buttonCoordinates[3][1] = (this.buttonCoordinates[0][1] + (width/6));
        this.buttonCoordinates[3][2] = (this.buttonCoordinates[0][2] + (width/2));
        this.buttonCoordinates[3][3] = (this.buttonCoordinates[0][3] + (width/6));

        this.borderSize = this.width/100;
        this.fieldSize = (this.width-(this.borderSize*2))/this.fieldsWidth;

        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setColor(Color.DKGRAY);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(this.borderSize);
    }

    public void drawSnake(Canvas canvas) {
        int[] coordinates;

        for (SnakePartModel snakePart : snake.getSnakeParts()) {
            coordinates = makePixelCoordinate(snakePart.getPosX(), snakePart.getPosY());

            snakePart.getShape().getPaint().setColor(Color.DKGRAY);
            snakePart.getShape().setBounds(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
            snakePart.getShape().draw(canvas);
        }
    }

    public void drawButtons(Canvas canvas) {

        this.paint.setColor(Color.CYAN);
        this.paint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < 4; i++) {
            canvas.drawRect(
                    this.buttonCoordinates[i][0],
                    this.buttonCoordinates[i][1],
                    this.buttonCoordinates[i][2],
                    this.buttonCoordinates[i][3],
                    this.paint
            );
        }
    }

    private int[] makePixelCoordinate(int x, int y) {
        int[] coordinates = new int[4];

        coordinates[0] = this.borderSize+((x-1)*this.fieldSize);
        coordinates[1] = this.borderSize+((y-1)*this.fieldSize);
        coordinates[2] = this.borderSize+((x-1)*this.fieldSize)+this.fieldSize;
        coordinates[3] = this.borderSize+((y-1)*this.fieldSize)+this.fieldSize;

        return coordinates;
    }

    public void drawBorder(Canvas canvas, int width, int height) {
        this.paint.setColor(Color.DKGRAY);
        this.paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, width, height, this.paint);
    }

    public void setSnake(SnakeModel snake) {
        this.snake = snake;
    }

    public void checkTouchPosition(int x, int y) {
        if(x >= this.buttonCoordinates[0][0] && x <= this.buttonCoordinates[0][2]
                && y >= this.buttonCoordinates[0][1] && y <= this.buttonCoordinates[0][3]) {
            if(this.snake.getDirection() != SnakeModel.SNAKE_UP && this.snake.getDirection() != SnakeModel.SNAKE_DOWN) {
                this.snake.setDirection(SnakeModel.SNAKE_UP);
            }
            System.out.println("button 1 pressed.");
        }
        if(x >= this.buttonCoordinates[1][0] && x <= this.buttonCoordinates[1][2]
                && y >= this.buttonCoordinates[1][1] && y <= this.buttonCoordinates[1][3]) {
            if(this.snake.getDirection() != SnakeModel.SNAKE_DOWN && this.snake.getDirection() != SnakeModel.SNAKE_UP) {
                this.snake.setDirection(SnakeModel.SNAKE_DOWN);
            }
            System.out.println("button 2 pressed.");
        }
        if(x >= this.buttonCoordinates[2][0] && x <= this.buttonCoordinates[2][2]
                && y >= this.buttonCoordinates[2][1] && y <= this.buttonCoordinates[2][3]) {
            if(this.snake.getDirection() != SnakeModel.SNAKE_LEFT && this.snake.getDirection() != SnakeModel.SNAKE_RIGHT) {
                this.snake.setDirection(SnakeModel.SNAKE_LEFT);
            }
            System.out.println("button 3 pressed.");
        }
        if(x >= this.buttonCoordinates[3][0] && x <= this.buttonCoordinates[3][2]
                && y >= this.buttonCoordinates[3][1] && y <= this.buttonCoordinates[3][3]) {
            if(this.snake.getDirection() != SnakeModel.SNAKE_RIGHT && this.snake.getDirection() != SnakeModel.SNAKE_LEFT) {
                this.snake.setDirection(SnakeModel.SNAKE_RIGHT);
            }
            System.out.println("button 4 pressed.");
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                checkTouchPosition(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    public int getFieldsWidth() {
        return fieldsWidth;
    }

    public void setFieldsWidth(int fieldsWidth) {
        this.fieldsWidth = fieldsWidth;
    }

    public int getFieldsHeight() {
        return fieldsHeight;
    }

    public void setFieldsHeight(int fieldsHeight) {
        this.fieldsHeight = fieldsHeight;
    }

    public int getEatablePosX() {
        return eatablePosX;
    }

    public void setEatablePosX(int eatablePosX) {
        this.eatablePosX = eatablePosX;
    }

    public int getEatablePosY() {
        return eatablePosY;
    }

    public void setEatablePosY(int eatablePosY) {
        this.eatablePosY = eatablePosY;
    }

    public Random getRnd() {
        return rnd;
    }
}
