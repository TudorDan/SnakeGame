package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

public class GoldChest extends GameEntity implements Interactable {

    public GoldChest(int spawnDuration) {
        setImage(Globals.getInstance().getImage("GoldChest"));
        spawn(spawnDuration);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "+100 points for founding the Treasure";
    }
}
