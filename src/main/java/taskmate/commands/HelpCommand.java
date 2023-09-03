package taskmate.commands;

import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.Storage;

/**
 * The HelpCommand class is a child class of the Command class. It represents the `help` command which allows the user
 * to view the list of available commands and how each command should be formatted.
 */
public class HelpCommand extends Command {

    String commandType;
    boolean isExit;

    /**
     * HelpCommand constructor that allows the user to create a `help` command that guides them to use TaskMate.
     */
    public HelpCommand() {
        this.commandType = "Help";
        this.isExit = false;
    }

    /**
     * Executes the `help` command by the user by printing out the list of available commands and how each command
     * should be formatted.
     *
     * @param tasks TaskList object that stores the list of undeleted tasks defined by the user.
     * @param ui Ui object that deals with taking in user input and printing messages out to the user.
     * @param storage Storage object that saves undeleted tasks to the disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printInputSpecifications(storage.getAbsoluteSaveFilePath());
    }
}
