package potato.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String priority;

    public Task(String description, boolean isDone, String priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    public static Task parse(String input) {
        if (input.startsWith("todo ")) {
            return Todo.todoParse(input.substring(5), "0");

        } else if (input.startsWith("deadline ")) {
            // empty deadline
            // no by
            return Deadline.deadlineParse(input.substring(9));

        } else if (input.startsWith("event ")) {
            // empty event
            // no from
            // no to
            return Event.eventParse(input.substring(6));
        } else {
            return null;
        }
    }

    public static Task savedParse(String input) {
        String[] parts = input.split(" \\| ");
        System.out.println(parts[0]);
        System.out.println(parts[1]);
        System.out.println(parts[2]);

        switch (parts[0]) {
        case "T":
            System.out.println("its todo");
            return Todo.todoSavedParse(parts);
        case "D":
            // empty deadline
            // no by
            return Deadline.deadlineSavedParse(parts);
        case "E":
            // empty event
            // no from
            // no to
            return Event.eventSavedParse(parts);
        default:
            return null;
        }
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public void setStatus(boolean bool) {
        isDone = bool;
    }

    public String getDescription() {
        return description;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String toSave() {
        return "";
    }

    public String toString() {
        return "[" + getStatus() + "]" + "[" + priority.toUpperCase() + "] " + description;
    }
}
