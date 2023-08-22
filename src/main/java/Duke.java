import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String line = "____________________________________________________________\n";
    private static final String greetingMessage = "Hello! I'm EnPassant\n"
                                                + "What can I do for you?\n";
    private static final String exitMessage = "Great heavens! Hope to see you again soon!\n";
    private static void printWithLines(String message) {
        System.out.print(line + message + line);
    }
    private static void printList(ArrayList<String> list) {
        System.out.print(line);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
        System.out.print(line);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        printWithLines(greetingMessage);
        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                printWithLines(exitMessage);
                break;
            } else if (str.equals("list")) {
                printList(tasks);
            } else {
                tasks.add(str);
                printWithLines("added: " + str + '\n');
            }
        }
    }
}
