import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm iP");
        Task[] tasks = new Task[100];
        int nextTask = 0;
        Scanner input = new Scanner(System.in);
        String response = "";
        while (!Objects.equals(response, "bye")) {
            response = input.nextLine();
            if (Objects.equals(response, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (Objects.equals(response, "list")) {
                for (int i = 0; i < nextTask; i++) {
                    System.out.println(i + 1 + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
            } else if (response.startsWith("mark")) {
                // Assumption: "mark" is not allowed as a task name & you can mark already done tasks.
                // Issue: "mark" by itself crashes.
                String[] array = response.split(" ");
                String lastVal = array[array.length - 1];
                int taskToMark = Integer.parseInt(lastVal);
                if (taskToMark <= nextTask) {
                    tasks[taskToMark - 1].completeTask();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + tasks[taskToMark - 1].getStatusIcon() + "] " + tasks[taskToMark - 1].getDescription());
                } else {
                    System.out.println("Invalid Mark Input Found.");
                }
            } else {
                tasks[nextTask++] = new Task(response);
                System.out.println("added: " + response);
            }
        }
    }
}
