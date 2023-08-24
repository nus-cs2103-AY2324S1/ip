import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Duke {
    private static String chatbot = "chuababyy chatbot";
    private static String line = "------------------------------------";

    public static void main(String[] args) {
        TaskList fullList = new TaskList();

        System.out.println(line);
        System.out.println("Hello! I'm " + chatbot);
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine().trim();
            String[] userInputParts = userInput.split(" ", 2);
            String command = userInputParts[0];

            // Error message when user enters empty response
            if (userInput.equals("")) {
                System.out.println(line);
                System.out.println("Item to be added cannot be empty");
                System.out.println(line);
            }

            // Closes chatbot when user says bye
            else if (command.equals("bye")) {
                break;
            }

            // List out all items when user says list
            else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                System.out.println(fullList.toString());
            }

            // Mark done
            else if (command.equals("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                fullList.markItem(index);
            }

            // Mark not done
            else if (command.equals("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                fullList.unMarkItem(index);
            }

            // create new ToDos object
            else if (command.equals("todo")) {
                ToDos toDo = new ToDos(userInputParts[1].trim());
                fullList.addToList(toDo);
            }

            // create new Deadline object
            else if (command.equals("deadline")) {
                String[] details = userInputParts[1].split("/by");
                String description = details[0].trim();

                if (details.length <= 1) {
                    System.out.println("Please input a deadline in the following format: " +
                            "deadline [description] /by [date]");
                    continue;
                }

                String by = details[1].trim();
                Deadline deadline = new Deadline(description, by);
                fullList.addToList(deadline);
            }

            else if (command.equals("event")) {
                String[] details = userInputParts[1].split("/from");
                String[] dateParts = details[1].trim().split("/to");

                if (details.length <= 1 || dateParts.length <=1) {
                    System.out.println(
                            "Please input an event with the " +
                            "following format: event [description] /from [date] /to [date]");
                    continue;
                }

                String description = details[0].trim();
                String from = dateParts[0].trim();
                String to = dateParts[1].trim();

                Event event = new Event(description, from, to);
                fullList.addToList(event);
            }

            else {
                System.out.println(line);
                System.out.println("No command detected");
                System.out.println(line);
            }

        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

        scanner.close();

    }
}
