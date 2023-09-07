package duke.command;

import duke.exception.InvalidParametersException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/** Command interface **/
public interface Command {

    StringBuilder output = new StringBuilder();
    /**
     * Executes code related to the nature of command.
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui UI that interacts between the app and the user.
     * @param storage Storage to save or load data from file.
     * @throws InvalidParametersException If command has parameters, then it can throw an InvalidParameters Exception.
     */
    void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidParametersException;

    /**
     * Checks whether the application is configured to exit.
     * By default, it will always return false unless stated otherwise.
     *
     * @return Command
     */
    default boolean isExit() {
        return false;
    };

    default String getString() {
        String outputString = output.toString();
        output.delete(0, output.length());
        return outputString;
    };
}
