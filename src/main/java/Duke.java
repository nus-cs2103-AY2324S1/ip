import java.util.Scanner;

public class Duke {
    private static final String chatBotName = "Cristiano";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greetUser();
        echoUntilBye();
    }

    public static void greetUser() {
        printLine();
        System.out.println("Hello! I'm " + chatBotName + "! SUIIII!!!");
        System.out.println("What can I do for you?");
    }

    public static void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void echoUntilBye() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            printLine();
            System.out.println(input);
            printLine();
        }
        exit();
    }
}
