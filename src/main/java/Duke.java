import parser.Parser;

import storage.FileHandler;
import storage.TaskList;

import ui.Ui;

import command.Command;

/**
 * Duke class is a simple chatbot that allows users
 * to mark down their tasks.It allows users to add,
 * list, and manage tasks.
 */
public class Duke {
    private FileHandler fileHandler; //FileHandler to read or write file.
    private TaskList task; // The list of task.
    private Ui ui; //The user interface.
    private static final String DATA_FILE_PATH = "data/TaskList.txt"; //The file path of the .txt file

    /**
     * Construct a Duke instance with the specified file path.
     *
     * @param filePath The file path to store task data.
     */
    public Duke(String filePath) {
        this.fileHandler = new FileHandler(filePath);
        this.task = new TaskList(FileHandler.readTasksFromFile());
        this.ui = new Ui(task);
    }

    /**
     * Runs the Duke application.
     * It displays a welcome message and processes user commands
     * until the 'bye' command is received to terminate the program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(task, ui, fileHandler);
                isExit = c.isExit();
            } catch (Exception e) {
                // Handle the specific exception here
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * The main for the Duke application.
     * Create a Duke instance to run.
     *
     * @param args (not used in this application).
     */
    public static void main(String[] args) {
        new Duke(DATA_FILE_PATH).run();
    }
}

