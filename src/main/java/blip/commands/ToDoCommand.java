package blip.commands;

import blip.ui.BlipUI;
import blip.tasks.TaskList;
import blip.tasks.ToDo;
import blip.storage.BlipStorage;
import blip.priority.Priority;


/**
 * Represents the to do command to add to do task.
 */
public class ToDoCommand extends Command {
    /**
     * Description of the to do task.
     */
    String description;

    /**
     * Priority of the to do task.
     */
    Priority priority;

    /**
     * Creates an instance of ToDoCommand.
     *
     * @param description The description of the to do task
     */
    public ToDoCommand(String description, Priority priority) {
        this.description = description;
        this.priority = priority;
    }

    /**
     * Executes the to do command to add to do task.
     *
     * @param taskList The Array List of tasks to add the to do task into
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     * @return String message shown to user.
     */
    @Override
    public String execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        ToDo toDoTask = new ToDo(description, false, this.priority);
        taskList.addTask(toDoTask);
        storage.saveToFile(taskList);
        return ui.addsTasksMsg(toDoTask, taskList.size());
    }
}
