import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = "Wiz";
        List<Task> tasks = new ArrayList<>();



        System.out.println("Hello from " + logo +
                "\nWhat can I do for you?\n" +
                "Bye! Hope to see you again");
        System.out.println("--------------------------");

        while(true) {
            String command = scanner.nextLine();
            System.out.println("--------------------------");

            System.out.println("--------------------------");

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------");
                break;
            } else if (command.equals("List")) {
                System.out.println("Task List:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
                }
            } else {
                Task newTask = new Task(command);
                tasks.add(newTask);
                System.out.println("Added: " + newTask);
            }
        }



    }
}
