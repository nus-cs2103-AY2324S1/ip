import javax.management.DynamicMBean;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /**
     * The file path for storing and retrieving tasks. */
    private static final String DATA_FILE_PATH = "./data/duke.txt";

    private final Storage storage;

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
    }

    public void run() {
        // Welcome
        Ui.showWelcome();

        // Creates data folder if doesn't exist
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            if (dataDirectory.mkdir()) {
                System.out.println("        Data directory created successfully.");
            } else {
                System.out.println("        Failed to create data directory.");
            }
            Ui.showLine();
        }

        // Check user input
        Scanner sc = new Scanner(System.in);
        List<Task> userList = new ArrayList<>();
        TaskList taskList = new TaskList(userList);

        this.storage.loadTasks(taskList); // Load tasks to list

        while (true) {
            String userInput = sc.nextLine();
            Parser.parseUserInput(userInput, taskList, storage);
        }
    }

    public static void main(String[] args) {
        new Duke(DATA_FILE_PATH).run();
    }
}