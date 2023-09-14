package duke;
import java.io.FileNotFoundException;

/**
 * The Duke class represents a task management application that allows users to interact with tasks.
 * It provides methods to manage tasks, mark them as done, and perform various operations on tasks.
 */
public class Duke {
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs a Duke instance with the specified file path to load task data from.
     */
    public Duke() {
        this.ui = new Ui();
        String filePath = "./src/main/data/tasklist.txt";
        Storage storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String userInput) {
        Parser parser = new Parser();
        parser.setUserInput(userInput);
        try {
            if (parser.isBye()) {
                parser.goodbye();
                return ui.goodbye();
            } else if (parser.isList()) {
                return tasks.printFileContents();
            } else if (parser.isMark()) {
                return tasks.mark(userInput);
            } else if (parser.isUnmark()) {
                return tasks.unMark(userInput);
            } else if (parser.isDelete()) {
                return tasks.delete(userInput);
            } else if (parser.isTodo()) {
                return tasks.handleTodo(userInput);
            } else if (parser.isDeadline()) {
                return tasks.handleDeadline(userInput);
            } else if (parser.isEvent()) {
                return tasks.handleEvent(userInput);
            } else if (parser.isFind()) {
                return tasks.handleFind(userInput);
            } else {
                throw new DukeException("Error: Invalid Command!");
            }
        } catch (DukeException exception) {
            return ui.getLine() + exception.getMessage() + "\n" + ui.getLine();
        }
    }
}
