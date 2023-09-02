package duke;

import duke.command.Command;
import duke.exception.DukeDatabaseNotFoundException;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * The main class for the Duke task management application.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke, which instantiates the ui, storage and taskList.
     *
     * @param filePath The specified filePath of the database.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadData());
        } catch (DukeDatabaseNotFoundException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Runs the Duke chat application.
     */
    public void run() {
        this.ui.showGreetMessage();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            try {
                String userInput = scanner.nextLine();
                Command c = Parser.parseUserInput(userInput);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
