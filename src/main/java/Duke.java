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

            try {
                if (userInput.equalsIgnoreCase("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println((i + 1) + "." + task.toString());
                    }
                    System.out.println(horizontalLine);

                } else if (userInput.startsWith("mark")) {
                    if (userInput.length() <= 5) {
                        throw new DukeException("Task to mark cannot be empty!");
                    }
                    try {
                        int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                        updateTaskStatus(tasks, taskNumber, true, "Task " + (taskNumber + 1) + " is already done!", "Great job! Task " + (taskNumber + 1) + " is done!");
                    } catch (NumberFormatException e) {
                        throw new DukeException("Invalid task number.");
                    }

                } else if (userInput.startsWith("unmark")) {
                    if (userInput.length() <= 7) {
                        throw new DukeException("Task to unmark cannot be empty!");
                    }
                    try {
                        int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                        updateTaskStatus(tasks, taskNumber, false, "Task " + (taskNumber + 1) + " is still incomplete.", "No worries, Task " + (taskNumber + 1) + " is incomplete.");
                    } catch (NumberFormatException e) {
                        throw new DukeException("Invalid task number.");
                    }

                } else if (userInput.startsWith("delete")) {
                    if (userInput.length() <= 7) {
                        throw new DukeException("Task to be deleted cannot be empty!");
                    }
                    try {
                        int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                        Task deletedTask = tasks.remove(taskNumber);
                        System.out.println("This task has been removed\n  " + deletedTask + "\nYou have a total of " + tasks.size() + (tasks.size() == 1 ? " task.\n" : " tasks.\n") + horizontalLine);
                    } catch (NumberFormatException e) {
                        throw new DukeException("Invalid task number.");
                    }

                } else if (userInput.startsWith("todo")) {
                    if (userInput.length() <= 5) {
                        throw new DukeException("The description of a todo cannot be empty!");
                    }
                    String description = userInput.substring(5);
                    Task task = new ToDo(description);
                    tasks.add(task);
                    System.out.println("I've added this task:\n  " + task + "\nYou have a total of " + tasks.size() + (tasks.size() == 1 ? " task.\n" : " tasks.\n") + horizontalLine);

                } else if (userInput.startsWith("deadline")) {
                    if (userInput.length() <= 9) {
                        throw new DukeException("The description/ deadline of a deadline cannot be empty!");
                    }
                    String fullStr = userInput.substring(9);
                    String[] parts = fullStr.split(" /by ");
                    String description = parts[0];
                    String by = parts[1];
                    Task task = new Deadline(description, by);
                    tasks.add(task);
                    System.out.println("I've added this task:\n  " + task + "\nYou have a total of " + tasks.size() + (tasks.size() == 1 ? " task.\n" : " tasks.\n") + horizontalLine);

                } else if (userInput.startsWith("event")) {
                    if (userInput.length() <= 6) {
                        throw new DukeException("The description/ time of an event cannot be empty!");
                    }
                    String fullStr = userInput.substring(6);
                    String[] partialStr = fullStr.split(" /from ");
                    String description = partialStr[0];
                    String[] toFrom = partialStr[1].split(" /to ");
                    String from = toFrom[0];
                    String to = toFrom[1];
                    Task task = new Event(description, from, to);
                    tasks.add(task);
                    System.out.println("I've added this task:\n  " + task + "\nYou have a total of " + tasks.size() + (tasks.size() == 1 ? " task.\n" : " tasks.\n") + horizontalLine);

                } else {
                    throw new DukeException("Invalid command.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage() + "\n" + horizontalLine);
            }

        } while (!userInput.equalsIgnoreCase("bye"));

        System.out.println("Goodbye. Catch you later!" + "\n" + horizontalLine);
        scanner.close();
    }

    private static void updateTaskStatus(ArrayList<Task> tasks, int taskNumber, boolean expectedStatus, String doneMessage, String undoneMessage) {
        String horizontalLine = "______________________________________________";
        try {
            Task task = tasks.get(taskNumber);
            if (task.isDone == expectedStatus) {
                System.out.println(doneMessage + "\n" + horizontalLine);
            } else {
                task.isDone = expectedStatus;
                System.out.println(undoneMessage + "\n" + horizontalLine);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number.");
        }
    }
}
