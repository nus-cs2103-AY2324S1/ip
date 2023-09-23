package dude.command;

import dude.note.NoteList;
import dude.task.TaskList;
import dude.ui.Ui;
import dude.util.Storage;

/**
 * Abstract parents class of Commands that can be created by users.
 */
public abstract class Command {

    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    }
}
