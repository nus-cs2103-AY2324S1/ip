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
            String userInput = scanner.nextLine().trim().toLowerCase(); // trim() removes leading and trailing spaces

            // Guard bye
            if (userInput.equals("bye")) {
                goodbye();
                break;
            }

            // Guard list
            if (userInput.equals("list")) {
                list(taskList, counter);
                continue;
            }

            String action = userInput.split(" ")[0];

            switch (action) {
                case "mark":
                    mark(taskList, userInput);
                    break;
                case "unmark":
                    unmark(taskList, userInput);
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
        for (int i = 0; i < counter; i++) {
            Task task = taskList[i];
            String status = task.getStatusIcon();
            String description = task.getDescription();
            System.out.printf("%d. %s %s\n", i + 1, status, description);
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
        Task userTask = new Task(userInput);
        taskList[counter] = userTask;

        // Display user input has been added
        System.out.println(Duke.lineSeparator + "\n" + "added: " + userTask.getDescription() + "\n" + Duke.lineSeparator);
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
                task.getStatusIcon() + " " + task.getDescription() + "\n" + Duke.lineSeparator);
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
                task.getStatusIcon() + " " + task.getDescription() + "\n" + Duke.lineSeparator);
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
}
