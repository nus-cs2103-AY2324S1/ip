package task;

public class ToDo extends Task {
    private String title;

    public ToDo(String response) {
        super(false);
        int toTrim = response.indexOf(" ");
        String taskTitle = response.substring(toTrim + 1);
        this.title = taskTitle;
    }

    @Override
    public String toFileString() {
        if (this.done == true) {
            return "T | 1 | " + this.title;
        }
        return "T | 0 | " + this.title;
    }

    @Override
    public String toString() {
        if (this.done == true) {
            return "[T] " + "[X] " + this.title;
        }
        return "[T] " + "[ ] " + this.title;
    }
}
