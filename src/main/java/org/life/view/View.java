package org.life.view;

import org.life.controller.Controller;
import org.life.model.Universe;

/**
 * UI view interface.
 *
 * @author konianzero
 * @since 0.3
 */
public interface View {
    /**
     * Refresh view.
     *
     * @param universe new {@link Universe}
     */
    void refresh(Universe universe);

    /**
     * Set controller.
     *
     * @param controller {@link Controller}
     */
    void setController(Controller controller);
}
