package duke.stubs;

import duke.components.Ui;
import duke.tasks.Task;

/**
 * Stub for Ui class, created for testing purposes.
 */
public class UiStub extends Ui {

    @Override
    public String showInvalidDateFormat() {
        return "(・´з`・) Uh oh...dates must be of YYYY-MM-DD HH:mm format";
    }

    @Override
    public String showTaskAdded(Task task, int listSize) {
        String header = "(｀･ω･´)ﾉ New task added:\n" + task + "\n";
        String numberOfTasksLeft = this.showNumberOfTasks(listSize);
        return header + numberOfTasksLeft;
    }
}
