import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        greet();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(input);
        }
        exit();
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can i do for you?\n");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
