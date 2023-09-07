package task;

public interface Completable {
    boolean isCompleted();
    void setCompleted();
    void setNotCompleted();
}