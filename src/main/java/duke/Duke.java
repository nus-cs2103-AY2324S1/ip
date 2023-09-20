package duke;

import command.Command;
import parser.Parser;
import storage.FileHandler;
import storage.TaskList;

/**
 * Duke is a simple chatbot that allows users
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
     */
    public Duke() {
        this.fileHandler = new FileHandler(DATA_FILE_PATH);
        this.task = new TaskList(FileHandler.readTasksFromFile());
        this.ui = new Ui(task);
    }


    /**
     * Does not perform any action for now.
     */
    public void run() {
    }

    /**
     * Creates a Duke instance to run.
     *
     * @param args (not used in this application).
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Processes the user's input to generate a response.
     *
     * @param input The user's input to be processed.
     * @return A response generated based on the user's input.
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

