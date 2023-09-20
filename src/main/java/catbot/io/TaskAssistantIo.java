package catbot.io;

import catbot.task.Task;
import catbot.task.TaskList;

/**
 * An object that supports interacting with the user to manage {@link Task Tasks} in a {@link TaskList TaskList}.
 */
public interface TaskAssistantIo {

    /**
     * Displays all Tasks in a TaskList with numbering.
     *
     * @param taskList TaskList containing Tasks to print.
     */
    void displayTaskList(TaskList taskList);

    /**
     * Displays all Tasks in a TaskList without numbering.
     *
     * @param taskList TaskList containing Tasks to print.
     */
    void displayTaskListWithoutNumber(TaskList taskList);

    /**
     * Displays that a Task was added to a list.
     * Assumes the Task was added to the end of the TaskList.
     *
     * @param taskList TaskList to which the Task was added.
     */
    void displayTaskAdded(TaskList taskList);

    /**
     * Displays that a Task was deleted from its list.
     *
     * @param deleted Task that was deleted.
     */
    void displayTaskDeleted(Task deleted);

    /**
     * Displays that a Task was modified.
     *
     * @param taskList the list containing the edited Task.
     * @param index the index of the modified task, in the given TaskList.
     */
    void displayTaskModified(TaskList taskList, int index);

}
