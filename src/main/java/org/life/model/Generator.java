package org.life.model;

import java.util.BitSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Generator implements Iterable<Universe> {
    private final int generations;

    private Universe first;

    public Generator(int generations) {
        this.generations = generations;
    }

    public void setFirstUniverse(Universe first) {
        this.first = first;
    }

    @Override
    public Iterator<Universe> iterator() {
        return new UniverseIterator();
    }

    private class UniverseIterator implements Iterator<Universe> {
        private Universe current = first;
        private int i = -1;
        public boolean hasNext()  { return i < generations;                 }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Universe next() {
            if (!hasNext())       { throw new NoSuchElementException();         }
            Universe next = current;
            current = generateNext(current);
            i++;
            return next;
        }
    }

    private Universe generateNext(Universe current) {
        Universe next = new Universe.Builder().setSize(current.getSize()).build();

        for (int[] coordinatesOfCell: current) {
            int row = coordinatesOfCell[0];
            int col = coordinatesOfCell[1];

            boolean cellState = current.getCell(row, col);
            BitSet neighbors = current.getNeighbors(row, col);

            int aliveNeighbors = neighbors.cardinality();

            if (!cellState && aliveNeighbors == 3) {
                next.setCell(row, col, true);
            } else if (cellState && (aliveNeighbors == 2 || aliveNeighbors == 3)) {
                next.setCell(row, col, true);
            } else {
                next.setCell(row, col, false);
            }
        }
        return next;
    }
}
