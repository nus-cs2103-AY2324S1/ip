package duke;

import duke.command.Parser;
import duke.task.TaskList;

/**
 * Class that implements the chatbot
 */
public class Duke {
    /**
     * Constructs the required variables for the chatbot and runs it
     * @param args Command-line arguments passed in at startup
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");
        TaskList taskList = new TaskList(storage);

        ui.showWelcomeMessage();

        while (true) {
            try {
                String userCommand = ui.getUserInput();

                if (userCommand.trim().equalsIgnoreCase("bye")) {
                    ui.showGoodbyeMessage();
                    break;
                }

                String response = Parser.parseCommand(userCommand, taskList);
                ui.showMessage(response);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }

        ui.closeScanner();
    }
}

