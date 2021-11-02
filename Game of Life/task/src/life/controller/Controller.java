package life.controller;

import life.model.Model;
import life.view.View;

public class Controller {
    private View view;
    private Model model;

    public boolean isPaused = false;
    public boolean isReset = true;

    public void setView(View view) {
        this.view = view;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void onStart() {
        while (true) {
            if (isReset) {
                model.initModel(50);
                isReset = false;
            }

            while (isPaused) {
                sleep(100);
            }

            if (model.hasNext()) {
                view.refresh(model.getNext());
                sleep(1000);
            } else {
                break;
            }
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
