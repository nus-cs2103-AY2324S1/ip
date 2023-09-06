public class Task {
    protected String description;
    protected boolean isDone;
    private static String LINE = "-----------------------------------------\n";

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public static Task parse(String input) {
        if (input.startsWith("todo ")) {
<<<<<<< HEAD
            return Todo.todoParse(input.substring(5), "0");
=======
            return Todo.todoParse(input.substring(5));
>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297

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
<<<<<<< HEAD
        String[] parts = input.split(" \\| ");
        System.out.println(parts[0]);
        System.out.println(parts[1]);
        System.out.println(parts[2]);

        switch (parts[0]) {
            case "T":
                System.out.println("its todo");
                return Todo.todoParse(parts[2], parts[1]);
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

=======
        if (input.startsWith("[T]")) {
            // split using | and read everything including mark
            return Todo.todoParse(input.substring(7));

        } else if (input.startsWith("[D]")) {
            // empty deadline
            // no by
            return Deadline.deadlineSavedParse(input.substring(7));

        } else if (input.startsWith("[E]")) {
            // empty event
            // no from
            // no to
            return Event.eventSavedParse(input.substring(7));
        } else {
            return null;
        }
    }
>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public void setStatus(boolean bool) {
        isDone = bool;
    }

    public String toSave() {
        return "";
    }

    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
