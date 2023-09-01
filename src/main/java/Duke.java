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
    // Attribute
    static final String DIRECTORY_NAME = "./data";
    static final String  FILE_NAME = "storage.txt";
    Ui ui;
    Storage storage;
    TaskList tasks;

    // Constructor

    /**
     * The constructor of Duke
     * 
     * @param directoryName the name of directory
     * @param fileName the name of the file
     */
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

    // Method

    /**
     * The main method
     * 
     * @param args the input argument
     * @throws IOException
     */
    public static void main(String[] args) {
        new Duke(DIRECTORY_NAME, FILE_NAME).run();
    }

    /**
     * Runs the program
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.printIntro();
        String message = scanner.nextLine();
        while(!message.equals("bye")) {
            Parser.parse(message, ui, tasks, storage);
            message = scanner.nextLine();
        }
        ui.printEnd();
        scanner.close();
    }
}