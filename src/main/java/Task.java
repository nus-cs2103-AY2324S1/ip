public abstract class Task {
    private final String descr;
    private boolean isDone;

    public Task(String descr) {
        this.descr = descr;
        this.isDone = false;
    }

    //methods to change task status
    public void markDone() {
        isDone = true;
    }
    public void markUndone() {
        isDone = false;
    }

    public void delete() {

    }
    public String marking() {
        return (isDone ? "X" : " ");
    }
    public String toString() {
        return "[" + marking() + "] " + this.descr; //groups "[ ] task" tgt
    }

}
