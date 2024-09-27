package duke.main;

import duke.command.Command;

/**
 * The Duke class.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for the duke.main.Duke class.
     *
     * @param filePath The String representing the filePath of the file to be used.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    public Duke() {
        this("duke.txt");
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.showWelcomeMsg();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String result;
        try {
            Command command = Parser.parse(input);
            result = command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            result = e.getMessage();
        }
        return result;
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
