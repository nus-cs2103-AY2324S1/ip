package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }
    static boolean isEnd = false;

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.awaitCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
