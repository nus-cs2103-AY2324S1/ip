import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;

public class Tong {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Tong() {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.load();
        parser = new Parser();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = parser.parse(fullCommand);
            c.setData(this.tasks);
            CommandResult result = c.execute();
            ui.showResultToUser(result);
            isExit = ExitCommand.isExit(c);
        }
        storage.save(this.tasks);
    }

    public static void main(String[] args) {
        new Tong().run();
    }
}
