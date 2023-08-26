package duke;
public class DukeExceptionHandler {
    public static void handleEmptyInput() {
        Ui ui = new Ui();
        ui.showLine();
        ui.formatString(" Input a task my brother.");
        ui.showLine();
    }

    public static void handleUnseenInput() {
        Ui ui = new Ui();
        ui.showLine();
        ui.formatString(" I'm sorry brother, I do not have a Scooby :-(");
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
        ui.formatString(" There is no task " + taskNum + ", friend.");
        ui.showLine();
    }
}
