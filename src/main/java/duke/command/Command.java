package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;

/**
 * The Command class is an abstract base class for all commands that can be executed in the Duke application.
 * Each concrete subclass of Command represents a specific user command.
 */
public abstract class Command {
    protected String fullCommand;

    /**
     * Constructs a new Command object with the specified full command string.
     *
     * @param fullCommand The full command string as entered by the user.
     */
    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command; false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command, performing the necessary actions on the task list, user interface, and storage.
     *
     * @param tasks   The task list to be operated on by the command.
     * @param ui      The user interface to display messages or interact with the user.
     * @param storage The storage object to read from or write to a data file.
     */
    public abstract String execute(TaskList tasks , Ui ui, Storage storage);

    /**
     * Validates the input arguments based on the specified type.
     *
     * This method checks if the provided array of words contains at least two elements.
     * If the array does not meet this requirement, it throws an InvalidArgumentException
     * with the specified type.
     *
     * @param words An array of words to validate.
     * @param type  A string representing the type of validation being performed.
     * @throws InvalidArgumentException If the number of elements in the 'words' array is less than 2.
     */
    public void validateArguments(String[] words, String type) throws InvalidArgumentException {
        if (words.length < 2) {
            throw new InvalidArgumentException(type);
        }
    }
}
