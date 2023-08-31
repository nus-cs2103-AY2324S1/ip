import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
public class Ballsorter {
    static String line = "____________________________________________________________";
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, HH:mm");
    TaskList taskList = new TaskList();
    Storage storage;
    Ui ui;
    File tmpDir;
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
    public void run() {
        System.out.println(line);
        System.out.println("Hello! I'm Ballsorter\nWhat can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        ui = new Ui(sc);
        ui.scan(taskList);

        //write list to storage
        storage.storeList(taskList);

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
    public static void main(String[] args) {
        new Ballsorter().run();
    }
}
