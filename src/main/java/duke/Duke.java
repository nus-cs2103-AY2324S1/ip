package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.io.FileNotFoundException;

public class Duke {
    private static TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks =  new TaskList(this.storage.readTasksFromFile());
        } catch (FileNotFoundException e) {
            tasks =  new TaskList();
        }
    }
    public void run() {
        Ui.greet();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                Ui.showError(e);
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}