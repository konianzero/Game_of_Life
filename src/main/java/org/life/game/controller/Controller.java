package org.life.game.controller;

import org.life.game.model.Generator;
import org.life.game.view.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class controls game actions.
 */
public class Controller {

    /** Logger */
    private final Logger log = LoggerFactory.getLogger(Controller.class);
    /** Game thread that starts main game loop */
    private final Thread gameThread = new Thread(this::mainLoop);
    /** UI {@link View} */
    private View view;
    /** Universe {@link Generator} */
    private Generator generator;

    /** Flag for game pause */
    private volatile boolean paused = false;
    /** Flag for game reset */
    private boolean reset = true;

    /**
     * Set UI view.
     *
     * @param view UI view
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * Set universe generator.
     *
     * @param generator universe generator
     */
    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    /**
     * Switched pause flag.
     */
    public synchronized void togglePauseFlag() {
        this.paused = !this.paused;
    }

    /**
     * Return pause flag.
     *
     * @return pause flag
     */
    public boolean getPauseFlag() {
        return this.paused;
    }

    /**
     * Set reset flag to true.
     */
    public void setResetFlagToTrue() { this.reset = true; }

    /**
     * Start main loop one time.
     */
    public void startMainLoop() {
        if (!gameThread.isAlive()) {
            gameThread.start();
        }
    }

    /**
     * Main game loop.
     */
    private void mainLoop() {
        while (true) {
            if (reset) {
                initGenerator(100);
                reset = false;
                paused = false;
            }

            while (paused) {
                Thread.onSpinWait();
            }

            if (generator.hasNext()) {
                view.refresh(generator.next());
                delay();
            } else {
                break;
            }
        }
    }

    /**
     * Initialize generator with new universe size.
     * @param size universe size
     */
    private void initGenerator(int size) {
        generator.init(Integer.MAX_VALUE, size);
    }

    /**
     * Delay for displaying universe in the interface.
     */
    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("Game Thread Interrupted", e);
            gameThread.interrupt();
        }
    }
}
