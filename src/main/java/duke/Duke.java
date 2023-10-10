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
     * Returns the goodbye message.
     */
    public String goodbye() {
        return Ui.getGoodbyeMessage();
    }

    /**
     * Returns the bot's response to the GUI.
     * @param input the user's input
     * @return the bot's response
     */
    public String getResponse(String input) {
        return Parser.parseCommand(input, this.taskList);
    }

}
