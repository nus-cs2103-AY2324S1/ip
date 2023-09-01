package Remy.command;
import Remy.Task.TaskList;
import Remy.Ui;
import Remy.Storage;
import Remy.ChatbotException;

public abstract class Command {

    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatbotException;
}
