package duke;

import duke.command.Command;
import duke.task.*;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            this.ui.addErrorMessage(e);
            this.taskList = new TaskList();
        }
    }

    public void run() {
        this.ui.printEntryMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.nextCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (TaskException e) {
                ui.addErrorMessage(e);
            } finally {
                ui.printMessage();
            }
        };
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
