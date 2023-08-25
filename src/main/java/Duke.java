import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "______________________________________________";

        Scanner scanner = new Scanner(System.in);

        System.out.println(horizontalLine +
                "\nHello i'm ChatterBuddy\n" +
                "Is there anything I can assist you with today?\n" +
                horizontalLine);

        ArrayList<Task> tasks = new ArrayList<>();
        String userInput;

        do {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println((i + 1) + ".[" + task.getStatusIcon() + "] " + task.description);
                }
            } else if (userInput.startsWith("mark ")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                    Task task = tasks.get(taskNumber);
                        if (task.isDone) {
                            System.out.println("Task " + (taskNumber + 1) + " is already done!\n" + horizontalLine);
                        } else {
                            task.isDone = true;
                            System.out.println("Great job! Task " + (taskNumber + 1) + " is done!\n" + horizontalLine);
                        }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid task number. Please try again. ");
                }
            } else if (userInput.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                    Task task = tasks.get(taskNumber);
                        if (!task.isDone) {
                            System.out.println("Task " + (taskNumber + 1) + " is still incomplete.\n" + horizontalLine);
                        } else {
                            task.isDone = false;
                            System.out.println("No worries, Task " + (taskNumber + 1) + " is incomplete.\n" + horizontalLine);
                        }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid task number. Please try again. ");
                }
            } else {
                    System.out.println("added task: " + userInput + "\n" + horizontalLine);
                    Task task = new Task(userInput);
                    tasks.add(task);
            }

        } while (!userInput.equalsIgnoreCase("bye"));

        System.out.println("Goodbye. Catch you later!" + "\n" + horizontalLine);
        scanner.close();
    }
}
