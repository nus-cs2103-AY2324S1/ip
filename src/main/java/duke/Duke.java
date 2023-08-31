package duke;

import taskstuff.TaskList;

import storagestuff.Storage;

import userstuff.Ui;

import instructionstuff.Instruction;

/**
 * Main class of duke.Duke bot.
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

    /** The ui.Ui object which handles the ui of this chatbot. */
    private Ui ui;


    /**
     * Initialises filepath field to given parameter.
     *
     * @param filePath String representing filepath of datafile.
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
        try {
            this.storage = new Storage(this.filePath);
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException d) {
            ui.showMessage(d.getMessage());
            return;
        }
    }


    /**
     * Handles input from the user accordingly.
     *
     */
    public void run() {
        this.ui.showWelcome();
        this.ui.showMessage("");
        String userInput = ui.readCommand();
        this.ui.showLine();
        Instruction instruction = null;
        while (true) {
            try {
                instruction = Parser.parse(userInput);
                instruction.execute(this.storage, this.taskList, this.ui);

            } catch (DukeException d) {
                this.ui.showMessage(d.getMessage());
            }
            if (instruction instanceof Instruction.Exit) {
                return;
            }
            this.ui.showLine();
            this.ui.showMessage("");
            userInput = this.ui.readCommand();
            this.ui.showLine();

        }

    }

    /**
     * Greets and calls run and finally says goodbye.
     *
     * @param args Not used currently.
     */
    public static void main(String[] args) {

        String filePath = "./data/duke.Duke.txt";
        Duke duke = new Duke(filePath);
        duke.run();

    }
}
