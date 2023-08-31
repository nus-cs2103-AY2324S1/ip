import java.util.Scanner;
/**
 * The Duke class represents a simple chatbot application that helps manage tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.greet();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            Ui.printLine();
            try {
                Parser.executeCommand(command, tasks);
                storage.save(tasks);
            } catch (DukeException e) {
                Ui.showLoadingError(e);
            }
            Ui.printLine();
            command = scanner.nextLine();
        }
        scanner.close();
        Ui.goodbye();
    }

    /**
     * The main method to start the Duke chatbot.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}