package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Represents a Todo Command to be executed
 */
public class TodoCommand extends Command {
    private String input;

    public TodoCommand(String input, TaskList taskList) {
        super(taskList);
        this.input = input;
    }

    /**
     * Adds todo Task to taskList
     *
     * @return response string
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        String[] splitStr = input.trim().split("\\s+");
        inputChecker(splitStr, "todo");
        Todo t = new Todo(input.substring(5));
        taskList.addTask(t);
        return t.addedMessage();
    }
}
