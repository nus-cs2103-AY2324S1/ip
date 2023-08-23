import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String Start = "Hello! I'm Red\nWhat can I do for you?";
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

            if (input.contains("deadline")) {
                String desc = input.substring(9 ,input.indexOf("/by") - 1);
                String by = input.substring(input.indexOf("/by") + 4);
                Task dl = new Deadline(desc, by);
                tasks[taskCount] = dl;
                taskCount++;

                System.out.println("Got it. I've added this task:\n" + dl +
                        "\nNow you have " + taskCount + " tasks in the list.");

                continue;
            }

            if (input.contains("event")) {
                String desc = input.substring(6 ,input.indexOf("/from") - 1);
                String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                String to = input.substring(input.indexOf("/to") + 4);
                Task event = new Event(desc, from, to);
                tasks[taskCount] = event;
                taskCount++;

                System.out.println("Got it. I've added this task:\n" + event +
                        "\nNow you have " + taskCount + " tasks in the list.");

                continue;
            }

            if (input.contains("todo")) {
                String desc = input.substring(input.indexOf("/todo") + 6);
                Task todo = new Todo(desc);
                tasks[taskCount] = todo;
                taskCount++;

                System.out.println("Got it. I've added this task:\n" + todo +
                        "\nNow you have " + taskCount + " tasks in the list.");

                continue;
            }

//            tasks[taskCount] = new Task(input);
//            taskCount++;
//            System.out.println("added: " + input);

        }

        String End = "Bye. Hope to see you again soon!";
        System.out.println(End);
    }
}
