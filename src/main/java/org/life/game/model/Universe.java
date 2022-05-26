package org.life.game.model;

import java.util.*;
import java.util.stream.IntStream;

/**
 * This class represents the Universe - a closed surface marked up into cells.
 *
 * @author konianzero
 * @since 0.5
 */
public class Universe implements Iterable<int[]> {
    private static final String ALIVE = "O";
    private static final String DEAD = " ";
    private static final int[][] neighborsCoordinates = new int[][] {
            {-1, -1}, {0, -1}, {1, -1},
            {-1,  0},          {1,  0},
            {-1,  1}, {0,  1}, {1,  1}
    };

    private final boolean[][] map;
    private final Random rand;

    private final int size;
    private int aliveCells = 0;

    public void setCell(int row, int col, boolean state) {
        map[row][col] = state;
        if (state) { aliveCells++; }
    }

    public boolean getCell(int row, int col) {
        return map[row][col];
    }

    public int getAliveCells() { return aliveCells; }

    public Universe(int size) {
        this.size = size;
        map = new boolean[size][size];

        rand = new Random();
        fillRandom();
    }

    private void fillRandom() {
        IntStream.range(0, size)
                .forEach(row -> IntStream.range(0, size)
                        .forEach(col -> setCell(row, col, rand.nextBoolean()))
                );
    }

    public int getSize() {
        return size;
    }

    public BitSet getNeighbors(int row, int col) {
        BitSet neighbors = new BitSet();
        int neighborsCount = 0;
        for (int[] coords: neighborsCoordinates) {
            int r = get(coords[0] + row);
            int c = get(coords[1] + col);
            if (map[r][c]) {
                neighbors.set(neighborsCount);
            }
            neighborsCount++;
        }
        return neighbors;
    }

    private int get(int a) {
        if (a >= size) {
            return 0;
        } else if (a < 0) {
            return size - 1;
        }
        return a;
    }

    public Iterator<int[]> iterator() {
        return new CoordinatesIterator();
    }

    private class CoordinatesIterator implements Iterator<int[]> {
        private int i = 0;
        private final int[][] coordinates = getCellsCoordinates();
        @Override public boolean hasNext()  { return i < coordinates.length; }
        @Override public void remove()      { throw new UnsupportedOperationException("Remove not support!"); }

        @Override public int[] next() {
            if (!hasNext())       { throw new NoSuchElementException(); }
            int[] c = coordinates[i];
            i++;
            return c;
        }

        private int[][] getCellsCoordinates() {
            int[][] comb = new int[size * size][];
            IntStream.range(0, size)
                    .forEach(row -> IntStream.range(0, size)
                            .forEach(col -> comb[row * size + col] = new int[] {row, col})
                    );
            return comb;
        }
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        IntStream.range(0, size)
                .forEach(row -> {
                    IntStream.range(0, size)
                            .forEach(col ->
                                    buf.append(
                                            map[row][col] ? ALIVE : DEAD
                                    )
                            );
                    buf.append("\n");
                });

        return buf.toString();
    }
}
