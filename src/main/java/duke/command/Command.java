package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.InvalidCommandException;
import duke.task.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A command class that will be executed to perform an action made by the user
 */
public abstract class Command {
    Matcher matcher;

    public Command() {
    }

    public Command(String response, String regex) {
        Pattern pattern = Pattern.compile(regex);
        this.matcher = pattern.matcher(response);
    }
    public abstract String execute(Storage storage, Ui ui, TaskList taskList) throws InvalidCommandException;
}
