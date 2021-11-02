package life.view;

import life.model.Universe;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class Field extends JPanel {

    private int columnCount;
    private int rowCount;

    private boolean calculated = false;
    private int width;
    private int height;
    private int cellWidth;
    private int cellHeight;
    private int xOffset;
    private int yOffset;

    private Universe universe;

    public void setUniverse(Universe universe) {
        this.universe = universe;

        if (!calculated) {
            calcParams();
            calculated = true;
        }
    }

    private void calcParams() {
        columnCount = universe.getSize();
        rowCount = universe.getSize();

        width = getWidth();
        height = getHeight();

        cellWidth = width / columnCount;
        cellHeight = height / rowCount;

        xOffset = (width - (columnCount * cellWidth)) / 2;
        yOffset = (height - (rowCount * cellHeight)) / 2;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(columnCount, rowCount);
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        if (Objects.nonNull(universe)) {
            paintUniverse(g2d);
        }
    }

    private void paintUniverse(Graphics2D g2d) {

        for (int[] coordinates: universe) {
            Rectangle cell = getRect(coordinates);

            if (isAlive(coordinates)) {
                g2d.setColor(Color.DARK_GRAY);
                g2d.fill(cell);
            } else {
                g2d.setColor(Color.GRAY);
            }

            g2d.draw(cell);
        }

        g2d.dispose();
    }

    private Rectangle getRect(int[] coordinates) {
        return new Rectangle(
                xOffset + (coordinates[1] * cellWidth),
                yOffset + (coordinates[0] * cellHeight),
                cellWidth,
                cellHeight);
    }

    private boolean isAlive(int[] coordinates) {
        return universe.getCell(coordinates[0], coordinates[1]);
    }
}
