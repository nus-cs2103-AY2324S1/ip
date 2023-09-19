package command;

import common.HelpMessage;
import common.Message;
import exceptions.InvalidIndexException;
import storage.Storage;
import task.Task;
import task.TaskList;
import utility.StringUtility;

/**
 * Represents a command print the user guide.
 *
 * @author Ho Khee Wei
 */
public class CmdHelp extends Command {
    private CommandKeyword command;

    /**
     * xxxxxx
     *
     * @param index xxxxxxx
     */
    public CmdHelp(CommandKeyword command) {
        this.command = command;
    }

    /**
     * xxxxxxx
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
        }
        return HelpMessage.INVALID;

    }

}
