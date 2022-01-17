package org.life.model;

import java.util.BitSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Universe generator with classic rules:
 * <ol>
 *     <li>Any live cell with two or three live neighbours survives.</li>
 *     <li>Any dead cell with three live neighbours becomes a live cell.</li>
 *     <li>All other live cells die in the next generation. Similarly, all other dead cells stay dead.</li>
 * </ol>
 *
 * @author konianzero
 * @since 0.2
 */
public class ClassicUniverseGenerator implements Generator, Iterator<Universe> {
    /**
     * Number of generations
     */
    private int generations;

    /**
     * the Universe - a closed surface marked up into cells {@link Universe}
     */
    private Universe current;

    /**
     * generations counter
     */
    private int count = -1;

    public ClassicUniverseGenerator() {
    }

    public void init(int generations, int universeSize) {
        this.generations = generations;

        current = new Universe.Builder()
                        .setSize(universeSize)
                        .setSeed(new Random().nextInt())
                        .build();
    }

    /**
     * Returns {@code true} if there is more generations.
     *
     * @return {@code true} if there is more generations
     */
    @Override
    public boolean hasNext() {
        return count < generations;
    }

    /**
     * Returns next universe.
     *
     * @return next universe
     */
    @Override
    public Universe next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Universe next = current;
        current = generateNext(current);
        count++;
        return next;
    }

    /**
     * Generate new universe based on previous with applied rules (classic rules).
     * <p>Rules:
     * <ol>
     *     <li>Any live cell with two or three live neighbours survives.</li>
     *     <li>Any dead cell with three live neighbours becomes a live cell.</li>
     *     <li>All other live cells die in the next generation. Similarly, all other dead cells stay dead.</li>
     * </ol>
     *
     * @param current the current universe
     * @return new universe based on previous with applied rules
     */
    private Universe generateNext(Universe current) {
        Universe next = new Universe.Builder().setSize(current.getSize()).build();

        for (int[] coordinatesOfCell : current) {
            int row = coordinatesOfCell[0];
            int col = coordinatesOfCell[1];

            boolean cellState = current.getCell(row, col);
            BitSet neighbors = current.getNeighbors(row, col);

            int aliveNeighbors = neighbors.cardinality();

            // dead cell with three live neighbours becomes a live cell
            if (!cellState && aliveNeighbors == 3) {
                next.setCell(row, col, true);
            }
            // cell with two or three live neighbours survives
            else if (cellState && (aliveNeighbors == 2 || aliveNeighbors == 3)) {
                next.setCell(row, col, true);
            }
            // all other live cells die in the next generation, dead cells stay dead
            else {
                next.setCell(row, col, false);
            }
        }
        return next;
    }
}
