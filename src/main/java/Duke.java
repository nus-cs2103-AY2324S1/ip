import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
            if (tasks.taskArray.isEmpty()) {
                Ui.taskListEmpty();
            } else {
                Ui.taskListNotEmpty(this.tasks.taskArray);
            }
            Ui.horizontalLine();
        } catch (DukeException e) {
            Ui.showExceptionError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            Parser parser = new Parser(userInput, this.tasks, this.storage);
            parser.parse();
            if (parser.isBye()) {
                break;
            }
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}