package arona.commands;

import arona.storage.Storage;
import arona.task.TaskList;
import arona.task.ToDoTask;
import arona.ui.Ui;

/**
 * Represents a command to add a new to-do task. When executed, this command
 * creates a new to-do task with the specified description, adds it to the task
 * list, saves it in storage, and displays a confirmation message.
 */
public class ToDoCommand extends Command implements UndoableCommand {
    private Storage storage;
    private ToDoTask todoTask;

    /**
     * Initializes a new instance of the ToDoCommand class with the specified
     * task list, user interface, storage, and task description.
     *
     * @param taskList    The task list containing the tasks.
     * @param ui          The user interface to interact with the user.
     * @param storage     The storage responsible for loading and saving tasks.
     * @param description The description of the new "To-Do" task to be added.
     */
    public ToDoCommand(TaskList taskList, Ui ui, Storage storage, String description) {
        super(taskList, ui);
        this.storage = storage;
        this.todoTask = new ToDoTask(description);
    }

    /**
     * Executes the "To-Do" command by creating a new To-Do task with the specified
     * description, adding it to the task list, saving it in storage, and displaying
     * a confirmation message to the user.
     *
     * @return A string message indicating the message in the GUI.
     */
    @Override
    public String execute() {
        taskList.getTasks().add(todoTask);
        storage.saveTask(todoTask);
        return ui.showTaskAdded(todoTask, taskList.getTasks().size());
    }


    /**
     * Reverses the "To-Do" action by removing the last added task from the task list
     * and storage, and displaying a confirmation message to the user.
     *
     * @return A string message indicating the result of the undo operation.
     */
    @Override
    public String undo() {
        int lastIndex = taskList.getTasks().size() - 1;
        if (lastIndex >= 0) {
            taskList.getTasks().remove(lastIndex);
            storage.deleteTask(lastIndex);
        }
        return ui.showUndoToDoCommand(todoTask);
    }

    /**
     * Retrieves the task index associated with this "To-Do" command.
     * As "To-Do" tasks don't have a specific task index, this method returns -1.
     *
     * @return The task index, which is -1 for "To-Do" tasks.
     */
    @Override
    public int getTaskIndex() {
        return -1;
    }
}
