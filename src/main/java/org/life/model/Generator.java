package org.life.model;

/**
 * Universe generator interface.
 *
 * @author konianzero
 * @since 0.2
 */
public interface Generator {
    void init(int generations, int universeSize);
    boolean hasNext();
    Universe next();
}
