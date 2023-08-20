import java.util.Scanner;

public class Duke {
    static Task[] taskList = new Task[100];
    static int counter = 0;

    // Greet user
    public static void greet() {
        System.out.println("Hello I'm Oscar! Your friendly chatbot :)");
        System.out.println("What can I do for you?\n");
    }

    public static void bye() {
        System.out.println("Goodbye for now. " +
                "Hope to see you again soon!");
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= counter; i++) {
            Task currentTask = taskList[i - 1];
            System.out.println(i + "." + currentTask.toString());
        }
        System.out.println();
    }

    public static void mark(String index) {
        int taskIndex = Integer.parseInt(index) - 1;
        Task currentTask = taskList[taskIndex];
        currentTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(currentTask + "\n");
    }

    public static void unmark(String index) {
        int taskIndex = Integer.parseInt(index) - 1;
        Task currentTask = taskList[taskIndex];
        currentTask.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n");
        System.out.println(currentTask + "\n");
    }

    public static void listCount() {
        if (counter == 0) {
            System.out.println("You have no tasks in the list. Add some now!\n");
        } else if (counter == 1) {
            System.out.println("You have 1 task in the list.\n");
        } else {
            System.out.println("You now have " + counter + " tasks in the list\n");
        }
    }

    public static void todo(String description) {
        Task newTodo = new Todo(description);
        taskList[counter] = newTodo;
        counter++;
        System.out.println("Oscar added:\n" + newTodo + "\n");
        listCount();
    }

    public static void deadline(String details) {
        String[] split = details.split(" /by ", 2);
        String description = split[0];
        String deadline = split[1];
        Task newDeadline = new Deadline(description, deadline);
        taskList[counter] = newDeadline;
        counter++;
        System.out.println("Oscar added:\n" + newDeadline + "\n");
        listCount();
    }

    public static void event(String details) {
        String[] split = details.split(" /from | /to ");
        String description = split[0];
        String start = split[1];
        String end = split[2];
        Task newEvent = new Event(description, start, end);
        taskList[counter] = newEvent;
        counter++;
        System.out.println("Oscar added:\n" + newEvent + "\n");
        listCount();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        greet();

        while (running) {
            // Obtain command entered by user
            String userInput = scanner.nextLine();
            String[] split = userInput.split(" ", 2);
            String command = split[0];
            String details = userInput.length() > command.length() ? split[1] : "";
            switch (command) {
                // Exit programme if user enters "bye" command
                case "bye":
                    bye();
                    running = false;
                    break;

                // Display text stored by user in chronological order if
                // user enters "list" command
                case "list":
                    list();
                    break;

                // Mark task as done if user enters "mark" command
                case "mark":
                    mark(details);
                    break;

                // Mark task as not done if user enters "unmark" command
                case "unmark":
                    unmark(details);
                    break;

                // Create a new todo task
                case "todo":
                    todo(details);
                    break;

                // Create a new deadline task
                case "deadline":
                    deadline(details);
                    break;

                // Create a new event task
                case "event":
                    event(details);
                    break;

                // Default response for unknown commands
                default:
                    System.out.println("Sorry I do not recognise this command\n");

            }
        }
        scanner.close();
    }
}
