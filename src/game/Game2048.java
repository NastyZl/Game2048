package game;

import board.*;
import java.util.*;

import java.util.Collections;

public class Game2048 implements  Game<Key,Integer>{
    public static final int GAME_SIZE = 4;
    private Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);
    private final GameHelper helper = new GameHelper();
    private final Random random = new Random();

    public Game2048(Board<Key,Integer> board) {
        this.board = board;
    }

    public Game2048() {}

    @Override
    public void init() {
        board.clear();
        for (var i=0; i < 2; i++) {
            try {
                addItem();
            }
            catch (NotEnoughSpace notEnoughSpace) {
                System.out.println(notEnoughSpace.getMessage());
            }
        }
    }

    @Override
    public boolean canMove() {
        return !board.availableSpace().isEmpty();
    }

    @Override
    public boolean move(Direction direction) {
        boolean result = false;

        if (!canMove()) {
            return result;
        }
        switch (direction) {
            case RIGHT:
                for (int i = 0; i < board.getHeight(); i++) {
                    result = shiftRight(board, i);
                }
                break;
            case LEFT:
                for (var i = 0; i < board.getHeight(); i++) {
                    result = shiftLeft(board, i);
                }
                break;
            case UP:
                for (int j = 0; j < board.getWidth(); j++) {
                    result = shiftUp(board, j);
                }
                break;
            case DOWN:
                for (int j = 0; j < board.getWidth(); j++) {
                    result = shiftDown(board, j);
                }
                break;
        }
        if (result) {
            try {
                addItem();
            }
            catch (NotEnoughSpace notEnoughSpace) {
                System.out.println(notEnoughSpace.getMessage());
            }
        }
        return result;
    }

    public boolean shiftLeft(Board<Key, Integer> board, int i) {
        var newRow = helper.moveAndMergeEqual(board.getValueRow(i));
        for (int j = 0; j < newRow.size(); j++){
            board.addItem(board.getKey(i, j), newRow.get(j));
        }
        return true;
    }

    public boolean shiftRight(Board<Key, Integer> board, int i) {
        var keys = board.getRow(i);
        Collections.reverse(keys);
        var newRow = helper.moveAndMergeEqual(board.getValueRow(i));
        Collections.reverse(newRow);
        for (int j = 0; j < newRow.size(); j++) {
            board.addItem(board.getKey(i, j), newRow.get(j));
        }
        return true;
    }

    public boolean shiftDown(Board<Key, Integer> board, int j) {
        var keys = board.getColumn(j);
        Collections.reverse(keys);
        var newCol = helper.moveAndMergeEqual(board.getValues(keys));
        Collections.reverse(newCol);
        for (int i = newCol.size()-1; i > -1; i--) {
            board.addItem(board.getKey(i, j), newCol.get(i));
        }
        return true;
    }

    public boolean shiftUp(Board<Key, Integer> board, int j) {
        var newCol = helper.moveAndMergeEqual(board.getValueColumn(j));
        for (int i = 0; i < newCol.size(); i++){
            board.addItem(board.getKey(i, j), newCol.get(i));
        }
        return true;
    }

    @Override
    public void addItem()  throws NotEnoughSpace {
        var emptyTiles = board.availableSpace();
        if (emptyTiles.isEmpty()){
            throw new NotEnoughSpace("There are no empty tiles on the board");
        }
        int randomIndex = random.nextInt(emptyTiles.size());
        if (random.nextFloat() > 0.9) {
            board.addItem(emptyTiles.get(randomIndex), 4);
        } else {
            board.addItem(emptyTiles.get(randomIndex), 2);
        }
    }

    @Override
    public Board<Key, Integer> getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return this.board.hasValue(2048);
    }
}
