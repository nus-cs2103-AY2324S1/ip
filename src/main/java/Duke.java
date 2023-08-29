import java.io.IOException;
import java.io.File;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Auntie Maggie " +
                "\nWhat can I do for you?");
        Parser p = new Parser();
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage("./data/duke.txt");
        File dataDir = new File("./data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        String s = sc.nextLine();
        ArrayList<SingleTask> taskList = new ArrayList<>();
        try {
            taskList = storage.loadTasks();
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
            taskList = new ArrayList<>();
        }
        while (!s.equals("bye")) {
            try {
                p.parse(s, taskList);
                s = sc.nextLine();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                s = sc.nextLine();
            }
        }
        if (s.equals("bye")) {
            try {
                storage.saveTasks(taskList);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Bye! Auntie maggie see you later!");
        }
    }
}

