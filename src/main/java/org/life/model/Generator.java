package org.life.model;

/**
 * Universe generator interface.
 *
 * @author konianzero
 * @since 0.2
 */
public interface Generator {
    /**
     * Initialize generator with generations number and universe size.
     * Create new universe with given size.
     *
     * @param generations number of generations
     * @param universeSize universe size
     */
    void init(int generations, int universeSize);

    /**
     * Returns {@code true} if there is more generations.
     *
     * @return {@code true} if there is more generations
     */
    boolean hasNext();

    /**
     * Returns next universe.
     *
     * @return next universe
     */
    Universe next();
}
