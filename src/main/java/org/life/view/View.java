package org.life.view;

import org.life.controller.Controller;
import org.life.model.Universe;

public interface View {
    void refresh(Universe universe);
    void setController(Controller controller);
}
