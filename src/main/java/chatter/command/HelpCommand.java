package chatter.command;

import chatter.Storage;
import chatter.TaskList;
import chatter.Ui;

/**
 * Represents a chatter.command.Command class that is responsible for providing a help page
 * and listing the commands that the user can use.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class HelpCommand extends Command {
    /**
     * Constructor for the chatter.command.HelpCommand class for users to list all the tasks.
     */
    public HelpCommand() {
        super();
    }

    /**
     * Shows available user commands
     *
     * @param tasks chatter.TaskList class storing an ArrayList of chatter.task.Task objects.
     * @param ui chatter.Ui class to handle user interactions.
     * @param storage chatter.Storage class to read and store tasks by the user.
     * @return String list of user commands that the user can use.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showCommands();
    }
}
