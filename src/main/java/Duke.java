import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Beary";
        Scanner scanner = new Scanner(System.in);

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
            System.out.println(command);
            printLine();
        }
    }


    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

