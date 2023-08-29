import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String storeText() {
        return " ";
    }

    public void markedChecker(String string) {
        if (string.equals("false")) {
            return;
        } else {
            this.markAsDone();
        }
    }

    public static void readTaskFromFile(String[] task, ArrayList taskList) {

        if (task[0].equals("T")) {
            Task task1 = new Todo(task[2]);
            task1.markedChecker(task[1]);
            taskList.add(task1);
        } else if (task[0].equals("D")) {
            Task task1 = new Deadline(task[2]);
            task1.markedChecker(task[1]);
            taskList.add(task1);
        } else if (task[0].equals("E")) {
            Task task1 = new Event(task[2]);
            task1.markedChecker(task[1]);
            taskList.add(task1);
        } else {
            return;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.getStatusIcon(), this.description);
    }
}
