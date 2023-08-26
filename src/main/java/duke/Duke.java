package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            try {
                File f = new File(filePath);
                if (f.createNewFile()) {
                    tasks = new TaskList();
                }
            } catch (IOException Ioe) {
                ui.showIoError();
            }
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.line();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e){
                ui.showIoError();
            } catch (IndexOutOfBoundsException e){
                ui.showOutOfBounds();
            } finally {
                ui.line();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("storage.txt").run();
    }
}
