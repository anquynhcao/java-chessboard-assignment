package walking.game.util;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public Direction next(){
        return values()[(ordinal() + 1) % values().length];
    }
}