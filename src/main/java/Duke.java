import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        List<Task> taskList = new ArrayList<>();

        String initMsg = "____________________________________________________________\n"
                + " Hello! I'm IRIS\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(initMsg);

        input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            String[] inputParts = input.split(" ", 3);
            String command = inputParts[0];

            if (input.equals("list")) {
                int count = 1;
                for (Task task : taskList) {
                    System.out.println(count++ + ". " + task);
                }
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(inputParts[1]) - 1;
                taskList.get(index).markDone();
                System.out.println("Nice! I've marked this task as done:\n"
                + taskList.get(index));
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(inputParts[1]) - 1;
                taskList.get(index).markUndone();
                System.out.println("OK, I've marked this task as not done yet:\n"
                        + taskList.get(index));
            } else {
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("Understood sir, I've added the task below:\n"
                + newTask);
            }
            System.out.println("____________________________________________________________");
            input = scanner.nextLine();
        }

        scanner.close();
        String endMsg = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(endMsg);
    }
}