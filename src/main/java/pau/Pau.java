package pau;

import pau.util.Parser;
import pau.util.Storage;
import pau.task.TaskList;
import pau.util.Ui;

import java.util.Scanner;

/**
 * Represents the Pau chatbot.
 */
public class Pau {

    /**
     * The list of tasks.
     */
    private TaskList list;
    /**
     * The storage that handles loading and saving of tasks.
     */
    private Storage storage;
    /**
     * The ui that handles interaction with users.
     */
    private Ui ui;

    /**
     * Constructs a new Pau chatbot.
     *
     * @param filePath The path to the file containing tasks to be loaded.
     */
    public Pau(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        this.ui.introduction();
        Scanner scan = new Scanner(System.in);
        this.list = this.storage.loadTasks();
        list.checkList();
        String input = scan.nextLine();

        while (!(input.equals("bye"))) {
            Parser.parseCommand(input, list);
            this.storage.saveTasksToFile(list);
            input = scan.nextLine();
        }
        this.ui.exit();
    }

    public static void main(String[] args) {
        new Pau("./data/paulist.txt").run();
    }
}
