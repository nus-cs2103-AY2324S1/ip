package dot.commands;

import java.util.function.Consumer;

import dot.errors.DotException;
import dot.ui.Ui;

/**
 * Command to display the help message.
 */
public class HelpCommand extends Command {

    /**
     * Constructor of HelperCommand, which does nothing.
     */
    public HelpCommand() {
    }

    /**
     * Displays the help message.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     * @throws DotException On detected error.
     */
    @Override
    public void execute(Consumer<String> handleDotOutput) throws DotException {
        handleDotOutput.accept(Ui.getHelpMessage());
    }

}
