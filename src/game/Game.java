package game;

import board.Board;

public interface Game<K,V> {
    void init();
    boolean canMove();
    boolean  move(Direction direction);
    void addItem();
    Board<K,V> getGameBoard();
    boolean hasWin();
}
