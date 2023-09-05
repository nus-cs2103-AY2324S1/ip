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

    /**
     * Constructor for duke.Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();
        storage.load(this.tasks);
    }



    /**
     * This runs the duke programs main chat functionality,
     * the while loop continues until user types bye.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();

        while (true) {
            String command = sc.nextLine();
            boolean isBye = parser.parseCommand(command, tasks);
            if (isBye) {
                break;
            }
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
//        parser.parseCommand(command, tasks);
    }



    public static void main(String[] args) {
//        bob.ui.printStart();
//        bob.run();
//        bob.ui.printEnd();
        Application.launch(Main.class, args);

    }
}
