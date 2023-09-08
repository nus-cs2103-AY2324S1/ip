package duke;

import duke.command.Command;

/**
 * A class that represents the whole program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor for the Duke program.
     * @param filePath the path to database of the program.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * A method to start the program.
     */
    public void run() {
        this.ui.greeting();
        boolean isExit = false;

        while (!isExit) {
            String userInput = ui.readCommand();
            try {
                // basic user input processing
                Command c = Parser.parse(userInput);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        ui.ending();
    }

    /**
     * A method that gets response from user and returns program's response.
     * @param input string that user inputted.
     * @return string to be displayed on GUI based on user's command.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Method to start the duke program.
     * @param args list of arguments passed.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
