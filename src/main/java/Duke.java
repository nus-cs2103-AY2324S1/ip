import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/data/tasklist.txt").startChat();
    }

    public void startChat() {
        ui.greet();

        int taskCount = 0;
        int taskId = 1;

        String userInput= parser.getUserInput();

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
                    String nameOfTask = userInput.substring(5);
                    ToDos task = new ToDos(nameOfTask);
                    tasks.addToList(task, taskCount);
                } else if (parser.deadline()) {
                    String[] parts = userInput.split("/by ");
                    String nameOfTask = parts[0].trim().substring(9);
                    try {
                        LocalDate deadline = LocalDate.parse(parts[1].trim());
                        Deadlines task = new Deadlines(nameOfTask, deadline);
                        tasks.addToList(task, taskCount);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid Date Format! Follow: YYYY-MM-DD");
                    }
                } else if (parser.event()) {
                    String[] taskAndTime = userInput.split("/from ");
                    String[] fromAndTo = taskAndTime[1].split("/to ");
                    try {
                        LocalDate start = LocalDate.parse(fromAndTo[0].trim());
                        LocalDate end = LocalDate.parse(fromAndTo[1].trim());
                        String nameOfTask = taskAndTime[0].trim().substring(6);
                        Events task = new Events(nameOfTask, start, end);
                        tasks.addToList(task, taskCount);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid Date Format! Follow: YYYY-MM-DD");
                    }
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
