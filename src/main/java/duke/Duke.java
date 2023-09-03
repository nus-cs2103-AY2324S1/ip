package duke;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.command.Command;
import duke.parser.Parser;

import java.io.IOException;

public class Duke {
    private static final String filePath = "./duke.txt";
    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;

    public Duke(String name) {
        this.ui = new Ui(name);
        storage = new Storage(filePath);
        try {
           tasks = new TaskList(storage.readFile());
        } catch (IOException e) {
            ui.showLoadingError(e);
            this.tasks = new TaskList();
        }
    }
    public void run() {
        ui.showWelcome();
        while (ui.hasNextLine()) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
