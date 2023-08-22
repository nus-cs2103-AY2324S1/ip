import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Task> tasks = new ArrayList<>();

        String greeting = "Hello! I'm Toothless. \n" +
                "What can I do for you today? \n" +
                "---------------------------------";
        String farewell = "Goodbye. Hope to see you soon!\n" +
                "---------------------------------";
        String name = "Toothless";

        System.out.println(greeting);

        String bye = "bye";
        String taskList = "list";

        while (true) {

            String nextInput = scanner.nextLine().trim();

            if (nextInput.equals(bye)) {
                break;
            } else if (nextInput.equals(taskList)) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println((i + 1) + ": " + task.getIsDone() + " " + task.getTaskName());
                }

            } else if (nextInput.contains("unmark")) {
                int taskNum = Integer.valueOf(nextInput.split(" ")[1]);
                Task task = tasks.get(taskNum - 1);
                task.markAsUndone();
                System.out.println("Task " + task.getTaskName() + " is now not completed :(");

            } else if (nextInput.contains("mark")) {
                int taskNum = Integer.valueOf(nextInput.split(" ")[1]);
                Task task = tasks.get(taskNum - 1);
                task.markAsDone();
                System.out.println("Task " + task.getTaskName() + " is completed! Good job :)");

            } else {
                Task newTask = new Task(nextInput);
                tasks.add(newTask);
                System.out.println("Task " + nextInput + " has been added");
            }
            System.out.println("\n---------------------------------");
        }

        System.out.println(farewell);
    }
}
