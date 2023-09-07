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
    private boolean isTerminated;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.isTerminated = false;
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Constructor for Duke with specified filePath.
     * @param filePath
     */
    public Duke(String filePath) {
        this.isTerminated = false;
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.print(init());

        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()) {
            try {
                String response = input.nextLine();
                ui.print(process(response));
                if (isTerminated) {
                    break;
                }
            } catch (DukeException e) {
                ui.printError(e.toString());
            }
        }
        input.close();
    }

    public String init() {
        try {
            tasks = storage.read();
            return ui.hello();
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public String process(String input) throws DukeException {
        Command command = Parser.parse(input);
        isTerminated = command.isExit();
        String res = command.execute(tasks, ui);
        storage.write(tasks);
        return res;
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public boolean isTerminated() {
        return isTerminated;
    }
}
