package commands;

import components.DukeException;
import components.Parser;
import components.Storage;
import components.Ui;
import tasks.Task;
import tasks.TaskList;

/**
 * Represents a ToDo command.
 */
public class ToDoCommand extends Command {
    private String command;

    public ToDoCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task newTodo = Parser.createToDoTask(command, list);
        Task oldTask = newTodo.getOldTask();
        if (oldTask != null) {
            Command.tempTask = newTodo;
            throw new DukeException.DuplicateDescriptionException(oldTask);
        }
        return list.addTask(newTodo, storage);
    }
}
