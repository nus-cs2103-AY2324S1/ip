package pardiyem.command;

import pardiyem.storage.Storage;
import pardiyem.task.TaskList;
import pardiyem.ui.Ui;

/**
 * Represents a command that ends the running of the program
 */
public class ByeCommand extends Command {
    /**
     * A constructor for the ByeCommand class
     *
     * @param desc additional arguments for the command
     * @throws IllegalArgumentException if the string desc is not empty
     */
    public ByeCommand(String desc) {
        super(desc);
        if (!this.desc.isEmpty()) {
            throw new IllegalArgumentException(String
                    .format("You used \"%s\" as an argument. A bye command shouldn't have any arguments", desc));
        }
    }

    /**
     * Utility inherited method to indicate whether this command will end the program
     *
     * @return true
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Inherited method to execute a command
     * Will show the goodbye message to the user
     *
     * @param taskList the TaskList object to modify
     * @param ui the Ui object to send the message to
     * @param storage the Storage object that will handle the saving to the data file
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showOutput("Ciao! See you again!");
    };

    /**
     * Utility inherited method to compare two objects.
     * All ByeCommand objects are equal
     *
     * @param obj the object to compare with
     * @return whether the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
