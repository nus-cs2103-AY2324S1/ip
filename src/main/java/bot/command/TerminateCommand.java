package bot.command;

public class TerminateCommand extends Command {
    private static final String MESSAGE = Command.SPACER + "\n" +
            " Bye. Hope to see you again soon!\n" +
            Command.SPACER;

    /**
     * Execute a series of instructions specific to terminating the Bot chatbot
     */
    public String execute() {
        return this.toString();
    }

    /**
     * Returns a String representation of TerminateCommand
     *
     * @return String representation of TerminateCommand
     */
    @Override
    public String toString() {
        return TerminateCommand.MESSAGE;
    }
}
