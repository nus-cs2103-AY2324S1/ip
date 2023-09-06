package bot.command;

public class TerminateCommand extends Command {
    private static final String MESSAGE = "____________________________________________________________\n" +
            " Bye. Hope to see you again soon!\n" +
            "____________________________________________________________";

    /**
     * Execute a series of instructions specific to terminating the Bot chatbot
     */
    public void execute() {
        System.out.println(this);
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
