import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.file.Paths.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    String fileName = "Duke.txt";
    String[] directories = {"data"};

    public Duke() {
        ui = new Ui();
        storage = new Storage(fileName, directories);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showSoftLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                if (!isExit) {
                    ui.showHardLine();
                }
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
