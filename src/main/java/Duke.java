import java.io.*;
import java.util.EnumSet;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String saveLocation) {
        storage = new Storage(saveLocation);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadSavedTasks(), ui);
            parser = new Parser(ui, tasks);
        } catch (DukeNoExistingTasksException e) {
            tasks = new TaskList(ui);
            parser = new Parser(ui, tasks);
        } catch (DukeLoadTasksException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList(ui);
            parser = new Parser(ui, tasks);
        }
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    private void run() {
        ui.showWelcome();
        String cmd = ui.readCmd();
        while (parser.parseCommand(cmd)) {
            cmd = ui.readCmd();
        }
        ui.closeScanner();
    }
}
