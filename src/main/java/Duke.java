import java.util.Scanner;

public class Duke {

    private static final String chatbotName = "Gobble Gobble";
    private static final String lineSeparator = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(Duke.lineSeparator + "\n" + "Hello! I'm " + Duke.chatbotName + "\n" +
                "What can I do for you?" + "\n" + Duke.lineSeparator);

        Scanner scanner = new Scanner(System.in);

        // Data
        Task[] taskList = new Task[100];
        int counter = 0;

        while (true) {
            String userInput = scanner.nextLine().trim();// trim() removes leading and trailing spaces
            String command = userInput.split(" ", 2)[0].toLowerCase();
            // Guard bye
            if (command.equals("bye")) {
                goodbye();
                break;
            }

            // Guard list
            if (command.equals("list")) {
                list(taskList, counter);
                continue;
            }

            switch (command) {
                case "mark":
                    mark(taskList, userInput);
                    break;
                case "unmark":
                    unmark(taskList, userInput);
                    break;
                case "todo":
                    addTodo(taskList, counter, userInput);
                    counter++;
                    break;
                case "deadline":
                    addDeadline(taskList, counter, userInput);
                    counter++;
                    break;
                case "event":
                    addEvent(taskList, counter, userInput);
                    counter++;
                    break;
                default:
                    add(taskList, counter, userInput);
                    counter++;
                    break;
            }
        }
        scanner.close();
    }

    /**
     * Display goodbye message.
     */
    public static void goodbye() {
        System.out.println(Duke.lineSeparator + "\n" + "Bye. Hope to see you again soon!" + "\n" + Duke.lineSeparator);
    }

    /**
     * Display ordered list of tasks.
     *
     * @param taskList list of tasks.
     * @param counter  number of tasks.
     */
    public static void list(Task[] taskList, int counter) {
        // Display Ordered list
        System.out.println(Duke.lineSeparator);
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < counter; i++) {
            Task task = taskList[i];
            String description = task.toString();
            System.out.printf("%d. %s\n", i + 1, description);
        }
        System.out.println(Duke.lineSeparator);
    }

    /**
     * Add task to task list.
     *
     * @param taskList  list of tasks.
     * @param counter   number of tasks.
     * @param userInput user input.
     */
    public static void add(Task[] taskList, int counter, String userInput) {
        // Save user input
        Task userTask = new Task(getDescriptionFromInput(userInput));

        // Display user input has been added
        addTask(taskList, counter, userTask);
        Duke.printAddResult(counter, userTask);
    }


    public static void addTodo(Task[] taskList, int counter, String userInput) {
        ToDoTask userTask = new ToDoTask(getDescriptionFromInput(userInput));

        addTask(taskList, counter, userTask);

        // Display user input has been added
        Duke.printAddResult(counter, userTask);
    }

    public static void addDeadline(Task[] taskList, int counter, String userInput) {
        String by = userInput.split("/by")[1].trim();
        String description = userInput.split("/by")[0].trim().split(" ", 2)[1];
        DeadlineTask userTask = new DeadlineTask(description, by);
        addTask(taskList, counter, userTask);
        Duke.printAddResult(counter, userTask);
    }

    public static void addEvent(Task[] taskList, int counter, String userInput) {
        String date = userInput.split("/from")[1];
        String from = date.split("/to")[0].trim();
        String to = date.split("/to")[1].trim();
        String description = userInput.split("/from")[0].trim().split(" ", 2)[1];

        EventTask userTask = new EventTask(description, from, to);

        addTask(taskList, counter, userTask);
        Duke.printAddResult(counter, userTask);
    }

    /**
     * Mark task as done.
     *
     * @param taskList  list of tasks.
     * @param userInput user input.
     */
    public static void mark(Task[] taskList, String userInput) {
        // Get task number from user input (e.g. "done 1)
        int taskNumber = getTaskNumber(userInput);
        Task task = taskList[taskNumber];

        // Mark task as done
        task.mark();

        // Display task has been marked as done
        System.out.println(Duke.lineSeparator + "\n" + "Nice! I've marked this task as done:" + "\n" +
                task + "\n" + Duke.lineSeparator);
    }

    /**
     * Unmark task as done.
     *
     * @param taskList  list of tasks.
     * @param userInput user input.
     */
    public static void unmark(Task[] taskList, String userInput) {
        // Get task number from user input (e.g. "done 1)
        int taskNumber = getTaskNumber(userInput);
        Task task = taskList[taskNumber];
        // Unmark task
        task.unmark();

        // Display task has been deleted
        System.out.println(Duke.lineSeparator + "\n" + "OK, I've marked this task as not done yet:" + "\n" +
                task + "\n" + Duke.lineSeparator);
    }

    public static void addTask(Task[] taskList, int counter, Task task) {
        taskList[counter] = task;
    }

    /**
     * Get task number from user input such as "Mark 1".
     *
     * @param userInput user input.
     * @return task number.
     */
    public static int getTaskNumber(String userInput) {
        return Integer.parseInt(userInput.split(" ")[1]) - 1;
    }

    public static void printAddResult(int counter, Task task) {
        System.out.println(Duke.lineSeparator + "\n" + "Got it. I've added this task:" + "\n"
                + task.toString() + "\n" + "Now you have " + (counter + 1) + " tasks in the list." + "\n"
                + Duke.lineSeparator);
    }

    public static String getDescriptionFromInput(String userInput) {
        return userInput.split(" ", 2)[1];
    }
}
