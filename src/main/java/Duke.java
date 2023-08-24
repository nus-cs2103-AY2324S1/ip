import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ██▄   ████▄    ▄     ▄▀  \n" +
                "█  █  █   █     █  ▄▀    \n" +
                "█   █ █   █ ██   █ █ ▀▄  \n" +
                "█  █  ▀████ █ █  █ █   █ \n" +
                "███▀        █  █ █  ███  \n" +
                "            █   ██       \n" +
                "                         ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if ("bye".equals(input)) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break; // This will exit the loop and end the program
            } else {
                System.out.println(input);
            }
        }

        scanner.close();

    }
}
