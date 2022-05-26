package org.life.game.view;

import org.life.game.controller.Controller;
import org.life.game.model.Universe;

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
