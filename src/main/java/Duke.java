import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String Start = "Hello! I'm Red \nWhat can I do for you?";
        System.out.println(Start);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();
            if(input.equals("bye"))
                break;

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i + 1 + "." + tasks[i]);
                }
                continue;
            }
            if (input.contains("unmark")) {
                int selected = Integer.parseInt(input.substring(7));
                Task selTask = tasks[selected - 1];
                selTask.taskNotCompleted();
                System.out.println("OK, I've marked this task as not done yet:\n" + selTask);
                continue;
            }

            if (input.contains("mark")) {
                int selected = Integer.parseInt(input.substring(5));
                Task selTask = tasks[selected - 1];
                selTask.taskCompleted();
                System.out.println("Nice! I've marked this task as done:\n" + selTask);
                continue;
            }

            tasks[taskCount] = new Task(input);
            taskCount++;
            System.out.println("added: " + input);

        }

        String End = "Bye. Hope to see you again soon!\n";
        System.out.println(End);
    }
}
