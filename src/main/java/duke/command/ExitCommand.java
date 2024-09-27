package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.application.Platform;

/**
 * The ExitCommand represents a command to
 * end the chatbot and exit
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit();
        return "";
    }
}
