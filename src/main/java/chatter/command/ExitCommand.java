package chatter.command;

import chatter.Storage;
import chatter.TaskList;
import chatter.Ui;

/**
 * Represents a chatter.command.Command class that is responsible for exiting the chatbot.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class ExitCommand extends Command {
    /**
     * Constructor for the chatter.command.ExitCommand class for users to exit the chatbot.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Changes isExit boolean to true and closes the Scanner object in chatter.Ui class.
     *
     * @param tasks chatter.TaskList class storing an ArrayList of chatter.task.Task objects.
     * @param ui chatter.Ui class to handle user interactions.
     * @param storage chatter.Storage class to read and store tasks by the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        this.setExit();
        ui.showExit();
        return "test";
    }
}
