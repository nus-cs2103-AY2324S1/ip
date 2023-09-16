package commands;

import functions.TaskList;
import tasks.ToDo;

/**
 * The class for executing an addition command of a todo task
 */
public class ToDoCommand extends Command {

    private TaskList taskList;
    private String description;

    /**
     * Constructs a new ToDoCommand object with the given parameters.
     *
     * @param taskList the task list to which the new ToDo task should be added
     * @param description the description of the new ToDo task
     */
    public ToDoCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.description = description;
    }

    @Override
    public String execute() {
        if (this.description.strip().isEmpty() || this.description.matches("todo")) {
            return "OOPS! The description of a todo cannot be empty.";
        }

        ToDo newTodo = new ToDo(this.description);
        this.taskList.add(newTodo);

        return "I have added the following: \n" + newTodo.getTaskAsString();
    }
}
