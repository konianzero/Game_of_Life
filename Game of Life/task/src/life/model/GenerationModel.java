package life.model;

import java.util.Iterator;
import java.util.Random;

public class GenerationModel implements Model {
    private Generator generator;
    private Iterator<Universe> iterator;

    public GenerationModel() {
    }

    public void initModel(int size) {
        long seed = new Random().nextInt();
        int generations = Integer.MAX_VALUE;

        generator = new Generator(generations);
        generator.setFirstUniverse(new Universe.Builder()
                .setSize(size)
                .setSeed(seed)
                .build()
        );

        iterator = generator.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Universe getNext() {
        return iterator.next();
    }
}
