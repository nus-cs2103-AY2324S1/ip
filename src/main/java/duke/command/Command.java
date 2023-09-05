package duke.command;

import duke.exception.KoraException;
import duke.task.TaskList;

/**
 * Command class executes and sets messages for different types of commands.
 */
public abstract class Command {
    private static String line = "------------------------------";

    /**
     * Class constructor of Command.
     */
    public Command() {
    }

    /**
     * Prints output of the command in nice format.
     * @param output Message after command is executed.
     */
    public void printOutput(String output) {
        System.out.println(line + "\n" + output + "\n" + line);
    }

    /**
     * Returns message for the command.
     * @return String message of command.
     */
    public abstract String getCommandMessage();

    /**
     * Executes the command according to the type of command.
     * @param taskList List with tasks.
     * @throws KoraException From its child class.
     */
    public abstract void execute(TaskList taskList) throws KoraException;

    /**
     * Returns false for all the commands except ByeCommand.
     * @return Boolean.
     */
    public boolean isExitYet() {
        return false;
    }
}
