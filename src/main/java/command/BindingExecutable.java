package command;

import duke.TaskList;
import duke.UserInterface;

/**
 * Represents an executable that either binds or unbinds a command to an alias.
 */
public class BindingExecutable implements Executable {
    private final String sourceBinding;
    private final String customBinding;
    private final String errorMessage;


    /**
     * Creates a new binding executable instance, with an error message if it failed. Since there is a source binding
     * provided, this indicates a rebinding.
     * @param sourceBinding The original binding command string.
     * @param customBinding The custom binding command string.
     * @param errorMessage the error message, if the binding was unsuccessful. Otherwise, should be blank.
     */
    public BindingExecutable(String sourceBinding, String customBinding, String errorMessage) {
        this.sourceBinding = sourceBinding;
        this.customBinding = customBinding;
        this.errorMessage = errorMessage;
    }

    /**
     * Creates a new binding executable instance, with an error message if it failed. Since there is no source binding,
     * this indicates a deletion.
     * @param customBinding The custom binding command string.
     * @param errorMessage the error message, if the binding was unsuccessful. Otherwise, should be blank.
     */
    public BindingExecutable(String customBinding, String errorMessage) {
        this.sourceBinding = null;
        this.customBinding = customBinding;
        this.errorMessage = errorMessage;
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
        if (this.sourceBinding != null) {
            outputBuilder.append("Adding of custom binding ");
        } else {
            outputBuilder.append("Removal of custom binding ");
        }
        outputBuilder.append("\"").append(customBinding).append("\"");
        if (this.sourceBinding != null) {
            outputBuilder.append(" to the source binding ");
            outputBuilder.append("\"").append(sourceBinding).append("\"");
        }
        outputBuilder.append(" was ");
        if (!errorMessage.isBlank()) {
            outputBuilder.append("unsuccessful.");
            outputBuilder.append(" Reason: ");
            outputBuilder.append(errorMessage);
        } else {
            outputBuilder.append("successful.");
        }
        ui.output(outputBuilder.toString());
        return false;
    }

}
