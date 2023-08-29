package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeDatabaseException;
import duke.exception.EmptyCommandException;
import duke.task.TaskList;

public class Duke {
    private static final String DATAPATH = "./data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.storage.loadData());
        } catch (DukeDatabaseException e) {
            this.ui.showDukeException(e);
        }
    }

    private void run() {
        this.ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.readInput();

                if (userInput.trim().isEmpty()) {
                    throw new EmptyCommandException();
                }

                Command command = Parser.parseCommand(userInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();

            } catch (DukeException e) {
                ui.showDukeException(e);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                ui.showArrayIndexOutOfBoundsException();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(DATAPATH).run();
    }
}