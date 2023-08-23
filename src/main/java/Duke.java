import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________\n";
    private static final String greetingMessage = "Hello! I'm EnPassant\n"
                                                + "What can I do for you?\n";
    private static final String exitMessage = "Bye! Hope to see you again soon!\n";
    private static final String invalidIndexMessage = "Great heavens! You have entered an invalid index!\n";
    private static final String markDoneMessage = "Great success! I have marked this task as done:\n";
    private static final String markUndoneMessage = "Very nice! I have marked this task as not done yet:\n";
    private static final String listMessage = "Here are the tasks in your list:\n";

    private static void printWithLines(String message) {
        System.out.print(line + message + line);
    }

    private static void printList(ArrayList<Task> list) {
        System.out.print(line + listMessage);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.print(line);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        printWithLines(greetingMessage);

        while (true) {
            String str = sc.nextLine();
            String[] tokens = str.split(" ");

            if (str.equals("bye")) {
                printWithLines(exitMessage);
                break;
            } else if (str.equals("list")) {
                printList(tasks);
            } else if (tokens[0].equals("mark") && tokens.length == 2) {
                int index = Integer.parseInt(tokens[1]);

                if (index < 1 || index > tasks.size()) {
                    printWithLines(invalidIndexMessage);
                    continue;
                }

                tasks.get(index - 1).markAsDone();
                printWithLines(markDoneMessage + "  " + tasks.get(index - 1) + '\n');
            } else if (tokens[0].equals("unmark") && tokens.length == 2) {
                int index = Integer.parseInt(tokens[1]);

                if (index < 1 || index > tasks.size()) {
                    printWithLines(invalidIndexMessage);
                    continue;
                }

                tasks.get(index - 1).markAsUndone();
                printWithLines(markUndoneMessage + "  " + tasks.get(index - 1) + '\n');
            } else {
                tasks.add(new Task(str));
                printWithLines("added: " + str + '\n');
            }
        }
    }
}
