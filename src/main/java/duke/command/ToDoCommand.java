package duke.command;

import duke.Ui;
import duke.Storage;
import duke.exceptions.InvalidCommandException;
import duke.task.*;

/**
 * Adds a ToDo task to the taskList
 */
public class ToDoCommand extends Command {
    protected static final String regex = "^todo\\s([\\w\\s]*)$";
    public ToDoCommand(String response) {
        super(response, regex);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws InvalidCommandException {
        if (!matcher.find() || matcher.groupCount() != 1) {
            throw new InvalidCommandException("Invalid use of todo. Usage: todo <task description>");
        }

        String description = matcher.group(1);
        Task task = new ToDo(description);
        taskList.addTask(task);
        return "Got it. I've added this task:\n" +
                task + "\n" +
                String.format("Now you have %d tasks in your list", taskList.getSize());
    }
}
