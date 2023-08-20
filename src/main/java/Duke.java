import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Arona, your Virtual Assistant.\n    What can I do for you today?\n";
        String exit = "Goodbye. Hope to see you again soon!";
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("    " + greeting);

        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine().toLowerCase().trim();

        while (!echo.equals("bye")) {
            if (echo.equals("list")) {
                System.out.println("    Here are your current tasks:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks.get(i).getStatusIcon() + tasks.get(i));
                }
                System.out.println("");
            } else if (echo.startsWith("mark ")) {
                int index = Integer.valueOf(echo.split(" ")[1]) - 1;

                System.out.println("    Nice! I've marked this task as done:");
                Task task = tasks.get(index);
                task.mark();
                System.out.println("    " + task.getStatusIcon() + task + "\n");

            } else if (echo.startsWith("unmark ")) {
                int index = Integer.valueOf(echo.split(" ")[1]) - 1;

                System.out.println("    Ok, I've marked this task as not done yet:");
                Task task = tasks.get(index);
                task.unmark();
                System.out.println("    " + task.getStatusIcon() + task + "\n");

            } else if (!echo.isEmpty()) { // Check for non-empty input
                tasks.add(new Task(echo));
                System.out.println("    added: " + echo + "\n");
            }
            echo = scanner.nextLine().toLowerCase().trim();
        }
        System.out.println("    " + exit);
    }
}
