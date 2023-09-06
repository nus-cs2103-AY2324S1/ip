package duke;

import duke.command.Command;
import duke.exception.KoraException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/savedtask.txt");
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
            command.execute(taskList, storage);
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
        try {
            Duke kora = new Duke();
            kora.run();
        } catch (Exception e) {
            System.out.println("haha,,");
        }

    }
}
