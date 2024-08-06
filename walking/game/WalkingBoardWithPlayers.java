package walking.game;
import java.util.*;
import walking.game.util.Direction;
import walking.game.player.*;
public class WalkingBoardWithPlayers extends WalkingBoard {
    private Player[] players;
    private int round;
    public static final int SCORE_EACH_STEP=13;
    public WalkingBoardWithPlayers(int [][] board, int playerCount){
        super(board);
        initPlayers(playerCount);
    }

    public WalkingBoardWithPlayers(int size, int playerCount) {
        super(size);
        initPlayers(playerCount);
    }


    private void initPlayers(int playerCount){
        if (playerCount < 2) {
            throw new IllegalArgumentException("Number of players must be at least two");
        }
        players = new Player[playerCount];
        players[0] = new MadlyRotatingBuccaneer();
        for (int i = 1; i < playerCount; i++) {
            players[i] = new Player();
        }
    }

    public int[] walk(int... stepCounts) { // 3, 2, 4 // SCORE_EACH_STEP = 2
        
        
        int stepsTaken = 0;
        for (int i = 0; i < stepCounts.length; i++) {
            Player currentPlayer = players[i % players.length];
            currentPlayer.turn();
            Direction currentDirection = currentPlayer.getDirection();
            
            
            for (int j = 0; j < stepCounts[i]; j++) {
                stepsTaken++;
                moveAndSet(currentDirection, Math.min(stepsTaken, SCORE_EACH_STEP));
                currentPlayer.addToScore(getTile(super.getPosition()[0], super.getPosition()[1]));
                //System.out.println("player " + i + ":" + currentPlayer.getScore() + "point ");
                

            }
            
           
        }
        int[] scores = new int[players.length];
        for (int i = 0; i < players.length; i++){
            scores[i] = players[i].getScore();
            //System.out.println("players " + i + " has " + scores[i] + " ");
        }
        return scores;
    }

    



}