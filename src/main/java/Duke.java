import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A chatbot to keep track of your tasks
 *
 * @author Andrew Daniel Janong
 */
public class Duke {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        String LINE = "____________________________________________________________";
        Scanner sc = new Scanner(System.in);

        System.out.println("\t" + LINE + "\n" +
                "\t Hello I'm ADJ \n" +
                "\t What can I do for you? \n\t" +
                LINE);

        try {
            while (true) {
                String userInput = sc.nextLine();

                System.out.println("\t" + LINE);

                if (userInput.toLowerCase().equals("bye")) {
                    System.out.println("\t Bye. Hope to see you again soon!");
                    break;
                }
                if (userInput.toLowerCase().equals("list")) {
                    System.out.println("\t Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("\t " + (i + 1) + "." + tasks.get(i));
                    }
                } else if (userInput.split(" ")[0].toLowerCase().equals("mark")) {
                    System.out.println("\t Nice job! I've marked this task as done:");
                    Task task = tasks.get(Integer.parseInt(userInput.split(" ")[1]) - 1);
                    task.markAsDone();
                    System.out.println("\t\t " + task);
                } else if (userInput.split(" ")[0].toLowerCase().equals("unmark")) {
                    System.out.println("\t What happened? I've marked this task as not done yet:");
                    Task task = tasks.get(Integer.parseInt(userInput.split(" ")[1]) - 1);
                    task.markAsNotDone();
                    System.out.println("\t\t " + task);
                } else {
                    Task task = new Task(userInput);
                    tasks.add(task);
                    System.out.println("\t Task added: " + userInput);
                }

                System.out.println("\t" + LINE);
            }
        } catch (Exception e) {
            System.out.println("\t [ERROR] Sorry, an error occurred and ADJ has broken down :(");
        }

    }
}
