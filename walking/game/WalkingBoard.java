package walking.game;
import java.util.*;
import walking.game.util.Direction;


public class WalkingBoard {

    private int[][] tiles;
    private int x;
    private int y;
    public static final int BASE_TILE_SCORE = 3;
    

    public WalkingBoard(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        this.tiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = BASE_TILE_SCORE;
            }
        }
    }
    
    public WalkingBoard(int[][] givenVal){
        this.tiles = new int[givenVal.length][];
        for (int i = 0; i < givenVal.length; i++){
            this.tiles[i] = Arrays.copyOf(givenVal[i], givenVal[i].length);
            for (int j = 0; j <this.tiles[i].length; j++){
                this.tiles[i][j] = Math.max(BASE_TILE_SCORE, this.tiles[i][j]);
            }
        }
    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public boolean isValidPosition(int x, int y){
        return x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length;
    }

    public int getTile(int x, int y){
        if (!isValidPosition(x, y)) {
            throw new IllegalArgumentException("Invalid position");
        }
        return tiles[x][y];
    }

    public int[][] getTiles(){
        int[][]copyTiles = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++) {
            copyTiles[i] = Arrays.copyOf(tiles[i], tiles[i].length);
        }
        return copyTiles;
        
    }

    public static int getYStep(Direction direction) {
        switch (direction) {
            case LEFT:
                return -1;
            case RIGHT:
                return 1;
            default:
                return 0;
        }
    }

    public static int getXStep(Direction direction){
        switch (direction){
            case UP:
                return -1;
            case DOWN:
                return 1;
            default:
                return 0;
        }
    }

    public int moveAndSet(Direction direction, int val){
        int newX = x + getXStep(direction);
        int newY = y + getYStep(direction);
        if (!isValidPosition(newX, newY)) {
            return 0; // Cancel the move
        }
        int oldVal = tiles[newX][newY];
        tiles[newX][newY] = val;
        x = newX;
        y = newY;
        return oldVal;

    }




}