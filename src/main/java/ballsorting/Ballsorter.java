package ballsorting;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class Ballsorter {
    static String LINE = "____________________________________________________________";
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, HH:mm");
    private TaskList taskList = new TaskList();
    private Storage storage;
    private Ui ui;
    private File tmpDir;

    /**
     * Creates a new instance of the chatbot.
     */
    public Ballsorter() {
        tmpDir = new File("data/Ballsorter.txt");
        //check for storage
        storage = new Storage(tmpDir);
        try {
            if (tmpDir.createNewFile()) {
                taskList = new TaskList();
            } else {
                storage.loadFile(taskList);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Creates a new instance of the chatbot.
     * @param filePath Takes in a filePath String that stores the chatbot information.
     */
    public Ballsorter(String filePath) {
        tmpDir = new File(filePath);
        //check for storage
        storage = new Storage(tmpDir);
        try {
            if (tmpDir.createNewFile()) {
                taskList = new TaskList();
            } else {
                storage.loadFile(taskList);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Starts the chatbot.
     */
    public void run() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Ballsorter\nWhat can I do for you?");
        System.out.println(LINE);

        Scanner sc = new Scanner(System.in);
        ui = new Ui(sc);
        ui.scan(taskList);

        //write list to storage
        storage.storeList(taskList);

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
    public static void main(String[] args) {
        new Ballsorter().run();
    }
}
