import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> items = new ArrayList<>();
    private static final String BAR = "____________________________________________________________";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    enum modifyStatus {
        MARK,
        UNMARK
    }

    enum taskType {
        EVENT,
        DEADLINE,
        TODO
    }
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

    public void run() {
        this.ui.greetUser();
        Scanner scanObj = new Scanner(System.in);
        while(true) {
            String nextLine = scanObj.nextLine();
            String firstWord = nextLine.split(" ")[0];
            try {
                switch(firstWord) {
                    case "bye":
                        this.ui.bidFarewell();
                        return;
                    case "list":
                        this.ui.printItems(this.tasks);
                        break;
                    case "unmark":
                        this.tasks.modifyStatus(modifyStatus.UNMARK, nextLine, this.ui);
                        break;
                    case "mark":
                        this.tasks.modifyStatus(modifyStatus.MARK, nextLine, this.ui);
                        break;
                    case "delete":
                        this.tasks.deleteItem(nextLine, this.ui);
                        break;
                    case "event":
                        this.tasks.addItem(taskType.EVENT, nextLine, this.ui);
                        break;
                    case "deadline":
                        this.tasks.addItem(taskType.DEADLINE, nextLine, this.ui);
                        break;
                    case "todo":
                        this.tasks.addItem(taskType.TODO, nextLine, this.ui);
                        break;
                    default:
                        throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                this.storage.write(this.tasks);
            } catch (DukeException e) {
                this.ui.printWrapped(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/data.ser").run();
    }
}
