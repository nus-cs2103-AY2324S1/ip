package duke;

import duke.task.TaskList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.run(new Parser(tasks));
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}

