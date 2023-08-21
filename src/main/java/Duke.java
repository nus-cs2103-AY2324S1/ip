import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
            } else {
                printHorizontalLine();
                System.out.println(command);
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
