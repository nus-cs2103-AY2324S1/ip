import java.util.Scanner;

public class Duke {
    private static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static boolean parseCommand(String input) {
        switch (input) {
            case "bye": {
                Duke.bye();
                return false;
            }

            default: {
                System.out.println(input);
                return true;
            }
        }
    }


    public static void main(String[] args) {
        Duke.greet();
        Scanner scanner = new Scanner(System.in);

        boolean parse = true;
        while (parse) {
            String input = scanner.nextLine();
            parse = Duke.parseCommand(input);
        }

    }
}
