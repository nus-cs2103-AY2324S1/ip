package task;

public class Task {
    public static final String DIVIDER = "%!%";
    private String name;
    private boolean completeStatus;

    public Task(String name) {
        this.name = name;
        this.completeStatus = false;
    }
    public Task(String name, boolean completeStatus) {
        this.name = name;
        this.completeStatus = completeStatus;
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
    public String fileFormat() {
        if (completeStatus) {
            return ("TRUE" + DIVIDER + name);
        }
        return ("FALSE" + DIVIDER + name);
    }
}
