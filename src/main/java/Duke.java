import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Zac");
        System.out.println("What can I do for you?");
        printHorizontalLine();

        boolean isBotRunning = true;
        while (isBotRunning) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                isBotRunning = false;
                sayBye();
            } else if (command.equals("list")) {
                int numTask = 1;
                for (String task : taskList) {
                    System.out.printf("%d. %s\n", numTask, task);
                    numTask += 1;
                }
            } else {
                printHorizontalLine();
                taskList.add(command);
                System.out.printf("added: %s%n", command);
                printHorizontalLine();
            }
        }

    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void sayBye() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
