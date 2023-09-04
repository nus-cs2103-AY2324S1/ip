package duke;

import duke.exception.KoraException;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private boolean isExit = false;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        try {
            storage.loadTask(taskList);
        } catch (KoraException e) {
            System.out.println(e.getMessage());
        }
    }

    public Command getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            command.execute(taskList);
            System.out.println(command.getCommandMessage());
            return command;
        } catch (KoraException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void run() {
        ui.greet();
        while (!isExit) {
                String userInput = ui.getUserInput();
                Command command = getResponse(userInput);
                if (command == null) {
                    isExit = false;
                } else {
                    isExit = command.isExitYet();
                }
        }
        ui.closeScanner();
    }
    public static void main(String[] args) {
        Duke kora = new Duke("./data/savedtask.txt");
        kora.run();
    }
}
