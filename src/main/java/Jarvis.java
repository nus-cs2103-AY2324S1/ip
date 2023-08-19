import java.util.Scanner;

public class Jarvis {
    public static void main(String[] args) {
        greet();
        echo();
        exit();
    }
    public static void greet() {
        String logo = "  _____     _       ____  __        __ ____    ____           \n"
                + " |_   _|   / \\     |  _ \\ \\ \\      / /|_  _|  / ___|      \n"
                + "   | |    / _ \\    | |_) | \\ \\    / /   | |  | (___   \n"
                + "   | |   / / \\ \\   | ___/   \\ \\  / /    | |   \\___ \\          \n"
                + "  _| |  / ----- \\  | |\\ \\    \\ \\/ /    _| |_  ____) |    \n"
                + " |___/ /_/     \\_\\ |_| \\_\\    \\__/    |_____||_____/             \n";

        horizontal_line_printer();
        System.out.println("Hello from Jarvis\n" + logo);
        System.out.println("What can I do for you, sir?");
    }
    public static void exit() {
        horizontal_line_printer();
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void echo() {
        horizontal_line_printer();
        System.out.println("Echo program started. Exit by entering 'bye' command.");
        horizontal_line_printer();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Exiting the echo program.");
                break;
            }
            horizontal_line_printer();
            System.out.println(input);
            horizontal_line_printer();
        }
        sc.close();
    }
    public static void horizontal_line_printer() {
        System.out.println("-".repeat(50));
    }
}
