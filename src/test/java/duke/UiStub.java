package duke;

import duke.task.Task;

/**
 * Represents a UI stub.
 * A <code>UiStub</code> object corresponds to a UI
 * that does not actually print to the console. It is used for testing.
 */
public class UiStub extends Ui {

    @Override
    public void showWelcome() {
    }

    @Override
    public void showLoadingError() {
    }

    @Override
    public String readCommand() {
        return "";
    }

    @Override
    public void showError(String message) {
    }

    @Override
    public void showExit() {
    }

    @Override
    public void showLine() {
    }

    @Override
    public void showAddTask(Task task, int taskListSize) {
    }

    @Override
    public void showDeleteTask(Task removedTask, int taskListSize) {
    }

    @Override
    public void showMarkTask(boolean isMark, String task) {
    }

    @Override
    public void showManipulateAllTask(String keyword) {
    }

    @Override
    public void listTask(String[] tasks) {
    }

    @Override
    public void printDateTask(String[] tasksOnDate, String date) {
    }
}
