import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;
    private final Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        new Duke("./src/main/data/tasklist.txt").startChat();
    }

    public void startChat() {
        ui.greet();
        String userInput = parser.getUserInput();

        while (!parser.bye()){
            try {
                if (parser.list()){
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
        }

        ui.goodbye();
        parser.goodbye();
    }
}
