import extensions.Task;
import extensions.TaskList;

public class Ui {

    private static final String HORIZONTAL_LINE = "_____________________________________________________\n";
    private static final String INTRO_MESSAGE = HORIZONTAL_LINE +
            " ____  _   _   ____  _____  ____   _     ____  _____\n" +
            "/ (__`| |_| | / () \\|_   _|/ () \\ | |__ / () \\|_   _|\n" +
            "\\____)|_| |_|/__/\\__\\ |_| /__/\\__\\|____|\\____/  |_|\n\n" +
            "Hello! I'm ChatALot.\n" +
            "What can I do for you?\n" +
            HORIZONTAL_LINE;
    private static final String OUTRO_MESSAGE = HORIZONTAL_LINE +
            "Bye. Hope to see you again soon!\n" +
            HORIZONTAL_LINE;

    public static void intro() {
        System.out.print(INTRO_MESSAGE);
    }

    public static void outro() {
        System.out.print(OUTRO_MESSAGE);
    }

    public static void addTask(Task task, int taskListSize) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:\n" +
                "  " +
                task +
                "\nNow you have " +
                taskListSize +
                " tasks in the list.");
        System.out.print(HORIZONTAL_LINE);
    }

    public static void deleteTask(Task task, int taskListSize) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(String.format("Noted. I've removed this task:\n" +
                "  " +
                task +
                "\nNow you have %d tasks in the list.", taskListSize));
        System.out.print(HORIZONTAL_LINE);
    }

    public static void mark(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:\n" +
                "  " +
                task);
        System.out.print(HORIZONTAL_LINE);
    }

    public static void unmark(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "  " +
                task);
        System.out.print(HORIZONTAL_LINE);
    }

    public static void list(TaskList taskList) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(taskList);
        System.out.print(HORIZONTAL_LINE);
    }

    public static void printError(String errorMessage) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(errorMessage);
        System.out.print(HORIZONTAL_LINE);
    }

}