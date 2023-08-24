import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        // an array list of tasks
        List<Task> tasks = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        printHorizontalLine();
        System.out.println("\t " + "Hello! I'm Buddy! \n" +
               "\t " + "What can I do for you? ");
        printHorizontalLine();
        int count = 0;
        while (count <=100 ) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("\t " + "Bye! Hope to see you again soon! ");
                printHorizontalLine();
                break;
            }

            //marking task
            else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5));
                tasks.get(taskIndex - 1).markAsDone();
                printHorizontalLine();
                System.out.println("\tGreat! I've marked this task as done:");
                System.out.println("\t" + taskIndex + ".[" + tasks.get(taskIndex - 1).getStatusIcon() + "] "
                        + tasks.get(taskIndex - 1).description);
                printHorizontalLine();

            }

            //unmarking task
            else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7));
                tasks.get(taskIndex - 1).markAsNotDone();
                printHorizontalLine();
                System.out.println("\tOk! I've marked this task as not done yet:");
                System.out.println("\t" + taskIndex + ".[" + tasks.get(taskIndex - 1).getStatusIcon() + "] "
                        + tasks.get(taskIndex - 1).description);
                printHorizontalLine();

            }

            //displaying list
            else if (input.equals("list")) {
                printHorizontalLine();
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 1; i <= count; i++) {
                    System.out.println("\t" + i + "." + "[" + tasks.get(i - 1).getStatusIcon() + "] "
                            + tasks.get(i - 1).getDescription());
                }
                printHorizontalLine();
            }

            // adding task to list
            else {
                printHorizontalLine();
                System.out.println("\t" + "added: " + input);
                tasks.add(new Task(input));
                count++;
                printHorizontalLine();
            }
        }

        }

    public static void printHorizontalLine() {
        System.out.println("    ________________________________________________");
    }


}
