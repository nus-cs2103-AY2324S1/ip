package duke.command;

import duke.exception.KoraException;
import duke.list.CommandList;
import duke.list.TaskList;
import duke.storage.Storage;

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
     * @param storage Storage where tasks are stored.
     * @throws KoraException From its child class.
     */
    public abstract void execute(TaskList taskList, Storage storage) throws KoraException;

    /**
     * Returns false for all the commands except ByeCommand.
     * @return Boolean.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns true for commands related to command names.
     * @return Boolean.
     */
    public boolean isSetCommand() {
        return false;
    }

    /**
     * Executes the command related to command names according to the type of command.
     * @param commandList List with command names.
     * @param storage Storage where command names are stored.
     * @throws KoraException From its child class.
     */
    public void executeSet(CommandList commandList, Storage storage) throws KoraException {
        return;
    }

    /**
     * Returns true for commands related to files.
     * @return Boolean.
     */
    public boolean isFileCommand() {
        return false;
    }

    /**
     * Returns specified file path for commands related to files.
     * @return String file path.
     */
    public String getFilePath() {
        return "";
    }
}
