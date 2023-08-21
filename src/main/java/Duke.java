import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Encapsulates the logic of a Chat bot
 *
 * @author Rayson
 */
public class Duke {

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        String LINE = "_______________________________________";
        String logo = "                     _                 _      \n" +
                " _ __ ___  ___ _ __ (_)_ __ ___  _ __ (_)_  __\n" +
                "| '__/ _ \\/ __| '_ \\| | '__/ _ \\| '_ \\| \\ \\/ /\n" +
                "| | |  __/\\__ \\ |_) | | | | (_) | | | | |>  < \n" +
                "|_|  \\___||___/ .__/|_|_|  \\___/|_| |_|_/_/\\_\\\n" +
                "              |_|                             ";

        Scanner scanner = new Scanner(System.in);
        System.out.println(LINE);
        System.out.println(logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm your personal AI");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        try {
            while (true) {
                String input = scanner.nextLine();
                System.out.println(LINE);

                // User wants to end the chatbot
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    break;
                }

                // List out all the tasks in the chatbot
                if (input.equalsIgnoreCase("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(String.format("%d. %s", i + 1, task));
                    }
                    System.out.println(LINE);
                    continue;
                }

                // Mark a task as done
                if (input.startsWith("mark")) {
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    Task task = tasks.get(index - 1);
                    task.markDone();
                    System.out.println("Great job completing the task! I've marked it as done.");
                    System.out.println(task);
                    System.out.println(LINE);
                    continue;
                }

                // Unmark a done task
                if (input.startsWith("unmark")) {
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    Task task = tasks.get(index - 1);
                    task.markUndone();
                    System.out.println("Oops... Did you mark it incorrectly?");
                    System.out.println(task);
                    System.out.println(LINE);
                    continue;
                }

                // Add a todo to the chatbot
                if (input.startsWith("todo")) {
                    String desc = input.replaceAll("(todo )", "");
                    Task task = new ToDo(desc);
                    tasks.add(task);
                    System.out.println("Got it!. I've added this task:");
                    System.out.println(" " + task);
                    System.out.printf("Now you have %d tasks in the list%n", tasks.size());
                    System.out.println(LINE);
                    continue;
                }


                // Add a deadline
                if (input.startsWith("deadline")) {
                    String desc_time = input.replaceAll("(deadline)", "");
                    String[] strings = desc_time.split(" /by ");
                    Task task = new Deadline(strings[0], strings[1]);
                    tasks.add(task);
                    System.out.println("Got it!. I've added this task:");
                    System.out.println(" " + task);
                    System.out.printf("Now you have %d tasks in the list%n", tasks.size());
                    System.out.println(LINE);
                    continue;
                }

                // Add an Event
                if (input.startsWith("event")) {
                    String content = input.replaceAll("event", "");
                    String[] desc_time = content.split(" /from ");
                    String[] times = desc_time[1].split(" /to ");
                    Task task = new Event(desc_time[0], times[0], times[1]);
                    tasks.add(task);
                    System.out.println("Got it!. I've added this task:");
                    System.out.println(" " + task);
                    System.out.printf("Now you have %d tasks in the list%n", tasks.size());
                    System.out.println(LINE);
                    continue;
                }

                // Add a generic task to the chatbot
                tasks.add(new Task(input));
                System.out.printf("added: %s%n", input);
                System.out.println(LINE);

            }
        } catch (Exception e) {
            System.out.println("Respironix has encountered an issue; exiting");
        }
    }
}
