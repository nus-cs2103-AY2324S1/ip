package duke;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The main class for the Duke application.
 * Duke is a task management application that allows users to manage tasks.
 *
 */

public class Duke  {

    private Storage storage;
    private Parser parser;
    private TaskList tasks;
    private Commands command;
    private Ui ui;

    /**
     * Constructs a Duke instance with the file path.
     * Initializes the user interface, task lists, commands and parser.
     *
     * @param filePath The file path for loading and saving task data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadTaskFromFile());
        } catch (NullPointerException e){
            tasks = new TaskList();
        } catch (Exception e) {
            ui.printFileError();
            new File(filePath);
            tasks = new TaskList();
        }
        command = new Commands(ui,storage,tasks);
        parser = new Parser(ui, storage, tasks, command);
    }

    /**
     * Runs the Duke application.
     * Displays a greeting message, processes user commands from the command-line input
     * and provides responses accordingly. The loop until user commands bye.
     */
    public void run() {
        ui.showGreeting();

        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();

            if(input.equals("bye")) {
                ui.closeGreeting();
                break;
            } else {
                parser.analyseInput(input);
            }
        }
    }

    /**
     * The main entry point of the Duke application.
     * Creates a new instance of Duke with a specified file path and runs the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("src/main/MYBOT.txt").run();
    }
}

