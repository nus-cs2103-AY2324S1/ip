import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    static List<Task> tasks = new ArrayList<>();

    private static void outputMessage(String message) {
        System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        outputMessage(" Hello! I'm Pixel\n What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String command = input.split(" ", 2)[0];
            switch (command) {
                case "bye":
                    outputMessage(" Bye. Hope to see you again soon!\n");
                    break;
                case "list":
                    outputMessage(tasks.stream().map(task -> task.printTask() + "\n").collect(Collectors.joining()));
                    break;
                case "mark": {
                    tasks.get(Integer.parseInt(input.split(" ", 2)[1]) - 1).markAsDone();
                    outputMessage(" Nice! I've marked this task as done:\n");
                    break;
                }
                case "unmark": {
                    tasks.get(Integer.parseInt(input.split(" ", 2)[1]) - 1).markAsUndone();
                    outputMessage(" OK, I've marked this task as not done yet:\n");
                    break;
                }
                default:
                    tasks.add(new Task(input));
                    outputMessage(String.format("added: %s\n", input));
                    break;
            }
        }
    }
}
