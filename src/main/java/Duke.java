import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> inputHistory = new ArrayList<>();

        System.out.println("Hello! I'm Gideon");
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                for(int i = 0; i < inputHistory.size(); i++) {
                    System.out.println((i + 1) + ". " + inputHistory.get(i).getDescription());
                }
            } else if (userInput.startsWith("mark")) {
                int id = Integer.parseInt(userInput.split(" ")[1]);
                Task markedTask = inputHistory.get(id - 1);
                markedTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(markedTask.getDescription());
            } else if (userInput.startsWith("unmark")) {
                int id = Integer.parseInt(userInput.split(" ")[1]);
                Task unmarkedTask = inputHistory.get(id - 1);
                unmarkedTask.markAsUnDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(unmarkedTask.getDescription());
            } else {
                Task t = new Task(userInput);
                inputHistory.add(t);
                System.out.println("added: " + t.getDescription());
            }
        }
        scanner.close();
    }
}
