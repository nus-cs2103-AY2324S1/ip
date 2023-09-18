package pau;

import java.util.Scanner;

import pau.task.TaskList;
import pau.ui.Ui;
import pau.util.Parser;
import pau.util.Storage;


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
    public Pau(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.list = storage.loadTasks();
        assert this.list != null : "Pau should create a task list for users";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output = Parser.parseCommand(input, list);
        storage.saveTasksToFile(list);
        return output;
    }

    public String introduce() {
        return ui.introduction() + list.checkList();
    }

    public String sayGoodbye() {
        return ui.exit();
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        System.out.println(this.ui.introduction());
        Scanner scan = new Scanner(System.in);
        System.out.println(list.checkList());
        String input = scan.nextLine();

        while (!(input.equals("bye"))) {
            System.out.println(Parser.parseCommand(input, list));
            this.storage.saveTasksToFile(list);
            input = scan.nextLine();
        }
        this.ui.exit();
    }

    public static void main(String[] args) {
        new Pau("./data/paulist.txt").run();
    }

}
