package org.life;

import org.life.controller.Controller;
import org.life.model.ClassicUniverseGenerator;
import org.life.model.Generator;
import org.life.view.SwingView;
import org.life.view.View;

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
