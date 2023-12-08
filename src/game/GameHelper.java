package game;

import java.util.*;
public class GameHelper {
    public List<Integer> moveAndMergeEqual(List<Integer> list) {
        List<Integer> result = new ArrayList<>(list.size());

        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        var currentTile = 0;
        while (currentTile < list.size()) {
            Integer valueCurTile = list.get(currentTile);
            if (valueCurTile == null) {
                currentTile++;
                continue;
            }
            var nextTile = currentTile + 1;
            while (nextTile < list.size() && list.get(nextTile) == null) {
                nextTile++;
            }
            if (nextTile < list.size() && valueCurTile.equals(list.get(nextTile))) {
                result.add(valueCurTile * 2);
                currentTile = nextTile + 1;
            } else {
                result.add(valueCurTile);
                currentTile = nextTile;
            }
        }
        while (result.size() < list.size()) {
            result.add(null);
        }
        return result;
    }
}
