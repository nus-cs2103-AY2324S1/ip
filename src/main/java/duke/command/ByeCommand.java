package duke.command;

import java.util.List;
import java.util.Map;

import duke.object.TaskList;
import duke.parser.element.CommandElement;
import duke.storage.Storage;
import javafx.application.Platform;

/**
 * Command to terminate the program.
 */
public class ByeCommand extends Command {

    /**
     * Constructs ByeCommand.
     *
     * @param args The arguments entered by the user.
     */
    public ByeCommand(Map<String, Object> args) {
        super("bye", args);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected List<CommandElement> getCommandElements() {
        return List.of();
    }

    /**
     * @inheritdoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Platform.exit();
        return "";
    }

}
