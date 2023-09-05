package duke;

import duke.command.Command;
import duke.task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws DukeException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = storage.loadIntoList(new TaskList());
    }

    public void run() throws DukeException {
        this.ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printException(e.getMessage());
            }
        }
        this.ui.sendOff();
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }
}
