import java.util.ArrayList;
import java.util.Scanner;

public class Pogo {
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static int parseTaskIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).getStatusMessage());
        }
    }

    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Pogo\nWhat can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);
        String quitMessage = "Bye. Hope to see you again soon!";

        while (true) {
            String input = scanner.nextLine();
            System.out.println(horizontalLine);

            // Convert switch statement to if-else statement
            if (input.equals("bye")) {
                System.out.println(quitMessage);
                break;
            } else if (input.equals("list")) {
                Pogo.printTasks();
            } else if (input.startsWith("mark")) {
                int index = Pogo.parseTaskIndex(input);
                tasks.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(index).getStatusMessage());
            } else if (input.startsWith("unmark")) {
                int index = Pogo.parseTaskIndex(input);
                tasks.get(index).markAsUndone();
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(tasks.get(index).getStatusMessage());
            }
            else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }

            System.out.println(horizontalLine);

        }
    }
}
