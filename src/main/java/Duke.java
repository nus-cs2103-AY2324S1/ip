import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            ui.printException(e);
            tasks = new TaskList(storage);
        } catch (IOException e) {
            ui.printException(new DukeException("Unable to find storage file."));
            tasks = new TaskList(storage);
        }
    }

    public void run() {
        Ui.welcomeMessage();
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String command = userInput.nextLine();
            Parser p = new Parser(command, this.storage, this.tasks);
            p.parse();
            if (p.isEnd()) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
