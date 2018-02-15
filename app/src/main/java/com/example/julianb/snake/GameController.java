package com.example.julianb.snake;

/**
 * Created by Viro on 02.02.2018.
 */

public class GameController implements Runnable {

    private SnakeModel snake;
    private GameView gameView;
    private int fieldsWidth;
    private boolean isRunning = false;

    GameController (GameView gameView) {
        this.fieldsWidth = gameView.getFieldsWidth();
        this.snake = new SnakeModel();
        this.gameView = gameView;
        this.addBody();
        this.isRunning = true;

        Thread thread = new Thread(this);
        thread.start();
    }

    // next: collision detection!

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveSnake();
        }
    }

    private void addBody() {
        for (int i = 0; i < 5; i++) {
            this.snake.addSnakePart(2);
        }
        for (int i = 0; i < 5; i++) {
            this.snake.addSnakePart(1);
        }
        for (int i = 0; i < 5; i++) {
            this.snake.addSnakePart(2);
        }
        for (int i = 0; i < 5; i++) {
            this.snake.addSnakePart(3);
        }
    }

    private void moveSnake () {
        switch(this.snake.getDirection()) {
            case SnakeModel.SNAKE_UP:
                if(this.snake.getSnakeParts().get(0).getPosY()-1 < 1) {
                    System.out.println("drueber!!!");
                    this.isRunning = false;
                    return;
                }

                this.snake.getSnakeParts().get(0).setNewPosition(
                        this.snake.getSnakeParts().get(0).getPosX(),
                        this.snake.getSnakeParts().get(0).getPosY()-1
                );
                break;
            case SnakeModel.SNAKE_RIGHT:
                if(this.snake.getSnakeParts().get(0).getPosX()+1 > this.fieldsWidth) {
                    System.out.println("drueber!!!");
                    this.isRunning = false;
                    return;
                }

                this.snake.getSnakeParts().get(0).setNewPosition(
                        this.snake.getSnakeParts().get(0).getPosX()+1,
                        this.snake.getSnakeParts().get(0).getPosY()
                );
                break;
            case SnakeModel.SNAKE_DOWN:
                if(this.snake.getSnakeParts().get(0).getPosY()+1 > this.fieldsWidth) {
                    System.out.println("drueber!!!");
                    this.isRunning = false;
                    return;
                }

                this.snake.getSnakeParts().get(0).setNewPosition(
                        this.snake.getSnakeParts().get(0).getPosX(),
                        this.snake.getSnakeParts().get(0).getPosY()+1
                );
                break;
            case SnakeModel.SNAKE_LEFT:
                if(this.snake.getSnakeParts().get(0).getPosX()-1 < 1) {
                    System.out.println("drueber!!!");
                    this.isRunning = false;
                    return;
                }

                this.snake.getSnakeParts().get(0).setNewPosition(
                        this.snake.getSnakeParts().get(0).getPosX()-1,
                        this.snake.getSnakeParts().get(0).getPosY()
                );
                break;
        }

        for (int i = 1; i < this.snake.getSnakeParts().size(); i++) {
            this.snake.getSnakeParts().get(i).setNewPosition(
                    this.snake.getSnakeParts().get(i-1).getOldX(),
                    this.snake.getSnakeParts().get(i-1).getOldY()
            );
        }

        // TODO eatable stuff, refactor
        if(this.snake.getSnakeParts().get(0).getPosX() == this.gameView.getEatablePosX()
                && this.snake.getSnakeParts().get(0).getPosY() == this.gameView.getEatablePosY()) {

            this.snake.addSnakePart(SnakeModel.SNAKE_ADD);
            this.gameView.setEatablePosX(this.gameView.getRnd().nextInt(this.gameView.getFieldsWidth()));
            this.gameView.setEatablePosY(this.gameView.getRnd().nextInt(this.gameView.getFieldsHeight()));
        }
    }

    public SnakeModel getSnake() {
        return snake;
    }
}
