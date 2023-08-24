import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void add(String message) {
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

    public void Mark(Task task) {
        task.Mark();
        System.out.println(HORIZONTAL_LINE + "\n" + "Nice! This task is completed\n" + task + "\n" + HORIZONTAL_LINE);
    }

    public void Unmark(Task task) {
        task.Unmark();
        System.out.println(HORIZONTAL_LINE + "\n" + "Okay! This task is not completed\n" + task + "\n" + HORIZONTAL_LINE);
    }

    public boolean isMarkCommand(String message) {
        Pattern pattern = Pattern.compile("(unmark|mark)\\s*(-?\\d+)");
        Matcher matcher = pattern.matcher(message.toLowerCase());

        if (matcher.find()) {
            // extract command
            String command = matcher.group(1);

            // extract task number
            String TaskNumber = matcher.group(2);
            int num = Integer.parseInt(TaskNumber) - 1;

            // check whether number is valid
            if (num < 0 || num >= tasks.size()) {
                System.out.println(HORIZONTAL_LINE + "\n" + "Please input a valid task number" + "\n" + HORIZONTAL_LINE);
                return true;
            }

            // if valid, mark or unmark the task
            if (Objects.equals(command, "mark")) {
                Mark(tasks.get(num));
            } else {
                Unmark(tasks.get(num));
            }
            return true;
        }
        return false;
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
                if (!isMarkCommand(message)) {
                    add(message);
                }
            }
        }
        bye();
    }

    public static void main(String[] args) {
        Ben ben = new Ben();
        ben.run();
    }
}


