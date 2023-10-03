package duke;

import java.util.Scanner;

import duke.helper.DukeException;
import duke.helper.Parser;
import duke.helper.Ui;
import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * Duke class
 */
public class Duke {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final String FILEPATH = "./data/tasks.txt";

    private TaskList taskList;

    /**
    * Constructs the Duke class.
    *
    * @param path the path to store the user's task list
    */
    public Duke(String path) {
        Storage.setPath(path);
        try {
            taskList = new TaskList(Storage.load());
        } catch (DukeException e) {
            Ui.print("Unable to load tasks");
            taskList = new TaskList();
        } finally {
            Parser.setTaskList(taskList);
        }
    }

    /**
    * Get chatbot responce for user input.
    *
    * @param input the user input
    * @return the chatbot's response
    */
    public String getResponse(String input) {
        try {
            String response = Parser.parse(input);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
    * Greet method for Duke.
    *
    * @return the chatbot's response
    */
    public String greet() {
        return Ui.greet();
    }

    /**
    * Main method for the chatbot. Initiate a Duke-Max instance.
    *
    * @param args arguments
    */
    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt");
        Ui.greet();
        String input = SCANNER.nextLine();

        while (!input.equals("bye")) {
            try {
                Parser.parse(input);
            } catch (DukeException e) {
                Ui.print(e.toString());
            } finally {
                input = SCANNER.nextLine();
            }
        }
        Ui.exit();
        SCANNER.close();
    }
}
