import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Program to run a task manager that can add, delete and mark tasks.
 *
 * @author Teo Kai Sheng
 */
public class Duke {
    public static ArrayList<Task> list = new ArrayList<Task>();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTaskList());
    }
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Parser parser = new Parser(ui, tasks);
        parser.parse(input);
        while (!parser.isDone()) {
            input = scanner.nextLine();
            parser.parse(input);
        }
        storage.updateTaskList();
    }

    public static void main(String[] args) {
        new Duke(Paths.get(".", "data", "duke.txt")).run(); // ./data/duke.txt
    }
}
