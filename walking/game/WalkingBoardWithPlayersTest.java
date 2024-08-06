package walking.game;

import walking.game.util.Direction;
import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;
import java.util.*;

public class WalkingBoardWithPlayersTest{
    private WalkingBoardWithPlayers board1;
    private WalkingBoardWithPlayers board2;
    @Test
    public void walk1() {
        int[][] mTest1 = {
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 12}
        };
        board1 = new WalkingBoardWithPlayers(mTest1, 2);
        int[] steps = {3, 3, 4};
        int[] expectedScores = {32, 14};
        int[][] expectedboard = {
            {4, 4, 5},
            {7, 8, 9},
            {10, 11, 12}
        };
        int[] scores = board1.walk(steps);
        int[][] b1 = board1.getTiles();
        assertArrayEquals(b1, expectedboard);
        assertArrayEquals(expectedScores, scores);
    }
    @Test
    public void walk2(){
        int[][] mTest2 = {
            {4, 4, 4, 4},
            {4, 4, 4, 4},
            {4, 4, 4, 4},
            {4, 4, 4, 4}
        };
        board2 = new WalkingBoardWithPlayers(mTest2, 3);
        int [] steps = {3, 4, 3, 4, 3};
        int[] expectedScores = {36, 60, 18};
        int[] scores = board2.walk(steps);
        int[][] b2 = board2.getTiles();

        int[][] expectedboard = {
            {4, 4, 5, 6},
            {4, 4, 4, 13},
            {4, 4, 4, 13},
            {4, 4, 4, 13}
        };
        assertArrayEquals(b2, expectedboard);
        assertArrayEquals(expectedScores, scores);
        
    }
}