package test;

import game.GameHelper;

import static java.util.Arrays.asList;

public class HelperTest {
    private final static GameHelper helper = new GameHelper();

    public static void main(String[] args) {
        BoardTest.assertEquals(helper.moveAndMergeEqual(asList(1, 2, null, 3)), asList(1, 2, 3, null));
        BoardTest.assertEquals(helper.moveAndMergeEqual(asList(2, 2, null, 3)), asList(4, 3, null, null));
        BoardTest.assertEquals(helper.moveAndMergeEqual(asList(2, 2, 4, 4)), asList(4, 8, null, null));
        BoardTest.assertEquals(helper.moveAndMergeEqual(asList(2, 2, 2, 3)), asList(4, 2, 3, null));
        BoardTest.assertEquals(helper.moveAndMergeEqual(asList(2, null, null, 2)), asList(4, null, null, null));
        BoardTest.assertEquals(helper.moveAndMergeEqual(asList(null, null, null, null)), asList(null, null, null, null));
        BoardTest.assertEquals(helper.moveAndMergeEqual(asList(null, null, null, 2)), asList(2, null, null, null));
        BoardTest.assertEquals(helper.moveAndMergeEqual(asList(null, null, 2, 2)), asList(4, null, null, null));
    }
}
