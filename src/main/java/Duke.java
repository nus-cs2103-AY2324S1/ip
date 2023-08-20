import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Arona, your Virtual Assistant.\n    What can I do for you today?\n";
        String exit = "Goodbye. Hope to see you again soon!";
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println("    " + greeting);

        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine().toLowerCase().trim();

        while (!echo.equals("bye")) {
            if (echo.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    " +(i + 1) + ". " + tasks.get(i));
                }
                System.out.println("");
            } else if (!echo.isEmpty()) { // Check for non-empty input
                tasks.add(echo);
                System.out.println("    added: " +  echo + "\n");
            }
            echo = scanner.nextLine().toLowerCase().trim();
        }
        System.out.println("    " + exit);
    }
}
