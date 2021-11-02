package life.view;

import life.controller.Controller;
import life.model.Universe;

public interface View {
    void refresh(Universe universe);
    void setController(Controller controller);
}
