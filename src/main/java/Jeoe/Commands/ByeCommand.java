package Jeoe.Commands;

import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.TaskManager;

/**
 * This class encapsulates a command that ends the Jeoe chatbot.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class ByeCommand extends Command {

    /**
     * Constructor for a ByeCommand object.
     * @param input The string input by the user to parse into a command.
     */
    ByeCommand(String input) {
        super(true);
    }

    /**
     * Executes the bye command.
     * Prints an exit command.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        ui.showEndString();
    }
}
