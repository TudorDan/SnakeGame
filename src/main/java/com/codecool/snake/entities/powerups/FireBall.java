package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

public class FireBall extends GameEntity implements Interactable {

    public FireBall(int spawnDuration) {
        setImage(Globals.getInstance().getImage("FireBall"));
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
        return "You're shooting have just improved!";
    }
}
