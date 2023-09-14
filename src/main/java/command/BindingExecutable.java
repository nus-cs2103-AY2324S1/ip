package command;

import duke.TaskList;
import duke.UserInterface;

/**
 * Represents an executable that either binds or unbinds a
 */
public class BindingExecutable implements Executable {
    private final boolean successState;
    private final boolean boundState;
    private final String sourceBinding;
    private final String customBinding;

    /**
     * Creates a new binding executable instance.
     * @param wasSuccessful whether the binding was successful.
     * @param wasBound whether an unbinding or rebinding was called.
     * @param sourceBinding The original binding command string.
     * @param customBinding THe custom binding command string.
     */
    public BindingExecutable(boolean wasSuccessful, boolean wasBound, String sourceBinding, String customBinding) {
        this.successState = wasSuccessful;
        this.boundState = wasBound;
        this.sourceBinding = sourceBinding;
        this.customBinding = customBinding;
    }

    /**
     * Executes and prints out to the ui the result of the bind command.
     * @param list the list associated with the bot (unnecessary.)
     * @param ui the ui to output to.
     * @return false, since the bot does not shut down.
     */
    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        StringBuilder outputBuilder = new StringBuilder();
        if (boundState) {
            outputBuilder.append("Adding of custom binding ");
        } else {
            outputBuilder.append("Removal of custom binding ");
        }
        outputBuilder.append("\"").append(customBinding).append("\"");
        outputBuilder.append(" to the source binding ");
        outputBuilder.append("\"").append(sourceBinding).append("\" was ");
        if (successState) {
            outputBuilder.append("successful.");
        } else {
            outputBuilder.append("unsuccessful.");
        }
        ui.output(outputBuilder.toString());
        return false;
    }

}
