import java.util.Scanner;

public class Duke {
    private static void greet() {
        System.out.println("Hello! I'm Untitled!");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void echo(String input) {
        System.out.println(input);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Duke.greet();
        for (String input = scanner.nextLine(); !input.equals("bye"); input = scanner.nextLine()) {
            Duke.echo(input);
        }
        Duke.exit();

        scanner.close();
    }
}
