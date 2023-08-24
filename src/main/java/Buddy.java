import java.util.Scanner;

public class Buddy {
    private static String name = "Buddy";

    public static void main(String[] args) {
        String greeting = String.format("Hello! I'm %s\n", name);
        String inquiry = "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println(greeting + inquiry);

        while (true) {
            command = scanner.next();
            if (command.equals("bye")) {
                System.out.println(exit);
                break;
            }
            System.out.println(command);
        }
    }
}
