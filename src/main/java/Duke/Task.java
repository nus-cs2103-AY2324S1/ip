package Duke;
public class Task {
    String name;
    boolean completeStatus;

    public Task(String name) {
        this.name = name;
        this.completeStatus = false;
    }

    public void markDone() {
        this.completeStatus = true;
    }

    public void markUndone() {
        this.completeStatus = false;
    }
    public String toString() {
        if (completeStatus) {
            return ("[X] " + name);
        }
        return ("[ ] " + name);
    }
}
