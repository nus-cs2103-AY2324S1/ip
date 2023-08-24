import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Duke {
    private static String chatbot = "chuababyy chatbot";
    private static String line = "------------------------------------";

    private static final String commands =
            line + "\n"
            + "List of commands\n"
            + "1. todo [description]\n"
            + "2. deadline [description] /by [deadline]\n"
            + "3. event [description] /from [start date] /to [end date]\n"
            + "4. mark [item_number]\n"
            + "5. unmark [item_number]\n"
            + "6. delete [item_number]\n"
            + "7. list\n"
            + "8. bye\n"
            + line ;

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
                if (userInputParts.length > 1) {
                    System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                    continue;
                }
                System.out.println("Here are the tasks in your list:");
                System.out.println(fullList.toString());
            }

            // Mark done
            else if (command.equals("mark")) {
                String[] split_index = userInput.split(" ");
                if (split_index.length <= 1 || split_index.length > 2) {
                    System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                    continue;
                }
                int index = Integer.parseInt(split_index[1]) - 1;
                fullList.markItem(index);
            }

            // Mark not done
            else if (command.equals("unmark")) {
                String[] split_index = userInput.split(" ");
                if (split_index.length <= 1 || split_index.length > 2) {
                    System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                    continue;
                }
                int index = Integer.parseInt(split_index[1]) - 1;
                fullList.unMarkItem(index);
            }

            // create new ToDos object
            else if (command.equals("todo")) {
                if (userInputParts.length <= 1) {
                    System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                    continue;
                }
                ToDos toDo = new ToDos(userInputParts[1].trim());
                fullList.addToList(toDo);
            }

            // create new Deadline object
            else if (command.equals("deadline")) {
                String[] details = userInputParts[1].split("/by");
                String description = details[0].trim();

                if (details.length <= 1) {
                    System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                    continue;
                }

                String by = details[1].trim();
                Deadline deadline = new Deadline(description, by);
                fullList.addToList(deadline);
            }

            // create new Event object
            else if (command.equals("event")) {
                String[] details = userInputParts[1].split("/from");
                if (details.length <=1) {
                    System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                    continue;
                }
                String[] dateParts = details[1].trim().split("/to");
                if (dateParts.length <=1) {
                    System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                    continue;
                }

                String description = details[0].trim();
                String from = dateParts[0].trim();
                String to = dateParts[1].trim();

                Event event = new Event(description, from, to);
                fullList.addToList(event);
            }

            else if (command.equals("delete")) {
                String[] split_index = userInput.split(" ");
                if (split_index.length <= 1 || split_index.length > 2) {
                    System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                    continue;
                }
                int index = Integer.parseInt(split_index[1]) - 1;
                fullList.deleteFromList(index);
            }

            // Invalid commands
            else {
                System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
            }

        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

        scanner.close();

    }
}
