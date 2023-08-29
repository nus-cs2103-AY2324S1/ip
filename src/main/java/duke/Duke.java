package duke;

import duke.command.Command;

/**
 * Shows the chat bot, Rion.
 */
public class Duke {
    /** Storage object that deals with loading and saving tasks. */
    private Storage storage;
    /** The unique task list containing all tasks inputted. */
    private TaskList taskList;
    /** The Ui that deals with interactions with the users. */
    private Ui ui;

    /**
     * Creates the chatbot with a specific file path.
     * It checks whether there is a cached version of the list.
     * The resulting task list will then be saved at the path.
     * 
     * @param filePath Path for the task list to be stored in.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();

        try {
            taskList = new TaskList(storage.loadList());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Runs the chatbot, Rion.
     * It receives commands and executes them accordingly.
     */
    private void runRion() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.nextCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    
    public static void main(String[] args) {
        Duke rion = new Duke("./data/tasks.txt");
        rion.runRion();
    }
}
