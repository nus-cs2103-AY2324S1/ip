package duke;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.filePath = filePath;
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
            this.filePath = filePath;
        }
    }

    /**
     * Runs the chatbot by initiating the scanner and parser
     */
    public void run() {
        ui.showWelcomeMessage();

        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String input = in.nextLine();
                int number = tasks.num();
                Parser.parseInput(input, tasks, number, filePath, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}
