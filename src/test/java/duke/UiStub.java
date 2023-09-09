package duke;

import duke.task.Task;

/**
 * Represents a UI stub.
 * A <code>UiStub</code> object corresponds to a UI
 * that does not actually print to the console. It is used for testing.
 */
public class UiStub extends Ui {

    @Override
    public String showWelcome() {
        return "";
    }

    @Override
    public String showLoadingError() {
        return "";
    }

    @Override
    public String showError(String message) {
        return "";
    }

    @Override
    public String showExit() {
        return "";
    }

    @Override
    public String showAddTask(Task task, int taskListSize) {
        return "";
    }

    @Override
    public String showDeleteTask(Task removedTask, int taskListSize) {
        return "";
    }

    @Override
    public String showMarkTask(boolean isMark, String task) {
        return "";
    }

    @Override
    public String showManipulateAllTask(String keyword) {
        return "";
    }

    @Override
    public String showListTask(String[] tasks) {
        return "";
    }

    @Override
    public String showPrintDateTask(String[] tasksOnDate, String date) {
        return "";
    }
}
