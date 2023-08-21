import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String userInput = scanner.nextLine();

            if ("bye".equalsIgnoreCase(userInput)) {
                break;
            }

            if ("list".equalsIgnoreCase(userInput)) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    Task task = tasks[i];
                    System.out.println((i + 1) + "." + task);
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("mark ")) {
                processTask(userInput, tasks, true, taskCount);
            } else if (userInput.startsWith("unmark ")) {
                processTask(userInput, tasks, false, taskCount);
            } else if (userInput.startsWith("todo ")) {
                if (taskCount >= 100) {
                    System.out.println("You have reached the task limit. Cannot add more tasks!");
                    continue;
                }

                String description = userInput.substring(5).trim();
                Todo newTodo = new Todo(description);
                tasks[taskCount] = newTodo;
                taskCount++;

                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTodo);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("deadline ")) {
                if (taskCount >= 100) {
                    System.out.println("You have reached the task limit. Cannot add more tasks!");
                    continue;
                }

                String content = userInput.substring(9).trim();
                int index = content.indexOf("/by");

                if (index == -1) {
                    System.out.println("Please use '/by' to specify the deadline time.");
                } else {
                    String description = content.substring(0, index).trim();
                    String by = content.substring(index + 4).trim();
                    Deadline newDeadline = new Deadline(description, by);
                    tasks[taskCount] = newDeadline;
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newDeadline);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
            } else if (userInput.startsWith("event ")) {
                if (taskCount >= 100) {
                    System.out.println("You have reached the task limit. Cannot add more tasks!");
                    continue;
                }

                String content = userInput.substring(6).trim();

                String[] parts = content.split("/from | /to ");

                if (parts.length < 3) {
                    System.out.println("Please use the format: event [description] /from [start time] /to [end time]");
                    continue;
                }

                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();

                Event newEvent = new Event(description, from, to);
                tasks[taskCount] = newEvent;
                taskCount++;

                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newEvent);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                if (taskCount >= 100) {
                    System.out.println("You have reached the task limit. Cannot add more tasks!");
                    continue;
                }

                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
                tasks[taskCount] = new Task(userInput);
                taskCount++;
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void processTask(String userInput, Task[] tasks, boolean mark, int taskCount) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber <= 0 || taskNumber > taskCount) {
                throw new IndexOutOfBoundsException();
            }

            Task task = tasks[taskNumber - 1];
            if (mark) {
                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.unmarkAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  [" + task.getStatusIcon() + "] " + task.description);
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That task doesn't exist!");
        }
    }
}

