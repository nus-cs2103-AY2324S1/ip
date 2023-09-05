package duke;

import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Gui;
import duke.ui.Ui;
import javafx.application.Application;

/**
 * The main class of the program.
 */
public class Duke {

    private static final String BOT_NAME = "Snake CYQJ";
    private final TaskList taskList = new TaskList("duke.txt");
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.ui = new Ui(BOT_NAME);
    }

    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }

    /**
     * Returns the hello message.
     * @return the hello message
     */
    public String greet() {
        return this.ui.getHelloMessage();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return Parser.parseCommand(input, this.taskList);
    }

}
