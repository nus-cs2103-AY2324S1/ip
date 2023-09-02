package jarvis.commands;

import jarvis.Storage;
import jarvis.TaskList;
import jarvis.Ui;
import jarvis.exceptions.InvalidIndexException;
import jarvis.exceptions.InvalidTaskFormatException;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException, InvalidTaskFormatException;
}
