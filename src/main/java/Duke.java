import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final String line = "____________________________________________________________";
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            ArrayList<Task> loadedTasks = this.storage.loadTasks();
            this.tasks = new TaskList(loadedTasks);
        } catch (DukeException e) {
            this.ui.explainException(e);
        }
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        String nextTask = scanner.nextLine();
        CommandEnum taskEnum = CommandEnum.assignEnum(nextTask);

        while (!taskEnum.equals(CommandEnum.BYE)) {
            switch (taskEnum) {
            case LIST:
                this.tasks.printTasks();
                break;
            case MARK:
            case UNMARK:
                try {
                    this.tasks.handleMark(nextTask);
                } catch (DukeException e) {
                    this.ui.explainException(e);
                }
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                try {
                    this.tasks.handleTask(nextTask);
                } catch (DukeException e) {
                    this.ui.explainException(e);
                }
                break;
            case DELETE:
                try {
                    this.tasks.handleDelete(nextTask);
                } catch (DukeException e) {
                    this.ui.explainException(e);
                }
                break;
            default:
                this.ui.handleInvalid();
            }

            nextTask = scanner.nextLine();
            taskEnum = CommandEnum.assignEnum(nextTask);
        }

        scanner.close();

        try {
            this.storage.saveTasks(this.tasks.getList());
        } catch (IOException e) {
            System.out.println("Unable to save your tasks to a file. Try Again.");
        }

        this.ui.bye();
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.ui.greet();
        chatBot.run();
    }
}
