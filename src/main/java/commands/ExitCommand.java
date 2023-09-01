package commands;

import tasks.TaskList;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE = "Exiting program...";

    @Override
    public void execute(TaskList tasks) {
        System.out.println(MESSAGE);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
