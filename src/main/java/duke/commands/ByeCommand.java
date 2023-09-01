package duke.commands;

import duke.records.ChatRecord;

public class ByeCommand extends Command {
    public static final String COMMAND_PHRASE = "bye";
    private static final String BYE_PHRASE = "Access Terminated! Hope to see you again soon!";
    public ByeCommand() { }
    @Override
    public void init(ChatRecord records) { }

    /**
     * Executes the created Bye Command.
     *
     * @return The bye phrase to be displayed as feedback to the user.
     */
    @Override
    public String execute() {
        return BYE_PHRASE;
    }

    public static boolean isBye(Command command) {
        return command instanceof ByeCommand;
    }
}
