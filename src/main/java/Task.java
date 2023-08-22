public class Task {
    int number;
    boolean done;
    String taskName;

    private Task(int number, boolean done, String taskName) {
        this.number = number;
        this.done = done;
        this.taskName = taskName;
    }

    public static Task newTask(int number, String taskName) {
        return new Task(number, false, taskName);
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return done
                ? String.format("%d.[X] %s",number, taskName)
                : String.format("%d.[ ] %s",number, taskName);
    }
}
