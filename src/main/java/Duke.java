import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

//My chatbot function
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.storage.createFileIfNotExists();
        try {
            this.tasks = new TaskList(this.storage.loadTasksFromFile());
        } catch (IOException e) {
            this.ui.showError("Unable to load saved task. Starting with a new empty task list");
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
