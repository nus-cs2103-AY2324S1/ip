package duke;

import duke.command.Command;
import duke.command.InvalidCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private Storage storage;

    private TaskList list;

    private Ui ui;

    private Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage(ui);
        parser = new Parser(ui);
        try {
            list = new TaskList(storage.readFile());
        } catch (Exception e) {
            ui.showError("Error reading from file.");
            list = new TaskList();
        }
    }

    public void run() {
        ui.startMessage();

        Command command = new InvalidCommand();
        while (command.type != Command.CommandType.BYE) {
            command = parser.parseCommand(ui.commandPrompt());
            command.execute(list, ui);
        }

        // Write to file
        try {
            storage.writeFile(list);
        } catch (Exception e) {
            ui.showError("Error writing to file.");
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
