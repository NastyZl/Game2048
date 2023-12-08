package board;

import java.util.*;

public class SquareBoard<V> extends Board<Key, V> {
    public SquareBoard(int size) {
        super(size, size);
    }

    public void clear(){
        for (var entry: board.entrySet()) {
            entry.setValue(null);
        }
    }

    @Override
    public void fillBoard(List<V> list) {
        this.board.clear();
        ListIterator<V> iterator = list.listIterator();
        if (list.size() > availableSpace().size()) {
            throw new RuntimeException("The entered number of elements exceeds the number of board tiles");
        }
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if(iterator.hasNext()) {
                    board.put(new Key(i, j), iterator.next());
                } else return;
            }
        }
    }

    @Override
    public List<Key> availableSpace() {
        List<Key> keysWithNullValues = new ArrayList<>();
        for (var entry: board.entrySet()) {
            if (entry.getValue() == null) {
                keysWithNullValues.add(entry.getKey());
            }
        }
        return keysWithNullValues;
    }

    @Override
    public void addItem(Key key, V value) {
        board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        for (var entry: board.entrySet()) {
            if (entry.getKey().getI() == i && entry.getKey().getJ() == j) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public V getValue(Key key) {
        return board.get(key);
    }

    @Override
    public List<Key> getColumn(int j) {
        List<Key>  column = new ArrayList<>();
        for (var i = 0; i < getHeight(); i++) {
                column.add(getKey(i, j));
        }
        return column;
    }

    @Override
    public List<Key> getRow(int i) {
        List<Key> row = new ArrayList<>();
        for (var j = 0; j < getWidth(); j++) {
                row.add(getKey(i, j));
        }
        return row;
    }

    @Override
    public boolean hasValue(V value) {
        return board.containsValue(value);
    }

    @Override
    public List<V> getValues(List<Key> keys) {
        List<V> values = new ArrayList<>();
        for (var key: keys) {
            values.add(getValue(key));
        }
        return values;
    }

    public List<V> getValueRow(int i){
        return getValues(getRow(i));
    }

    public List<V> getValueColumn(int i){
        return getValues(getColumn(i));
    }

}
