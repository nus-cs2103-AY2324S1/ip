import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Chatbot class
 */
public class Duke {
    Ui ui;
    Storage storage;
    TaskList tasks;

    public Duke(String directoryName, String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(directoryName, fileName);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            this.tasks = new TaskList(new ArrayList<>());
            this.ui.printException(e);
        }
    }

    /**
     * The main method
     * 
     * @param args the input argument
     */
    public static void main(String[] args) {
        new Duke("./data", "storage.txt").run();
    }

    /**
     * Runs the program
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.printIntro();
        String message = scanner.nextLine();
        while (!message.equals("bye")) {
            Parser.parse(message, ui, tasks, storage);
            message = scanner.nextLine();
        }
        ui.printEnd();
        scanner.close();
    }
}