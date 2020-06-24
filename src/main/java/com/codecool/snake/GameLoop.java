package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class GameLoop {
    private Snake snake;
    private Enemy enemy;
    private boolean running = false;

    public GameLoop(Snake snake) {
        this.snake = snake;
    }

    public List<Point2D> SnakePos() {
        Point2D head = snake.getHead().getPosition();
        Point2D body = (Point2D) snake.getBody().getList();
        List<Point2D> headBody = new ArrayList<>();
        headBody.add(head);
        headBody.add(body);

        return headBody;
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
//        System.out.println("CEVA!!!!!!!!!!!!!!");
////        enemy.clearAll();
//        Globals.getInstance().display.clear();
//        System.out.println("CEVA!!!!!!!!!!!!!!22222222222222");
    }

    public void step() {
        if (running) {
            snake.step();
            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
            }
            checkCollisions();
        }

        Globals.getInstance().display.frameFinished();
    }

    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable) {
                        if (objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())) {
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }
}
