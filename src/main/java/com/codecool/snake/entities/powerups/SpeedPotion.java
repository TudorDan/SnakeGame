package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

public class SpeedPotion extends GameEntity implements Interactable {


    public SpeedPotion(int spawnDuration) {
        super();
        setImage(Globals.getInstance().getImage("Potion"));
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
        return "Speed increased!";
    }
}
