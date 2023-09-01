package duke;

/**
 * The Ui class handles user interface interactions and displays messages to the user.
 */
public class Ui {
    public static String greet() {
        return "Hello! I'm Untitled! What can I do for you?";
    }

    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    public static String add(Task task, int newSize) {
        return String.format("Got it! I've added this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.",
                task.toString(),
                newSize);
    }

    public static String delete(Task task, int newSize) {
        return String.format("Noted. I've removed this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.",
                task.toString(),
                newSize);
    }

    public static String mark(Task task) {
        return "Nice! I've marked this task as done:\n"
            + task.toString();
    }

    public static String unmark(Task task) {
        return "OK, I've marked this task as not done yet:\n" +
            task.toString();
    }
}
