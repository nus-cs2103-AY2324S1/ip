package sam.commands;

import sam.constants.Message;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;
import sam.tasks.Task;
import sam.tasks.ToDo;

import java.io.IOException;

/**
 * Adds a ToDo to the task list.
 */
public class AddToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo to the task list. \n"
            + "Example: " + COMMAND_WORD
            + " borrow books";
    private String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task todo = new ToDo(description);
            tasks.addTask(todo);
            ui.printMessage(Message.ADD_TASKS, "\t" + todo, tasks.getTaskCountSummary());
            storage.saveTasksToFile(tasks);
            return new CommandResult(Message.ADD_TASKS, "\t" + todo, tasks.getTaskCountSummary());
        } catch (IOException e) {
            ui.showError(Message.FAILED_TO_SAVE + e.getMessage());
            return new IncorrectCommand(Message.FAILED_TO_SAVE
                    + e.getMessage()).execute(tasks, ui, storage);
        }
    }

}
