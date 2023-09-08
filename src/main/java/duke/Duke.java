package duke;

import instructionstuff.Instruction;
import javafx.application.Application;
import storagestuff.Storage;
import taskstuff.TaskList;
import userstuff.MainWindow;
import userstuff.UiLauncher;

/**
 * Main class of Duke bot.
 * This class performs simple input and output handling and calls
 * appropriate functions from other classes.
 */
public class Duke {

    /** The storage object that handles storing of user data. */
    private Storage storage;

    /** The taskList object representing the list of tasks. */
    private TaskList taskList;

    /** String representing filepath of data file. */
    private String filePath;

    /** The MainWindow object which handles the ui of this chatbot. */
    private MainWindow mainWindow;


    /**
     * Initialises all the field variables in this class.
     * Initialises filepath and constructs Ui instance.
     * Tries to initialise storage.
     * If an error occurs, it returns.
     *
     * @param filePath String representing filepath of datafile.
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        try {
            this.storage = new Storage(this.filePath);
            this.taskList = new TaskList(this.storage.load());
            UiLauncher.set(this, "");
        } catch (DukeException d) {
            UiLauncher.set(this, d.getMessage() + "\n");
        }

        Application.launch(UiLauncher.class);


    }

    public void setUi(MainWindow ui) {
        this.mainWindow = ui;
    }


    /**
     * Handles input from the user accordingly.
     *
     */
    public void run(String userInput) {
        Instruction instruction = null;
        try {
            instruction = Parser.parse(userInput);
            instruction.execute(this.storage, this.taskList, this.mainWindow);

        } catch (DukeException d) {
            this.mainWindow.setMessage(d.getMessage());
        }
    }

    /**
     * Greets and calls run and finally says goodbye.
     *
     * @param args Not used currently.
     */
    public static void main(String[] args) {

        String filePath = "./data/Duke.txt";
        Duke duke = new Duke(filePath);


    }
}
