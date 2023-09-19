package duke.util;

import duke.task.Task;

/**
 * Represents a UI stub.
 * A <code>UiStub</code> object corresponds to a UI
 * that does not actually print to the console. It is used for testing.
 */
public class UiStub extends Ui {

    @Override
    public Response showWelcome() {
        return null;
    }

    @Override
    public Response showLoadingError() {
        return null;
    }

    @Override
    public Response showError(String message) {
        return null;
    }

    @Override
    public Response showExit() {
        return null;
    }

    @Override
    public Response showAddTask(Task task, int taskListSize) {
        return null;
    }

    @Override
    public Response showDeleteTask(Task removedTask, int taskListSize) {
        return null;
    }

    @Override
    public Response showMarkTask(boolean isMark, String task) {
        return null;
    }

    @Override
    public Response showManipulateAllTask(String keyword) {
        return null;
    }

    @Override
    public Response showListTask(String[] tasks) {
        return null;
    }

    @Override
    public Response showPrintDateTask(String[] tasksOnDate, String date) {
        return null;
    }
}
