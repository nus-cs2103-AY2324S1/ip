package taskmate.commands;

import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;

import java.io.IOException;

/**
 * The ExitCommand class is a child class of the Command class. It represents the `bye` command which stops the
 * application and saves the undeleted tasks to the disk.
 */
public class ExitCommand extends Command {

    String commandType;

    /**
     * ExitCommand constructor that allows the user to stop the application and save the undeleted tasks to the
     * disk.
     * Note: This is the only command that has a value of `true` in the isExit instance attribute.
     */
    public ExitCommand() {
        this.commandType = "Exit";
        this.isExit = true;
    }

    /**
     * Executes the `bye` command by the user by attempting to save the undeleted tasks to the disk using the `storage`
     * instance. If the writing process encounters an exception, a failure message is printed by the `ui` instance to
     * inform the user that the command failed to write the undeleted tasks to the disk.
     * Lastly, a farewell message is printed out by the `ui` object to the user, which lists the undeleted tasks.
     *
     * @param tasks TaskList object that stores the list of undeleted tasks defined by the user.
     * @param ui Ui object that deals with taking in user input and printing messages out to the user.
     * @param storage Storage object that saves undeleted tasks to the disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Exit procedure
        // 1. Write incomplete tasks to disk
        String saveTaskText = tasks.formatAllTasksForSaving();
        try {
            storage.writeToFile(saveTaskText);
        } catch (IOException e) {
            ui.printSaveFailResponse(System.getProperty("user.dir") +
                    storage.getSaveFilePath().substring(1).replace("/", "\\"));
        }
        // 2. Print exit message
        ui.farewellUser();
    }

}
