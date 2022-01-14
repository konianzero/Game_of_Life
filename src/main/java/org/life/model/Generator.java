package org.life.model;

/**
 * Universe generator interface.
 *
 * @author konianzero
 * @since 0.2
 */
public interface Generator {
    boolean hasNext();
    Universe next();
}
