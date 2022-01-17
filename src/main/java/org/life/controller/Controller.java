package org.life.controller;

import org.life.model.Generator;
import org.life.view.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller {

    private final Logger log = LoggerFactory.getLogger(Controller.class);
    private View view;
    private Generator generator;

    private boolean paused = false;
    private boolean reset = true;

    public void setView(View view) {
        this.view = view;
    }

    public void setModel(Generator generator) {
        this.generator = generator;
    }

    public boolean isPaused() { return paused; }

    public void setPaused(boolean paused) { this.paused = paused; }

    public void setReset(boolean reset) { this.reset = reset; }

    public void onStart() {
        while (true) {
            if (reset) {
                initModel(50);
                reset = false;
            }

            while (paused) {
                sleep(100);
            }

            if (generator.hasNext()) {
                view.refresh(generator.next());
                sleep(1000);
            } else {
                break;
            }
        }
    }

    private void initModel(int size) {
        generator.init(Integer.MAX_VALUE, size);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.warn("Thread Interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}
