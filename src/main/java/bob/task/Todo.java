package bob.task;

import bob.exception.InvalidPriorityException;

public class Todo extends Task {

    /**
     * Todo constructor
     * @param description in the form e.g."p/high read"
     * @throws InvalidPriorityException
     */
    public Todo(String description)
            throws InvalidPriorityException, IndexOutOfBoundsException {

        super(description.split(" ")[1]);

        try {
            String priority = description.split(" ")[0].split("/")[1];
            super.priority = Enum.valueOf(Priority.class, priority);
        } catch (Exception e) {
            throw new InvalidPriorityException();
        }
    }

    public Todo(String name, boolean done, String priority) {
        super(name);
        super.done = done;
        super.priority = Enum.valueOf(Priority.class, priority);
    }

    /**
     * Converts object to string representation for user display
     * @return string representation
     */
    @Override
    public String toString() {
        String done = this.done ? "[X]" : "[ ]";

        return "[T]" + done + super.priorityToString() + " " + this.name;
    }

    /**
     * Parses string into a Todo object
     * @param str is in the form e.g. "0 | low | read book"
     * @return Todo object
     * @throws IndexOutOfBoundsException when parsing fails, as string split does not occur correctly.
     */
    public static Todo parseTodo(String str) throws IndexOutOfBoundsException {
        String[] strSplit = str.split(" \\| ", 3);

        boolean isDone = strSplit[0].equals("1");
        String priority = strSplit[1];
        String name = strSplit[2];

        return new Todo(name, isDone, priority);
    }

    /**
     * Converts object into string to be stored in bob.txt
     * @return string representation
     */
    @Override
    public String toTxt() {
        String separation = " | ";
        return "todo" + separation
                + (done ? 1 : 0) + separation
                + super.priority + separation + super.name;
    }
}
