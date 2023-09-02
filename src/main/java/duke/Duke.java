package duke;

import duke.command.Parser;
import duke.task.TaskList;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui(); // Create Ui instance
        Storage storage = new Storage("./data/duke.txt");
        TaskList taskList = new TaskList(storage);

        ui.showWelcomeMessage(); // Display welcome message

        while (true) {
            try {
                String userCommand = ui.getUserInput(); // Get user input

                if (userCommand.trim().equalsIgnoreCase("bye")) {
                    ui.showGoodbyeMessage(); // Display goodbye message
                    break;
                }

                String response = Parser.parseCommand(userCommand, taskList);
                ui.showMessage(response); // Display response
            } catch (DukeException e) {
                ui.showMessage(e.getMessage()); // Display error message
            }
        }

        ui.closeScanner(); // Close scanner
    }
}

