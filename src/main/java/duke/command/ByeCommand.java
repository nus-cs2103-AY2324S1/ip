package duke.command;

import duke.object.TaskList;
import duke.parser.element.CommandElement;
import duke.storage.Storage;
import duke.ui.Ui;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Command to terminate the program.
 */
public class ByeCommand extends Command {

    /**
     * Constructor for ByeCommand.
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.print("Bye. Hope to see you again soon!");
    }

    /**
     * @inheritdoc
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
