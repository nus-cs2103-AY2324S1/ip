import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String name = "Beary";
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println(String.format("Hello! I'm %s\nWhat can I do for you?", name));
        printLine();

        while (true) {
            String command = scanner.nextLine();
            printLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                printLine();
                continue;
            }

            System.out.println("added: " + command);
            tasks.add(command);
            printLine();
        }
    }


    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

