import java.util.Scanner;

public class Duke {
    private static String horizontalLine = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(horizontalLine);
        String myName = "Quack-NKN";
        System.out.print("Hello from ");
        System.out.println(myName);
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            // receive input
            String command = scanner.nextLine();
            System.out.println(horizontalLine);

            // exit
            if (command.equals("bye")) {
                isExit = true;
            }

            // echo
            else {
                echo(command);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    private static void echo(String text) {
        System.out.println(text);
        System.out.println(horizontalLine);
    }
}
