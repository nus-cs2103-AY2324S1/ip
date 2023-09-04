package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The backbone of the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Constructor for Duke with specified filePath.
     * @param filePath
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Runs the program.
     */
    public void run() {
        try {
            tasks = storage.read();
        } catch (DukeException e) {
            ui.printError(e.toString());
        }

        Scanner input = new Scanner(System.in);
        ui.hello();

        while (input.hasNextLine()) {
            try {
                String response = input.nextLine();
                Command command = Parser.parse(response);
                command.execute(tasks, ui);
                if (command.isExit()) {
                    break;
                }
            } catch (DukeException e) {
                ui.printError(e.toString());
            }
        }

        try {
            storage.write(tasks);
        } catch (DukeException e) {
            ui.printError(e.toString());
        }

        input.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
