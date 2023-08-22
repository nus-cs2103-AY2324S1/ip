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
            if (output.equals("bye")) {
                System.out.println(bye);
                break;
            }
            else if (output.equals("list")) {
                list.display();
            } else if (output.startsWith("mark")) {
                String[] inputs = output.split(" ");
                int key = Integer.parseInt(inputs[1]);
                list.markDone(key);
            } else if (output.startsWith("unmark")) {
                String[] inputs = output.split(" ");
                int key = Integer.parseInt(inputs[1]);
                list.unmark(key);
            } else {
                list.add(output);
            }
        }
    }
}
