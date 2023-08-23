import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = "I'm Chewy,\n" +
                "What can I do for you?\n";
        System.out.println("Hello!\n" + logo);

        // Create a scanner object to read commands entered by the user
        Scanner scanner = new Scanner(System.in);

        // Store tasks entered by the user in tasks
        List<Task> tasks = new ArrayList<>();

        // Start command loop
        while (true) {
            // Read the next line of input
            String userInput = scanner.nextLine();

            try {
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equals("help")) {
                    HelpMessage.displayHelpMessage();
                } else if (userInput.equals("list")) {
                    listTasks(tasks);
                } else if (userInput.startsWith("mark")) {
                    markTaskAsDone(userInput, tasks);
                } else if (userInput.startsWith("unmark")) {
                    unmarkTaskAsDone(userInput, tasks);
                } else {
                    addTask(userInput, tasks);
                }
            } catch (DukeException e) {
                System.out.println(":( Chewy can't understand! " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Something unexpected happened. try 'help' to see a list of commands");
            }
        }
    }

    private static void addTask(String userInput, List<Task> tasks) throws DukeException {
        String[] inputParts = userInput.split(" ", 2);

        // Check if a valid addTask command is entered
        String inputCommand = inputParts[0];
        if (!(inputCommand.equals("todo") || inputCommand.equals("deadline") || inputCommand.equals("event"))) {
            throw new InvalidCommandException();
        }

        // Throw InvalidCommandException if there is no description
        if (inputParts.length == 1) throw new NoDescriptionException();

        String taskDesc = inputParts[1];
        Task task;
        switch (inputCommand) {
            case "todo":
                task = new Todo(taskDesc);
                break;
            case "deadline":
                task = new Deadline(taskDesc);
                break;
            case "event":
                task = new Event(taskDesc);
                break;
            default:
                // Throw InvalidCommandException as invalid command was entered
                throw new DukeException("Error, see 'help' for a list of commands");
        }
        // Add task to task list
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n\t" + task);
        System.out.printf("Now you have %d tasks in the list%n", tasks.size());
    }

    private static void unmarkTaskAsDone(String userInput, List<Task> tasks) throws DukeException {
        try {
            // Get the id of the task (zero-indexed) from the userInput
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            // Throw InvalidTaskException if task id is out of bounds of task list or is invalid
            if (taskId <= 0 || taskId > tasks.size()) throw new InvalidTaskException();
            // Unmark the selected task as done
            Task selectedTask = tasks.get(taskId - 1);
            selectedTask.unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + selectedTask);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
        }
    }

    private static void markTaskAsDone(String userInput, List<Task> tasks) throws DukeException {
        try {
            // Get the id of the task (zero-indexed) from the userInput
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            // Throw InvalidTaskException if task id is out of bounds of task list or is invalid
            if (taskId <= 0 || taskId > tasks.size()) throw new InvalidTaskException();
            // Mark the selected task as done
            Task selectedTask = tasks.get(taskId - 1);
            selectedTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n"
                    + selectedTask);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
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