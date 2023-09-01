package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
