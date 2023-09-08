package duke;

import duke.exception.DukeException;
import duke.processors.FileHandler;
import duke.processors.Parser;
import duke.processors.TaskList;
import duke.processors.Ui;

public class Duke {
    private final Ui UI;
    private final Parser PARSER;

    public Duke() {
        UI = new Ui();
        TaskList tasks = new TaskList(new FileHandler());
        PARSER = new Parser(tasks);
    }

    private void run() {
        UI.OnEnter();
        new FileHandler().fileCreate();
        while (!PARSER.getTerminate()) {
            try {
                String fullCommand = UI.readCommand();
                UI.showLine();
                PARSER.readInputs(fullCommand);
            } catch (DukeException e) {
                UI.showLine();
                UI.showError(e.getMessage());
            } finally {
                UI.showLine();
            }
        }
        UI.OnExit();
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
