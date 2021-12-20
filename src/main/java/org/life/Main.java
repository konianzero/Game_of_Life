package org.life;

import org.life.controller.Controller;
import org.life.model.GenerationModel;
import org.life.model.Model;
import org.life.view.GameOfLife;
import org.life.view.View;

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
