package duke.stubs;

import duke.Ui;
import duke.tasks.Task;

/**
 * Stub for Ui class, created for testing purposes.
 */
public class UiStub extends Ui {
    private String line = "--------------------------------------------------------------------";

    @Override
    public void showInvalidDateFormat() {
        System.out.println("(・´з`・) Uh oh...dates must be of YYYY-MM-DD HH:mm format");
        System.out.println(line);
    }

    @Override
    public void showTaskAdded(Task task, int listSize) {
        System.out.println("(｀･ω･´)ﾉ New task added:\n" + task);
        this.showNumberOfTasks(listSize);
    }

    @Override
    public void showError(String message) {
        System.out.println(message);
        System.out.println(line);
    }

    public String showLine() {
        return this.line;
    }
}
