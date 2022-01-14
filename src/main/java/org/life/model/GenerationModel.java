package org.life.model;

import java.util.Random;

public class GenerationModel implements Model {
    private Generator generator;

    public GenerationModel() {
    }

    public void initModel(int size) {
        long seed = new Random().nextInt();
        int generations = Integer.MAX_VALUE;

        generator = new ClassicUniverseGenerator(
                generations,
                new Universe.Builder()
                        .setSize(size)
                        .setSeed(seed)
                        .build()
        );
    }

    @Override
    public boolean hasNext() {
        return generator.hasNext();
    }

    @Override
    public Universe getNext() {
        return generator.next();
    }
}
