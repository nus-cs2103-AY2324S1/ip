public class DukeExceptionHandler {
    public static void handleEmptyInput() {
        Ui ui = new Ui();
        ui.showLine();
        ui.formatString(" OOPS!!! Please input a task.");
        ui.showLine();
    }

    public static void handleUnseenInput() {
        Ui ui = new Ui();
        ui.showLine();
        ui.formatString(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        ui.showLine();
    }

    public static void printErrorMsg(String s) {
        Ui ui = new Ui();
        ui.showLine();
        ui.formatString(" " + s);
        ui.showLine();
    }

    public static void handleTaskNumOutOfBounds(int taskNum) {
        Ui ui = new Ui();
        ui.showLine();
        ui.formatString(" OOPS!!! There is no task " + taskNum + ".");
        ui.showLine();
    }
}
