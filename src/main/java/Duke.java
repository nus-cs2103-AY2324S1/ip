import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Arona, your Virtual Assistant.\n    What can I do for you today?\n";
        String exit = "Goodbye. Hope to see you again soon!";

        System.out.println("    " + greeting);

        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine();

        while (!echo.toLowerCase().equals("bye")) {
            if (!echo.trim().isEmpty()) { // Check for non-empty input
                System.out.println("    " + echo + "\n");
            }
            echo = scanner.nextLine();
        }

        System.out.println("    " + exit);
    }
}
