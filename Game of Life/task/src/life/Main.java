package life;

import life.controller.Controller;
import life.model.GenerationModel;
import life.model.Model;
import life.view.GameOfLife;
import life.view.View;

public class Main {
    public static void main(String[] args) {

        Model model = new GenerationModel();
        View view = new GameOfLife();
        Controller controller = new Controller();

        view.setController(controller);
        controller.setView(view);
        controller.setModel(model);

        ((GameOfLife) view).start();
    }
}
