package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;
import com.codecool.snake.HealthBar;

import com.codecool.snake.GameInfoBox;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;


public class Snake implements Animatable {
    private int speed;
    private int health = 100;
    private int score;
    private HealthBar healthBar;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;


    public Snake(Point2D position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();
        healthBar = new HealthBar();

        addPart(4);

        this.score = 0;
        this.speed = 2;
    }

    public boolean isTouchedBy(GameEntity gameEntity) {
        //check if game entity touches head
        if (head.getBoundsInParent().intersects(gameEntity.getBoundsInParent())) {
            return true;
        }
        //check if game entity touches body
        for (GameEntity bodyPart : body.getList()) {
            if (bodyPart.getBoundsInParent().intersects(gameEntity.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }

    public SnakeHead getHead() {
        return head;
    }

    public DelayedModificationList<GameEntity> getBody() {
        return body;
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);

        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Point2D position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        healthBar.changeHealth(diff);
    }

    public void changeScore(int diff) {
        score += diff;
        GameInfoBox.changeTitleSnake1("BLUE DRAGON: " + this.getScore());
    }


    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || healthBar.getHealth() <= 0) {
            System.out.println("outOfBounds: " + head.isOutOfBounds());
            System.out.println("health: " + healthBar.getHealth());

            System.out.println("Game Over");
            Globals.getInstance().stopGame();
            Globals.getInstance().showGameWonDialog(body.getList().size() + 1);
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for (GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if (result != null) return result;
        return head;
    }

    public int getScore() {
        return this.score;
    }

    public void restoreHealth() {
        healthBar.restoreHealth();
        this.health = 100;
    }

    public int getHealth() {
        return healthBar.getHealth();
    }

    public void changeSpeed(int diff) {
        this.speed += diff;
    }

    public int getSpeed() {
        return this.speed;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }
}
