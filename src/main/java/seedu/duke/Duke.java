package seedu.duke;

import java.util.Scanner;

import seedu.duke.datafile.Storage;
import seedu.duke.exceptions.LemonException;
import seedu.duke.parser.Parser;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

/**
 * Duke is the class representing a chatbot named Lemon.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor of Duke that represents the chatbot Lemon with a given filepath that
     * stores the tasks that are added by the user.
     * @param filePath the file path that stores the task details.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (LemonException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Run the chatbot named Lemon.
     */
    public void run() {
        ui.welcomeMessage();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            //System.out.println("in while loop");
            String input = scanner.nextLine();
            //System.out.println("Received input: " + input); // Provide feedback to the user
            if (input.equalsIgnoreCase("bye")) {
                isRunning = false;
            } else {
                //System.out.println("parsing soon");
                try {
                    Parser.parseTasks(input, tasks, storage, ui);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        ui.bye();
    }



    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
