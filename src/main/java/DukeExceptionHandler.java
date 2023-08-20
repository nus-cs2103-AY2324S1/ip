public class DukeExceptionHandler {
    public static void handleEmptyInput() {
        Duke.printDivider();
        Duke.formatString(" \u2639 OOPS!!! Please input a task.");
        Duke.printDivider();
    }

    public static void handleUnseenInput() {
        Duke.printDivider();
        Duke.formatString(" \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        Duke.printDivider();
    }

    public static void printErrorMsg(String s) {
        Duke.printDivider();
        Duke.formatString(" " + s);
        Duke.printDivider();
    }

    public static void handleTaskNumOutOfBounds(int taskNum) {
        Duke.printDivider();
        Duke.formatString(" \u2639 OOPS!!! There is no task " + taskNum + ".");
        Duke.printDivider();
    }
}
