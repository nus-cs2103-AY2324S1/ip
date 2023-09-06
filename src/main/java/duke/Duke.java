package duke;

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
    static final String DIRECTORY_NAME = "./data";
    static final String FILE_NAME = "storage.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs the duke.Duke
     *
     * @param directoryName the name of the directory
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

    /**
     * The main method
     *
     * @param args the input argument
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
        while (!message.equals("bye")) {
            Parser.parse(message, ui, tasks, storage);
            message = scanner.nextLine();
        }
        Parser.parse(message, ui, tasks, storage);
        scanner.close();
    }

    /**
     * Gets the response based on the input
     *
     * @param input the input of the program
     * @return the response based on the input
     */
    public String getResponse(String input) {
        return Parser.parse(input, ui, tasks, storage);
    }
}
