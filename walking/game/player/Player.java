package walking.game.player;
import java.util.*;
import walking.game.util.Direction;

public class Player {
    private int score;
    protected Direction direction = Direction.UP;
    public Player() {
    }

    public void addToScore(int score) {
        this.score += score;
    }

    public void turn() {
        direction = direction.next();
        //System.out.print("Changing direction to ");
        //System.out.println(direction);

    }

    public int getScore() {
        return score;
    }

    public Direction getDirection() {
        return direction;
    }
}