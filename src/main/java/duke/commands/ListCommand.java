package duke.commands;

import duke.records.ChatRecord;

/**
 * The command to list all recorded tasks.
 * @author Toh Li Yuan (A0255811H)
 */
public class ListCommand extends Command {
    public static final String COMMAND_PHRASE = "list";
    public ListCommand() {

    }

    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    /**
     * Executes the created List Command.
     *
     * @return The list of task to be displayed to the user.
     */
    @Override
    public String execute() {
        return "You have " + chatRecord.getCount() + " tasks in your list!\n" + chatRecord.listMessage();
    }
}
