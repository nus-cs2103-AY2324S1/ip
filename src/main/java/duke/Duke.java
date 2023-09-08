package duke;

import command.Command;

import parser.Parser;

import storage.FileHandler;
import storage.TaskList;

/**
 * duke.Duke class is a simple chatbot that allows users
 * to mark down their tasks.It allows users to add,
 * list, and manage tasks.
 */
public class Duke {

    private static final String DATA_FILE_PATH = "data/TaskList.txt"; //The file path of the .txt file
    private FileHandler fileHandler; //FileHandler to read or write file.
    private TaskList task; // The list of task.
    private Ui ui; //The user interface.

    /**
     * Constructs a Duke instance with the specified file path.
     *
     */
    public Duke() {
        this.fileHandler = new FileHandler(DATA_FILE_PATH);
        this.task = new TaskList(FileHandler.readTasksFromFile());
        this.ui = new Ui(task);
    }


    /**
     * Runs the duke.Duke application.
     * It displays a welcome message and processes user commands
     * until the 'bye' command is received to terminate the program.
     */
    public void run() {
        fileHandler = new FileHandler(DATA_FILE_PATH);
        task = new TaskList(FileHandler.readTasksFromFile());
        ui = new Ui(task);
    }

    /**
     * The main for the duke.Duke application.
     * Create a duke.Duke instance to run.
     *
     * @param args (not used in this application).
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(task, ui, fileHandler);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}

