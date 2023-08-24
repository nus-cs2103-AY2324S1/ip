import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[100];
        int taskCount = 0;

        String introduction = "____________________________________________________________\n" +
                " Hello! I'm eggbot\n" +
                " Please add a task!\n\n" +
                " To view tasks, type 'list' \n" +
                " To exit, type 'bye' \n" +
                "____________________________________________________________\n";

        System.out.println(introduction);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please add a task, or type list to show tasks.");

            String input = scanner.nextLine();  // Read input

            if (Objects.equals(input, "bye")) {
                String output = "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon! \n" +
                        "____________________________________________________________\n";

                System.out.println(output);
                break;
            } else if (Objects.equals(input, "list")) {

                if (taskCount == 0) {
                    System.out.println("You have no tasks!");

                    continue;
                }
                String output = "____________________________________________________________\n" +
                        "Your tasks: \n";

                System.out.println(output);

                for (int i = 0; i < taskCount; i++) {
                    System.out.println(tasks[i]);
                }

                System.out.println("____________________________________________________________\n");

                continue;
            }

            tasks[taskCount] = input;
            taskCount += 1;

            String output = "____________________________________________________________\n" +
                    "added: " + input + "\n" +
                    "____________________________________________________________\n";

            System.out.println(output);
        }

    }
}
