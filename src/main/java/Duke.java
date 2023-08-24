import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Greeting message
        System.out.println("Hello! I'm Sivraj");
        System.out.println("What can I do for you?");

        while (true) {
            String echo = scanner.nextLine();

            if (echo.equals("bye")) {
                // Farewell message
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(" ----------------------------------------");
                System.out.println("    " + echo);
                System.out.println(" ----------------------------------------");
            }
        }

        scanner.close();

    }
}
