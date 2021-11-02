package life.model;

public interface Model {
    void initModel(int size);
    boolean hasNext();
    Universe getNext();
}
