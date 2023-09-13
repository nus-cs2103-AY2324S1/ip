package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

import javafx.application.Application;



/**
 * A chatbot inspired by the Java Mascot: duke.Duke.
 */
public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    private String dukeCurrOutput;
    Parser parser;

    /**
     * Constructor for duke.Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.parser = new Parser();
        storage.load(this.tasks);
        assert this.ui != null : "UI cannot be a null value";
    }



    /**
     * This runs the duke programs main chat functionality,
     * the while loop continues until user types bye.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            String stringToPrint = parser.parseCommand(command, tasks);
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public String getCommandResponse(String command) {
        return parser.parseCommand(command, tasks);
    }



    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
