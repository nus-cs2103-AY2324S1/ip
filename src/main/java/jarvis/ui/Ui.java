package jarvis.ui;

import jarvis.tasks.Task;

/**
 * Represents the Ui Class.
 * Responsible for returning commands.
 *
 * @author Shishir
 */
public class Ui {
    /**
     * Returns an acknowledgment message on successful addition to the list.
     * @param size Length of the list.
     * @param task Newly added task.
     */
    public String showAdd(int size, Task task) {
        return UiMessages.ADDED_TASK + size + ") " + task.toString() + "\n"
                + String.format(UiMessages.TASK_COUNT, size);
    }

    /**
     * Returns an acknowledgment message on a successful mark/unmark.
     * @param index Index of the newly marked/unmarked task.
     * @param task Newly marked/unmarked task.
     * @param isMark Mark if true, Unmark if false.
     */
    public String showStatus(int index, Task task, boolean isMark) {
        String message;
        if (isMark) {
            message = UiMessages.STATUS_MARKED;
        } else {
            message = UiMessages.STATUS_UNMARKED;
        }
        return message + index + ") " + task.toString() + "\n" + UiMessages.ASSIST;
    }

    /**
     * Returns an acknowledgment message on a successful deletion of a task.
     * @param index Index of the newly deleted task.
     * @param task Newly deleted task.
     */
    public String showDelete(int index, Task task) {
        return UiMessages.DELETE_SUCCESS + index + ") " + task.toString() + "\n" + UiMessages.ASSIST;
    }

    /**
     * Returns an acknowledgment message on request to display all the tasks.
     * @param size Size of the task list.
     */
    public String showList(int size) {
        return size == 0 ? UiMessages.EMPTY_TASK_LIST : UiMessages.TASKS_DISPLAYED;
    }

}
