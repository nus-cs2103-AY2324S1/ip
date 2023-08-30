import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Ben {
    public static final String HORIZONTAL_LINE = "------------------------------------------";
    private boolean isActive = true;
    private final Scanner user = new Scanner(System.in);
    private final TaskList tasks = new TaskList();
    private final Storage storage;

    public Ben(String filePath) {
        File f = new File(filePath);
        storage = new Storage(f);
    }

    public void greeting() {
        System.out.println(HORIZONTAL_LINE + "\nWhat's up! I'm Ben\nWhat can I do for you?\n" + HORIZONTAL_LINE);
    }

    public void bye() {
        System.out.println(HORIZONTAL_LINE + "\nBye. For now\n" + HORIZONTAL_LINE);
    }

    public void deactivate() {
        isActive = false;
    }

    public void run() {

        // Load tasks from data.txt
        try {
            storage.loadTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        greeting();

        Parser parser = new Parser();
        while (isActive) {
            String message = user.nextLine();
            if (Objects.equals(message.toLowerCase(), "bye")) {
                deactivate();
            } else if (Objects.equals(message.toLowerCase(), "list")) {
                System.out.println(HORIZONTAL_LINE + "\n" + tasks + HORIZONTAL_LINE);
            } else {
                if (!parser.isEditListCommand(message, tasks)) {
                    parser.commandParser(message, tasks);
                }
            }
        }

        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            bye();
        }
    }

    public static void main(String[] args) {
        Ben ben = new Ben("src/main/java/data/ben.txt");
        ben.run();
    }
}


