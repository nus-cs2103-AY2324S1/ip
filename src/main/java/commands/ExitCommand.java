package commands;

import tasks.TaskList;

/**
 * This class instructs the application to exit.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE = "Exiting program...";

    @Override
    public void execute(TaskList tasks) {
        System.out.println(MESSAGE);
    }

    /**
     * Checks whether the Command object is an instance of an ExitCommand.
     *
     * @param command
     * @return true if Command is an ExitCommand, false otherwise.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
