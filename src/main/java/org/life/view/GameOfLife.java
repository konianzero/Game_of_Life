package org.life.view;

import org.life.controller.Controller;
import org.life.model.Universe;

import javax.swing.*;

public class GameOfLife extends JFrame implements View {
    private static final String generationStr = "Generation #%d";
    private static final String aliveStr = "Alive: %d";

    private Controller controller;

    private JLabel generationLabel;
    private int genCount = 0;
    private JLabel aliveLabel;
    private Field field;

    public GameOfLife() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 350);
        setLocationRelativeTo(null);

        initComponents();

        setLayout(null);
        setVisible(true);
    }

    private void initComponents() {

        JToggleButton pauseButton = new JToggleButton();
        pauseButton.setBounds(5,5, 70,20);
        pauseButton.setName("PlayToggleButton");
        pauseButton.setText("Pause");
        pauseButton.addActionListener(actionEvent -> controller.isPaused = !controller.isPaused);

        JButton resetButton = new JButton();
        resetButton.setBounds(5,30, 70,20);
        resetButton.setName("ResetButton");
        resetButton.setText("Reset");
        resetButton.addActionListener(actionEvent -> {
            controller.isReset = true;
            genCount = 0;
        });

        generationLabel = new JLabel(String.format(generationStr, 0));
        generationLabel.setBounds(5,55, 90,20);
        generationLabel.setName("GenerationLabel");

        aliveLabel = new JLabel(String.format(aliveStr, 0));
        aliveLabel.setBounds(5,75, 100,20);
        aliveLabel.setName("AliveLabel");

        field = new Field();
        field.setBounds(120,5, 300,300);

        add(pauseButton);
        add(resetButton);
        add(generationLabel);
        add(aliveLabel);
        add(field);
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void refresh(Universe universe) {
        setAliveCount(universe.getAliveCells());
        setGenerationCount(genCount++);

        field.setUniverse(universe);
        field.repaint();
    }

    public void setAliveCount(int alive) {
        aliveLabel.setText(String.format(aliveStr, alive));
    }

    public void setGenerationCount(int generation) {
        generationLabel.setText(String.format(generationStr, generation));
    }

    public void start() {
        controller.onStart();
    }
}
