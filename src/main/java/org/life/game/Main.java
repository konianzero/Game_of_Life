package org.life.game;

import org.life.game.controller.Controller;
import org.life.game.model.ClassicUniverseGenerator;
import org.life.game.model.Generator;
import org.life.game.view.SwingView;
import org.life.game.view.View;

public class Main {
    public static void main(String[] args) {

        Generator generator = new ClassicUniverseGenerator();
        View view = new SwingView();
        Controller controller = new Controller();

        view.setController(controller);
        controller.setView(view);
        controller.setGenerator(generator);
    }
}
