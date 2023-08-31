package taskmaster;

import taskmaster.exceptions.DukeException;
import taskmaster.parser.Parser;
import taskmaster.storage.Storage;
import taskmaster.tasks.TaskList;
import taskmaster.ui.Ui;

import java.util.Scanner;
public class Taskmaster {
    private static final String FILE_PATH = "./Data.txt";
    public static boolean activated = true;
    private Storage storage;
    private Scanner scanner;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;

    public Taskmaster() {
        this.storage = new Storage(FILE_PATH);
        this.scanner = new Scanner(System.in);
        this.ui = new Ui();
        this.parser = new Parser();
        this.taskList = new TaskList();
    }

    private void run() throws DukeException {
        this.storage.loadTasksFromFile();
        this.ui.printHello();

        while (Taskmaster.activated) {
            String userInput = scanner.nextLine();
            parser.parse(userInput, this.storage, this.taskList);
        }
        this.ui.printGoodbye();
    }

    public static void main(String[] args) throws DukeException {
        new Taskmaster().run();
    }

}

