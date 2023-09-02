package tasks;

public class ToDo extends Task {
    public ToDo(String content) {
        super(content, false);
    }

    public ToDo(String content, boolean status) {
        super(content, status);
    }

    public ToDo mark() {
        return new ToDo(super.getContent(), true);
    }

    public ToDo unmark() {
        return new ToDo(super.getContent());
    }

    public String addTask(int listSize) {
        return "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                toString() + "\n" +
                String.format("Now you have %d tasks in the list,\n", listSize) +
                "____________________________________________________________";
    }

    public String toString() {
        if (!super.isMarked()) {
            return String.format("[T][ ] %s", super.getContent());
        } else {
            return String.format("[T][X] %s", super.getContent());
        }
    }
}
