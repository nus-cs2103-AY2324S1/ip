package command;

import common.HelpMessage;
import task.TaskList;

/**
 * Represents a command to print the guide to the commands.
 *
 * @author Ho Khee Wei
 */
public class CmdHelp extends Command {
    private CommandKeyword command;

    /**
     * Constructs a CmdHelp object with a command keyword.
     *
     * @param command The command keyword that the user is requesting help for.
     */
    public CmdHelp(CommandKeyword command) {
        this.command = command;
    }

    /**
     * Executes the command that prints the help information regarding a command.
     *
     * @param taskList Not used in this command.
     * @return response to the user.
     */
    @Override
    public String execute(TaskList taskList) {
        switch (command) {

        case TODO:
            return HelpMessage.TODO;

        case DEADLINE:
            return HelpMessage.DEADLINE;

        case EVENT:
            return HelpMessage.EVENT;

        case EXIT:
            return HelpMessage.EXIT;

        case DELETE:
            return HelpMessage.DELETE;

        case FIND:
            return HelpMessage.FIND;

        case HELP:
            return HelpMessage.INVALID;

        case LIST:
            return HelpMessage.LIST;

        case MARK:
            return HelpMessage.MARK;

        case UNMARK:
            return HelpMessage.UNMARK;

        case PRIORITY:
            return HelpMessage.PRIORITY;

        case INVALID:
            return HelpMessage.INVALID;

        case TOGGLE:
            return HelpMessage.TOGGLE;

        default:
            return HelpMessage.INVALID;
        }
    }

}
