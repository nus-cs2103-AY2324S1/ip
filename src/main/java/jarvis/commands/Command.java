package jarvis.commands;

import jarvis.exceptions.InvalidIndexException;
import jarvis.exceptions.InvalidTaskFormatException;
import jarvis.ui.Ui;
import jarvis.storage.Storage;
import jarvis.tasks.TaskList;

/**
 * Interface to define the methods required in Command classes
 */
public interface Command {
    String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException, InvalidTaskFormatException;
}
