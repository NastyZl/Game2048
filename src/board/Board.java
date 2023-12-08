package board;

import java.util.*;

public abstract class Board<K,V> {
    private final int width;
    private final int height;
    protected Map<K, V> board = new HashMap<>();
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public abstract void clear();
    public abstract void fillBoard(List<V> list);
    public abstract List<K> availableSpace();
    public abstract void addItem(K key, V value);
    public abstract K getKey(int i, int j);
    public abstract V getValue(K key);
    public abstract List<K> getColumn(int j);
    public abstract List<K> getRow(int i);
    public abstract boolean hasValue(V value);
    public abstract List<V> getValues(List<K> keys);
    public abstract List<V> getValueRow(int i);
    public abstract List<V> getValueColumn(int i);
}
