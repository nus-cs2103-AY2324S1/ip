package duke;
import duke.command.Command;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.parser.Parser;
import duke.ui.Ui;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public void run() {
        ui.showWelcome(tasks.getList());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDivider();
                Command c = Parser.handleInput(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
    }
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }


    public static void main(String[] args) {
        new Duke("./data/list.txt").run();
    }
}
