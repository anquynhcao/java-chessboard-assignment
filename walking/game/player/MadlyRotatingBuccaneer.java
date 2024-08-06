package walking.game.player;
import java.util.*;
import walking.game.util.Direction;

public class MadlyRotatingBuccaneer extends Player {
    private int turnCount;

    public MadlyRotatingBuccaneer() {
        super();
        this.turnCount = 0;
    }

    @Override
    public void turn() {
        if (turnCount > 0) {
            int timesToTurn = turnCount % Direction.values().length;
            for (int i = 0; i < timesToTurn; i++) {
                super.turn();
            }
        }
        turnCount++;
    }
}