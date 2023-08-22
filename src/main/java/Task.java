public class Task {
    private final String descr;
    private boolean isDone;
    private final int id;

    public Task(String descr, int id) {
        this.descr = descr;
        this.isDone = false;
        this.id = id;
    }

    //methods to change task status
    public void markDone() {
        isDone = true;
    }
    public void markUndone() {
        isDone = false;
    }

    public String marking() {
        return (isDone ? "X" : " ");
    }
    public String toString() {
        return this.id + ". [" + marking() + "] " + this.descr; //groups "1. [ ] task" tgt
    }

}
