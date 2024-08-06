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

public class WalkingBoardTest{
    private WalkingBoard board;

    @ParameterizedTest
    @CsvSource({"3", "5", "7", "8"})
    public void testSimpleInit(int size){
        board = new WalkingBoard(size);       
        int[][] tiles = board.getTiles();

        
        for (int i = 0; i < size; i++) {
            assertEquals(tiles[i].length, size);
        }

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                assertEquals(tiles[i][j], WalkingBoard.BASE_TILE_SCORE);
            }
        }

        //edge checking
        assertEquals(board.getTile(0, 0), WalkingBoard.BASE_TILE_SCORE);
        assertEquals(board.getTile(size - 1, size - 1), WalkingBoard.BASE_TILE_SCORE);
    }   

    @ParameterizedTest
    @CsvSource({
        "0, 0, 3",
        "1, 1, 5",
        "2, 2, 9"

    })
    public void testCustomInit(int x, int y, int expected) {
        int[][] givenValues = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        board = new WalkingBoard(givenValues);
        int[][] tiles = board.getTiles();
        
        // at least BASE_TILE_SCORE
        if (givenValues[x][y] < 3) {
            assertEquals(WalkingBoard.BASE_TILE_SCORE, tiles[x][y]);
        }
        
        
        givenValues[x][y] = expected+1;
        assertEquals(expected, board.getTile(x, y));


        //modify element of getTiles but get the value of the respective tile again, 
        //this newly received content has to be the originally set value.
        tiles[x][y] = expected+1;
        assertEquals(expected, board.getTile(x, y));
    }

    @Test
    public void TestMoves(){
        int[][] mTest = {
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 12}
        };
        board = new WalkingBoard(mTest);
        int [][] tiles = board.getTiles();
        for (int i = 0; i < mTest.length; i++) {
            assertArrayEquals(mTest[i], tiles[i]);
        }
        assertArrayEquals(board.getPosition(), new int[]{0,0});
        assertEquals(5, board.moveAndSet(Direction.RIGHT, 50));
        assertEquals(6, board.moveAndSet(Direction.RIGHT, 60));
        //current [0][2]
        assertArrayEquals(board.getPosition(), new int[]{0,2});
        //move beyond
        assertEquals(0, board.moveAndSet(Direction.RIGHT, 1000));
        //check current position
        assertArrayEquals(board.getPosition(), new int[]{0,2});
        //check current tile
        assertEquals(board.getTile(0,2), 60);

        assertEquals(9, board.moveAndSet(Direction.DOWN, 90));
        assertEquals(8, board.moveAndSet(Direction.LEFT, 80));
        //current position middle
        assertArrayEquals(board.getPosition(), new int[]{1,1});

        int[][] finalTiles = board.getTiles();
        int[][] expectedFinalTiles = {
            {4, 50, 60},
            {7, 80, 90},
            {10, 11, 12}
        };

        for (int i = 0; i < expectedFinalTiles.length; i++) {
            assertArrayEquals(expectedFinalTiles[i], finalTiles[i]);
        }
        

        


    
    }
    
}