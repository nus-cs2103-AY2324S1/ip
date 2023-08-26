import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[100];
        Boolean[] doneness = new Boolean[100];
        int taskCount = 0;

        String introduction = "____________________________________________________________\n" +
                " Hello! I'm eggbot\n" +
                " Please add a task!\n\n" +
                " To view tasks, type 'list' \n" +
                " To mark a task as 'done', type 'mark [index]' \n" +
                " To mark a task as 'undone', type 'unmark [index]' \n" +
                " To exit, type 'bye' \n" +
                "____________________________________________________________\n";

        System.out.println(introduction);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please add a task, or type list to show tasks.");

            String input = scanner.nextLine().strip();  // Read input

            if (Objects.equals(input, "bye")) {
                String output = "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon! \n" +
                        "____________________________________________________________\n";

                System.out.println(output);
                break;
            } else if (Objects.equals(input, "list")) {

                if (taskCount == 0) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("You have no tasks!");
                    System.out.println("____________________________________________________________\n");

                    continue;
                }
                
                String output = "____________________________________________________________\n" +
                        "Your tasks: \n";

                System.out.println(output);

                for (int i = 0; i < taskCount; i++) {
                    String entryOutput = (i + 1) + ". [ ] " + tasks[i];
                    if (doneness[i]) {
                        entryOutput = (i + 1) + ". [X] " + tasks[i];
                    }
                    System.out.println(entryOutput);
                }

                System.out.println("____________________________________________________________\n");

            } else if (input.startsWith("mark")) {
                if (input.equals("mark")) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("You cannot mark an empty task!");
                    System.out.println("____________________________________________________________\n");

                    continue;
                }
                int index = Integer.parseInt(input.substring(input.length() - 1)) - 1;

                if (index >= taskCount) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("Task " + (index + 1) + " not found!");
                    System.out.println("____________________________________________________________\n");

                    continue;
                }
                doneness[index] = true;

                String output = "Nice! I've marked this task as done: \n" +
                        "[X] " + tasks[index];

                System.out.println("____________________________________________________________\n");
                System.out.println(output);
                System.out.println("____________________________________________________________\n");

            } else if (input.startsWith("unmark")) {
                if (input.equals("unmark")) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("You cannot unmark an empty task!");
                    System.out.println("____________________________________________________________\n");

                    continue;
                }

                int index = Integer.parseInt(input.substring(input.length() - 1)) - 1;

                if (index >= taskCount) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("Task " + (index + 1) + " not found!");
                    System.out.println("____________________________________________________________\n");

                    continue;
                }
                doneness[index] = false;

                String output = "OK, I've marked this task as not done yet: \n" +
                        "[ ] " + tasks[index];

                System.out.println("____________________________________________________________\n");
                System.out.println(output);
                System.out.println("____________________________________________________________\n");
            } else {
                tasks[taskCount] = input;
                doneness[taskCount] = false;
                taskCount += 1;

                String output = "____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________\n";

                System.out.println(output);
            }
        }

    }
}
