package sillybot.commands;

import sillybot.Storage;
import sillybot.Ui;
import sillybot.tasks.TaskList;

/**
 * Represents a HelpCommand object that handles the help command.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder("Here are the available commands:\n");

        for (CommandType commandType : CommandType.values()) {
            sb.append("\t")
                    .append(commandType.getCommand())
                    .append("\t:\t")
                    .append(commandType.getDescription())
                    .append(" (e.g. ")
                    .append(commandType.getExample())
                    .append(")\n\n");
        }

        return sb.toString();
    }
}
