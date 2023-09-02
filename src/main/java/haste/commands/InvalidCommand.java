package haste.commands;

import haste.data.TaskList;
import haste.exceptions.HasteException;
import haste.ui.Ui;

public class InvalidCommand extends Command {
    private String message;
    public InvalidCommand(String message) {
        this.message = message;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) throws HasteException {
        throw new HasteException(message);
    }
}
