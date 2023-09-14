package commands;

import functions.TaskList;
import tasks.ToDo;

/**
 * Represents a command to load a todo task into a task list.
 */
public class LoadToDoCommand extends Command {

    private String currentTaskAsString;
    private TaskList taskList;

    /**
     * Constructs a new LoadEventCommand object with the given parameters.
     *
     * @param currentTaskAsString a string representation of the todo task to be loaded
     * @param taskList the task list to which the todo task should be added
     */
    public LoadToDoCommand(String currentTaskAsString, TaskList taskList) {
        this.currentTaskAsString = currentTaskAsString;
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        boolean isDone = currentTaskAsString.substring(4, 5).toUpperCase().equals("X");
        int descriptionBeginIndex = 7;

        String description = currentTaskAsString.substring(descriptionBeginIndex);
        ToDo todo = new ToDo(description, isDone);
        taskList.add(todo);
        return "Ok";
    }
}
