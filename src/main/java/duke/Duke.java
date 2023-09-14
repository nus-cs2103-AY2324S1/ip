package duke;
import java.io.FileNotFoundException;

/**
 * The Duke class represents a task management application that allows users to interact with tasks.
 * It provides methods to manage tasks, mark them as done, and perform various operations on tasks.
 */
public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private final String filePath = "./src/main/data/tasklist.txt";

    /**
     * Constructs a Duke instance with the specified file path to load task data from.
     */
    public Duke() {
        this.ui = new Ui();
        Storage storage = new Storage(this.filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public String startChat(String userInput) {
        Parser parser = new Parser();
        parser.setUserInput(userInput);
        while (true) {
            try {
                if (parser.bye()) {
                    break;
                }
                if (parser.list()) {
                    return tasks.printFileContents();
                } else if (parser.mark()) {
                    return tasks.mark(userInput);
                } else if (parser.unMark()) {
                    return tasks.unMark(userInput);
                } else if (parser.delete()) {
                    return tasks.delete(userInput);
                } else if (parser.todo()) {
                    return tasks.handleTodo(userInput);
                } else if (parser.deadline()) {
                    return tasks.handleDeadline(userInput);
                } else if (parser.event()) {
                    return tasks.handleEvent(userInput);
                } else if (parser.find()) {
                    return tasks.handleFind(userInput);
                } else {
                    throw new DukeException("Error: Invalid Command!");
                }
            } catch (DukeException exception) {
                return Ui.line + exception.getMessage() + "\n" + Ui.line;
            }
        }
        ui.goodbye();
        parser.goodbye();
        return "";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return startChat(input);
    }
}
