package Task;

public class Task {
    private String name;
    private boolean completeStatus;
    public final static String divider = "%!%";

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
            return ("TRUE" + divider + name);
        }
        return ("FALSE" + divider + name);
    }
}
