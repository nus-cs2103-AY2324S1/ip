package duke;

import duke.storage.Storage;
import duke.commands.Command;
import duke.parser.Parser;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printMessageWithSeparator("Error loading duke.tasks from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "./data/duke.txt";
        Duke duke = new Duke(filePath);
        duke.run();
    }

    public void run() {
        this.ui.showWelcome();
        String userInput = ui.readCommand();
        Command command = null;
        while (true) {
            try {
                command = Parser.parse(userInput);
                command.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.printMessageWithSeparator(e.getMessage());
            }

            if (command instanceof Command.Exit) {
                break;
            }

            userInput = ui.readCommand();
        }
    }
}
