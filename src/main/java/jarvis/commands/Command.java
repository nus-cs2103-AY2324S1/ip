package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.gui.Ui;
import jarvis.exceptions.InvalidIndexException;
import jarvis.exceptions.InvalidTaskFormatException;
import jarvis.tasks.TaskList;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException, InvalidTaskFormatException;
}
