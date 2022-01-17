package org.life;

import org.life.controller.Controller;
import org.life.model.ClassicUniverseGenerator;
import org.life.model.Generator;
import org.life.view.GameOfLife;
import org.life.view.View;

public class Main {
    public static void main(String[] args) {

        Generator generator = new ClassicUniverseGenerator();
        View view = new GameOfLife();
        Controller controller = new Controller();

        view.setController(controller);
        controller.setView(view);
        controller.setModel(generator);

        ((GameOfLife) view).start();
    }
}
