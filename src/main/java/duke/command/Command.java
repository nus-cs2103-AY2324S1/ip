package duke.command;


import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public interface Command {
    public abstract String execute(String text, UI ui, TaskList list, Storage storage) throws DukeException;

}
