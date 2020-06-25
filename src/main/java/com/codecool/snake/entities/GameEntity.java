package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected GameEntity() {
        Globals.getInstance().display.add(this);
    }

    public void destroy() {
        Globals.getInstance().display.remove(this);
    }

    public Point2D getPosition() {
        return new Point2D(getX(), getY());
    }

    public void setPosition(Point2D pos) {
        setX(pos.getX());
        setY(pos.getY());
    }

    public boolean isOutOfBounds() {
        if (getX() > Globals.WINDOW_WIDTH || getX() < 0 ||
            getY() > Globals.WINDOW_HEIGHT || getY() < 0) {
            return true;
        }
        return false;
    }

    public void spawn() {
        Random rnd = new Random();
        setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH - 20));
        setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT - 20));
    }

//    Overloading the default function to achieve creating a game entity with a limited life time
    public void spawn(int spawnDuration) {
        spawn();
        AtomicInteger spawnedTimer = new AtomicInteger();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            spawnedTimer.addAndGet(1);
            if (spawnedTimer.intValue() == spawnDuration) {
                this.destroy();
            }
        }));
        timeline.setCycleCount(spawnDuration);
        timeline.play();
    }
}
