package org.life.game.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * This class represents the Universe - a closed surface (toroidal) marked up into cells.
 *
 * @author konianzero
 * @since 0.5
 */
public class Universe implements Iterable<int[]> {
    private static final int[][] neighborsCoordinates = new int[][] {
            {-1, -1}, {0, -1}, {1, -1},
            {-1,  0},          {1,  0},
            {-1,  1}, {0,  1}, {1,  1}
    };

    private final boolean[][] matrix;
    private final int size;
    private int aliveCells = 0;

    public Universe(int size) {
        this.size = size;
        matrix = new boolean[size][size];
        fillRandom();
    }

    public void setCellState(int row, int col, boolean state) {
        matrix[row][col] = state;
        if (state) { aliveCells++; }
    }

    public boolean getCellState(int row, int col) {
        return matrix[row][col];
    }

    public int getSize() {
        return size;
    }

    public int getAliveCells() { return aliveCells; }

    private void fillRandom() {
        Random rand = ThreadLocalRandom.current();
        IntStream.range(0, size)
                .forEach(row -> IntStream.range(0, size)
                        .forEach(col -> setCellState(row, col, rand.nextBoolean()))
                );
    }

    public BitSet getNeighbors(int row, int col) {
        BitSet neighbors = new BitSet(8);
        int neighborsCount = 0;
        for (int[] coords: neighborsCoordinates) {
            int r = get(coords[0] + row);
            int c = get(coords[1] + col);
            if (matrix[r][c]) {
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
}
