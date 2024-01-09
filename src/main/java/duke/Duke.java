package duke;

import duke.exception.DukeException;
import duke.processors.FileHandler;
import duke.processors.Parser;
import duke.processors.TaskList;
import duke.processors.Ui;

/**
 * Application class.
 */
public class Duke {
    private final Parser PARSER;

    /**
     * A constructor for duke.
     */
    public Duke() {
        TaskList tasks = new TaskList(new FileHandler());
        PARSER = new Parser(tasks);
    }


    /**
     * Return responses after executing the commands.
     *
     * @param input the command provided by the user.
     * @return a String contains the corresponding response to the user.
     */
    public String getResponse(String input) {
        try {
            assert !input.isEmpty() : "input is empty";
            return PARSER.readInputs(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
