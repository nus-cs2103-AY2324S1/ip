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
            if (output.equals("list")) {
                list.display();
            }
            if (output.startsWith("mark")) {
                String[] inputs = output.split(" ");
                int key = Integer.parseInt(inputs[1]);
                list.markDone(key);
            }
            if (output.startsWith("unmark")) {
                String[] inputs = output.split(" ");
                int key = Integer.parseInt(inputs[1]);
                list.unmark(key);
            }
            if (output.startsWith("todo")) {
                String[] inputs = output.split(" ", 2);
                list.addToDo(inputs[1]);
            }
            if (output.startsWith("deadline")) {
                String[] inputs = output.split(" ", 2);
                String[] deadLine = inputs[1].split("/by", 2);
                list.addDeadline(deadLine[0], deadLine[1]);
            }
            if (output.startsWith("event")) {
                String[] inputs = output.split(" ", 2);
                String[] from = inputs[1].split("/from", 2);
                String[] to = from[1].split("/to", 2);
                list.addEvent(from[0], to[0], to[1]);
            }
        }
    }
}
