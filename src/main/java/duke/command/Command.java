package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

public abstract class Command {

    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return " ";
    }
}
