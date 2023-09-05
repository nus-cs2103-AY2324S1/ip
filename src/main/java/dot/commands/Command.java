package dot.commands;

import java.util.function.Consumer;

import dot.errors.DotException;

/**
 * Responsible for execution of the app's logic.
 */
public abstract class Command {

    /**
     * Executes the operations of the command.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     * @throws DotException passing the buck.
     */
    public void execute(Consumer<String> handleDotOutput) throws DotException {
    }

    /**
     * Returns true if Command should terminate the program.
     *
     * @return false unless overridden.
     */
    public boolean isTerminateCommand() {
        return false;
    }

}
