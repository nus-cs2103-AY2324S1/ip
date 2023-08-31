package duke;
import java.io.FileNotFoundException;

public class Duke {
    private final Ui ui;
    private TaskList tasks;

    public Duke(String filePath) {
        this.ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }

    }

    public static void main(String[] args) {
        new Duke("./src/main/data/tasklist.txt").startChat();
    }

    public void startChat() {
        ui.greet();
        Parser parser = new Parser();
        String userInput = parser.getUserInput();
        parser.setUserInput(userInput);
        while (true) {
            try {
                if (parser.bye()) {
                    break;
                }
                if (parser.list()) {
                    tasks.printFileContents();
                } else if (parser.mark()) {
                    tasks.mark(userInput);
                } else if (parser.unMark()) {
                    tasks.unMark(userInput);
                } else if (parser.delete()) {
                    tasks.delete(userInput);
                } else if (parser.todo()) {
                    tasks.handleTodo(userInput);
                } else if (parser.deadline()) {
                    tasks.handleDeadline(userInput);
                } else if (parser.event()) {
                    tasks.handleEvent(userInput);
                } else {
                    throw new DukeException("Error: Invalid Command!");
                }
            } catch (DukeException exception) {
                System.out.println(Ui.line + exception.getMessage() + "\n" + Ui.line);
            }
            userInput = parser.getUserInput();
            parser.setUserInput(userInput);
        }
        ui.goodbye();
        parser.goodbye();
    }
}
