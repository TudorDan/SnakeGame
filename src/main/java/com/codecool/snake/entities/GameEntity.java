package com.codecool.snake.entities;

import com.codecool.snake.Globals;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.util.Random;

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
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }
}
