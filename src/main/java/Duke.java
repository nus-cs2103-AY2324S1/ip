import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String Start = "Hello! I'm Red \nWhat can I do for you?";
        System.out.println(Start);

        String[] tasks = new String[100];
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();
            if(input.equals("bye"))
                break;

            if (input.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
                continue;
            }

            tasks[taskCount] = input;
            taskCount++;
            System.out.println("added: " + input);

        }

        String End = "Bye. Hope to see you again soon!\n";
        System.out.println(End);
    }
}
