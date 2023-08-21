import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String greeting = HORIZONTAL_LINE + " Hello! I'm Pixel\n What can I do for you?\n" + HORIZONTAL_LINE;
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(HORIZONTAL_LINE + " Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE);
            } else if (input.equals("list")) {
                System.out.println(HORIZONTAL_LINE);
                for (Task task : tasks) {
                    System.out.println(task.printTask());
                }
                System.out.println(HORIZONTAL_LINE);
            } else if (input.split(" ", 2)[0].equals("mark")) {
                Task task = tasks.get(Integer.parseInt(input.split(" ", 2)[1]) - 1);
                task.markAsDone();
                System.out.println(HORIZONTAL_LINE + " Nice! I've marked this task as done:\n" +
                        task.printTask() + "\n" + HORIZONTAL_LINE);
            } else if (input.split(" ", 2)[0].equals("unmark")) {
                Task task = tasks.get(Integer.parseInt(input.split(" ", 2)[1]) - 1);
                task.markAsUndone();
                System.out.println(HORIZONTAL_LINE + " OK, I've marked this task as not done yet:\n" +
                        task.printTask() + "\n" + HORIZONTAL_LINE);
            } else {
                tasks.add(new Task(input));
                System.out.println(HORIZONTAL_LINE + "added: " + input + "\n" + HORIZONTAL_LINE);
            }
        }
    }
}
