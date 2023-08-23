import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = "I'm Chewy,\n" +
                "What can I do for you?\n";
        System.out.println("Hello! \n" + logo);

        // Create a scanner object to read commands entered by the user
        Scanner scanner = new Scanner(System.in);

        // Store tasks entered by the user in tasks
        List<Task> tasks = new ArrayList<>();

        // Start command loop
        while (true) {
            // Read the next line of input
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                listTasks(tasks);
            } else if (userInput.startsWith("mark")) {
                markTaskAsDone(userInput, tasks);
            } else if (userInput.startsWith("unmark")) {
                unmarkTaskAsDone(userInput, tasks);
            } else {
                addTask(userInput, tasks);
            }
        }
    }

    private static void addTask(String userInput, List<Task> tasks) {
        try {
            String[] inputParts = userInput.split(" ", 2);
            String taskType = inputParts[0];
            String taskDesc = inputParts[1];
            Task task;

            if (taskType.equals("todo")) {
                task = new Todo(taskDesc);
            } else if (taskType.equals("deadline")) {
                task = new Deadline(taskDesc);
            } else if (taskType.equals("event")) {
                task = new Event(taskDesc);
            } else {
                System.out.println("Invalid task. Please start with 'todo', 'deadline' or 'event'");
                return;
            }
            // Add task to task list
            tasks.add(task);
            System.out.println("Got it. I've added this task:\n\t" + task.toString());
            System.out.println(String.format("Now you have %d tasks in the list", tasks.size()));
        } catch (Exception e) {
            System.out.println("Invalid input. Try starting with 'todo', 'deadline' or 'event'\n");
        }
    }

    private static void unmarkTaskAsDone(String userInput, List<Task> tasks) {
        try {
            // Get the id of the task (zero-indexed) from the userInput
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            // Unmark the selected task as done
            Task selectedTask = tasks.get(taskId - 1);
            selectedTask.unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + selectedTask.toString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid use of 'unmark' command. Please follow the format 'unmark [task id]'");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please ensure that the id of the task is within the task list");
        } catch (Exception e) {
            System.out.println("Error. Please follow the format 'unmark [task id]'");
        }
    }

    private static void markTaskAsDone(String userInput, List<Task> tasks) {
        try {
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            Task selectedTask = tasks.get(taskId - 1);
            selectedTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n"
                    + selectedTask.toString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid use of 'mark' command. Please follow the format 'mark [task id]'");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please ensure that the id of the task is within the task list");
        } catch (Exception e) {
            System.out.println("Error. Please follow the format 'mark [task id]'");
        }
    }

    private static void listTasks(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i=0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskLine = String.format("%d.%s",
                    i+1,
                    task.toString());
            System.out.println(taskLine);
        }
    }
}