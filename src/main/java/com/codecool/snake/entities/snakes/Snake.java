package com.codecool.snake.entities.snakes;

import com.codecool.snake.*;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import com.codecool.snake.HealthBar;
import com.sun.javafx.geom.Vec2d;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

import java.util.List;


public class Snake implements Animatable {
    private int speed;
    private int blueShoot = 0;
    private int redShoot = 0;
    private int health = 100;
    private int score;
    private HealthBar healthBar;
    private Shooting shooting;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;

    private String name;
    private boolean fireBall = false;


    public Snake(Point2D position, String name) {
        this.name = name;
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();
        healthBar = new HealthBar(this);

        addPart(4);

        this.score = 0;
        this.speed = 2;
    }


    public String getName() {
        return this.name;
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

    public void setFireBall(boolean fireBall) {
        this.fireBall = fireBall;
    }

    public boolean getFireBall() {return this.fireBall;}

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

        switch (this.name) {
            case "Blue":
                if (blueShoot > 0) {
                    blueShoot --;
                }
                if (InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
                if (InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
                if (InputHandler.getInstance().isKeyPressed(KeyCode.UP)) {
                    System.out.println("TRAGE BAAAA");
                    if (blueShoot == 0) {
                        new Shooting(this);
                        blueShoot += 10;
                    }
                }
                break;
            case "Red":
                if (redShoot > 0) {
                    redShoot --;
                }
                if (InputHandler.getInstance().isKeyPressed(KeyCode.A)) turnDir = SnakeControl.TURN_LEFT;
                if (InputHandler.getInstance().isKeyPressed(KeyCode.D)) turnDir = SnakeControl.TURN_RIGHT;
                if (InputHandler.getInstance().isKeyPressed(KeyCode.W)) {
                    System.out.println("TRAGE BAAAA");
                    if (redShoot == 0) {
                        new Shooting(this);
                        redShoot += 10;
                    }
                }
                break;
        }

        return turnDir;

    }
    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Point2D position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position, this);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        healthBar.changeHealth(diff);
    }

    public void changeScore(int diff) {
        score += diff;
        switch (this.name) {
            case "Blue":
                GameInfoBox.changeTitleSnake1("BLUE DRAGON: " + this.getScore());
                break;
            case "Red":
                GameInfoBox.changeTitleSnake2("RED DRAGON: " + this.getScore());
                break;
        }
    }


    public void checkForGameOverConditions() {
        if (head.isOutOfBounds() || healthBar.getHealth() <= 0) {
            destroySnake();
        }
    }

    public void destroySnake() {
        Globals.getInstance().game.getChildren().remove(this.getHead());
        List<GameEntity> bodyList = getBody().getList();
        for (GameEntity bodyPart : bodyList) {
            Globals.getInstance().game.getChildren().remove(bodyPart);
        }

        switch (this.name) {
            case "Blue":
                Globals.getInstance().game.redSnakeDead = true;
                break;
            case "Red":
                Globals.getInstance().game.blueSnakeDead = true;
        }

        setHealth(0);
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


    public void setHealth(int health) {
        this.health = health;
        healthBar.setHealth(health);
    }




}
