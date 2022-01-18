package org.life.view;

import org.life.controller.Controller;
import org.life.model.Universe;

import javax.swing.*;

/**
 * View based on Java Swing.
 */
public class SwingView extends JFrame implements View {
    /** Const Strings for text in generation label */
    private static final String GENERATION_COUNT = "Generation #%d";
    /** Const Strings for text in alive label */
    private static final String ALIVE_CELLS = "Alive: %d";

    /** Game controller */
    private Controller controller;

    /** Displays number of generations */
    private JLabel generationLabel;
    /** Number of generation */
    private int genCount = 0;
    /** Displays number of alive cells */
    private JLabel aliveLabel;
    /** Universe representation (grid) */
    private Field field;

    /**
     * Create swing view.
     */
    public SwingView() {
        super("Game Of Life");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(615, 545);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        initComponents();

        setVisible(true);
    }

    /**
     * Initialize swing components.
     */
    private void initComponents() {

        JButton resetButton = new JButton();
        resetButton.setBounds(5,5, 100,20);
        resetButton.setText("Start/Reset");
        resetButton.addActionListener(actionEvent -> {
            controller.setResetFlagToTrue();
            genCount = 0;
            controller.startMainLoopOnce();
        });

        JToggleButton pauseButton = new JToggleButton();
        pauseButton.setBounds(5,30, 100,20);
        pauseButton.setText("Pause");
        pauseButton.addActionListener(actionEvent -> {
            controller.togglePauseFlag();
            resetButton.setEnabled(!controller.getPauseFlag());
        });

        generationLabel = new JLabel(String.format(GENERATION_COUNT, 0));
        generationLabel.setBounds(5,55, 100,20);

        aliveLabel = new JLabel(String.format(ALIVE_CELLS, 0));
        aliveLabel.setBounds(5,75, 100,20);

        field = new Field();
        field.setBounds(110,5, 500,500);

        add(resetButton);
        add(pauseButton);
        add(generationLabel);
        add(aliveLabel);
        add(field);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(Universe universe) {
        setAliveCount(universe.getAliveCells());
        setGenerationCount(genCount++);

        field.setUniverse(universe);
        field.repaint();
    }

    /**
     * Set number of alive cells to label.
     *
     * @param alive number of alive cells
     */
    public void setAliveCount(int alive) {
        aliveLabel.setText(String.format(ALIVE_CELLS, alive));
    }

    /**
     * Set number of generation.
     *
     * @param generation number of generation
     */
    public void setGenerationCount(int generation) {
        generationLabel.setText(String.format(GENERATION_COUNT, generation));
    }
}
