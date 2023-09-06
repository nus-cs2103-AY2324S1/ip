package dukepackage;

import java.util.Scanner;

import toolpackage.Parser;
import toolpackage.Storage;
import toolpackage.TaskList;
import toolpackage.Ui;

/**
 * Represents a Duke bot. A Duke object contains
 * its own storage, task lists and ui.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs the respective components to
     * initialise Duke, namely the UI, Storage,
     * and TaskList.
     */
    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);

            boolean isCreated = this.storage.createStorage();
            if (!isCreated) {
                tasks = new TaskList(storage.load());
            } else {
                ui.showLoadingError();
                tasks = new TaskList();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs a loop to listen for commands by user.
     * This function is used for the CLI UI.
     */
    private void run() {
        this.ui.showWelcome();
        Scanner inputs = new Scanner(System.in);

        String response;
        while (true) {
            String command = this.ui.readCommands(inputs);
            try {
                response = Parser.parse(command, this.tasks, this.ui);
                this.storage.saveStorage(this.tasks);
                if (response.equals("bye")) {
                    break;
                }
                System.out.println(response);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        inputs.close();
        this.ui.showBye();
    }

    /**
     * Parses the input by user and returns a response.
     * Also saves list of tasks into the storage file.
     * This function is used for the JavaFX GUI.
     *
     * @param command Input from user.
     * @return String Response from Duke.
     */
    public String getResponse(String command) {
        try {
            String response = Parser.parse(command, this.tasks, this.ui);
            this.storage.saveStorage(this.tasks);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}