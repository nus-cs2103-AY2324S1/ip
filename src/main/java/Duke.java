import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Duke {
    private static String chatbot = "chuababyy chatbot";
    private static String line = "------------------------------------";

    public static void main(String[] args) {
        ArrayList<Task> fullList = new ArrayList();

        System.out.println(line);
        System.out.println("Hello! I'm " + chatbot);
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine().trim();

            // Closes chatbot when user says bye
            if (userInput.equals("bye")) {
                break;
            }

            // List out all items when user says list
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= fullList.size(); i++) {
                    int index = i - 1;
                    System.out.println(i + ". " + fullList.get(index).getStatusIcon() +
                            " " + fullList.get(index).getDescription());
                }
                continue;
            }

            // Error message when user enters empty response
            if (userInput.equals("")) {
                System.out.println(line);
                System.out.println("Item to be added cannot be empty");
                System.out.println(line);
                continue;
            }

            if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (index >= 0 && index < fullList.size()) {
                    Task curr = fullList.get(index);
                    curr.markDone();

                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(curr.getStatusIcon() + " " + curr.getDescription());
                    System.out.println(line);
                    continue;
                } else {
                    System.out.println(line);
                    System.out.println("No such item exists");
                    System.out.println(line);
                    continue;
                }
            }

            if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (index >= 0 && index < fullList.size()) {
                    Task curr = fullList.get(index);
                    curr.markNotDone();

                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(curr.getStatusIcon() + " " + curr.getDescription());
                    System.out.println(line);
                    continue;
                } else {
                    System.out.println(line);
                    System.out.println("No such item exists");
                    System.out.println(line);
                    continue;
                }
            }

            fullList.add(new Task(userInput));
            System.out.println(line);
            System.out.println("added: " + userInput);
            System.out.println(line);
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

        scanner.close();

    }
}
