import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Ben {
    private static final String HORIZONTAL_LINE = "------------------------------------------";
    private boolean isActive = true;
    private final Scanner user = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void greeting() {
        System.out.println(HORIZONTAL_LINE + "\nWhat's up! I'm Ben\nWhat can I do for you?\n" + HORIZONTAL_LINE);
    }

    public void bye() {
        System.out.println(HORIZONTAL_LINE + "\nBye. For now\n" + HORIZONTAL_LINE);
    }

    public void echo(String message) {
        Task task = new Task(message);
        tasks.add(task);
        System.out.println(HORIZONTAL_LINE + "\n" + "added: " + task + "\n" + HORIZONTAL_LINE);
    }

    public void deactivate() {
        isActive = false;
    }

    public void listToString() {
        String message = "";
        for (int i = 1; i <= tasks.size(); i++) {
            message += i + ". " + tasks.get(i - 1).toString() + "\n";
        }
        System.out.println(HORIZONTAL_LINE + "\n" + message + HORIZONTAL_LINE);
    }

    public void run() {
        greeting();
        while (isActive) {
            String message = user.nextLine();
            if (Objects.equals(message.toLowerCase(), "bye")) {
                deactivate();
            } else if (Objects.equals(message.toLowerCase(), "list")) {
                listToString();
            } else {
                echo(message);
            }
        }
        bye();
    }

    public static void main(String[] args) {
        Ben ben = new Ben();
        ben.run();
    }
}


