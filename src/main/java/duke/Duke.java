package duke;

import duke.command.Command;
import duke.exception.KoraException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main class of Kora Chatbot Program.
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Class constructor of Duke.
     *
     * @param filePath path of file in user's hard disk.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        try {
            storage.loadTask(taskList);
        } catch (KoraException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Executes and prints command.
     * @param userInput Input from the user.
     * @return Command.
     */
    public Command getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            command.execute(taskList);
            command.printOutput(command.getCommandMessage());
            return command;
        } catch (KoraException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Runs main function of Kora Chatbot.
     */
    public void run() {
        ui.greet();
        while (!isExit) {
            String userInput = ui.getUserInput();
            Command command = getResponse(userInput);
            if (command == null) {
                isExit = false;
            } else {
                isExit = command.isExitYet();
            }
        }
        ui.closeScanner();
    }

    /**
     * Runs the main programme.
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        Duke kora = new Duke("./data/savedtask.txt");
        kora.run();
    }
}
