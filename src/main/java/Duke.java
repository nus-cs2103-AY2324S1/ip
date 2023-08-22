import java.util.Scanner;

/**
 * Duke is a simple task management application that allows users to add and list tasks.
 */
public class Duke {
    /**
     * The main method that starts the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        String logo = "Hello! I am Bob\n"
                + "What can I do for you?\n"
                + "----------------------------\n";
        String bye = "Bye! Hope to see you again soon!\n";
        System.out.println(logo);
        DukeList list = new DukeList();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String output = scanner.nextLine();

            // Exit the loop and the application if the user inputs "bye"
            if (output.equals("bye")) {
                System.out.println(bye);
                break;
            }
            // Display the list of tasks if the user inputs "list"
            else if (output.equals("list")) {
                list.display();
            }
            // Add the user input as a new task
            else {
                list.add(output);
            }
        }
    }
}
