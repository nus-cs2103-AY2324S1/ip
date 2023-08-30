
import java.util.ArrayList;
public class Duke {


    private static ArrayList<Task> tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.readTasks();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new ArrayList<>();
        }
    }

    public void run() {
        InputParser.parse(tasks);
    }
    public static void main(String[] args) throws DukeException {

        System.out.println("Hello! I'm Mikhil" + '\n' + "What can I do for you");
        new Duke("tasks.txt").run();

        }
    }

