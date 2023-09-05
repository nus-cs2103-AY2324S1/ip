package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main to class to handle duke operations
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor
     * @param filePath path to file
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.handleReadAllTasksFromFile());
        } catch (DukeException | IOException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Method to get the user input and run the respective methods.
     */
    public void run() {
        ui.greet();
        Scanner obj = new Scanner(System.in);
        while (true) {
            try {
                String userInput = obj.nextLine();

                if (userInput.equals("bye")) {
                    break;
                }

                String[] commandType = parser.handleUserInput(userInput);
                ui.print(handleCommand(commandType[0], commandType[1]));
                storage.handleChangesInFile(tasks.getTasks());
            } catch (DukeException | IOException | InvalidInputExpression e) {
                System.out.println(e.getMessage());
            }
        }
        ui.exit();
    }

    /**
     * Method to handle input
     * @param command command of user
     * @param input inout of user
     * @return String representing the process performed
     * @throws DukeException
     * @throws IOException
     */

    public String handleCommand(String command, String input) throws DukeException, IOException {
        switch (command) {
        case "mark":
            return tasks.markTask(input);
        case "unmark":
            return tasks.unmarkTask(input);
        case "list":
            return tasks.getAllToDo();
        case "todo":
            return tasks.handleTodoTask(input, "user");
        case "deadline":
            return tasks.handleDeadlineTask(input, "user");
        case "event":
            return tasks.handleEventTask(input, "user");
        case "delete":
            return tasks.deleteTask(input);
        case "find":
            return tasks.handleFindTask(input);
        default:
        }
        return "";
    }

    public static void main(String[] args) {
        new Duke("./src/data/duke.txt").run();
    }
}
