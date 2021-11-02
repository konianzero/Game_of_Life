package life.model;

import java.util.*;
import java.util.stream.IntStream;

public class Universe implements Iterable<int[]> {
    private static final String ALIVE = "O";
    private static final String DEAD = " ";
    private static final int[][] neighborsCoordinates = new int[][] {
            {-1, -1}, {0, -1}, {1, -1},
            {-1,  0},          {1,  0},
            {-1,  1}, {0,  1}, {1,  1}
    };

    private final boolean[][] map;

    private int size;
    private int aliveCells = 0;

    public void setCell(int row, int col, boolean state) {
        map[row][col] = state;
        if (state) { aliveCells++; }
    }

    public boolean getCell(int row, int col) {
        return map[row][col];
    }

    public int getAliveCells() { return aliveCells; }

    private Universe(int size, Optional<Long> seed) {
        this.size = size;
        map = new boolean[size][size];

        if (!seed.isEmpty()) {
            fillRandom(seed.get());
        }
    }

    private void fillRandom(long seed) {
        final Random random = new Random(seed);
        IntStream.range(0, size)
                .forEach(row -> IntStream.range(0, size)
                        .forEach(col -> setCell(row, col, random.nextBoolean()))
                );
    }

    public static class Builder {
        private int size;
        private Optional<Long> seed = Optional.empty();

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setSeed(long seed) {
            this.seed = Optional.of(seed);
            return this;
        }

        public Universe build() {
            return new Universe(size, seed);
        }
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
        private int[][] coordinates = getCellsCoordinates();
        public boolean hasNext()  { return i < coordinates.length;              }
        public void remove()      { throw new UnsupportedOperationException();  }

        public int[] next() {
            if (!hasNext())       { throw new NoSuchElementException();         }
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
        StringBuffer buf = new StringBuffer();
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
