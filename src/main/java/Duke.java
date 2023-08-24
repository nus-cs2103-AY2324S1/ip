import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String chatbotName = "Gobble Gobble";
    private static final String lineSeparator = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(Duke.lineSeparator + "\n" + "Hello! I'm " + Duke.chatbotName + "\n" +
                "What can I do for you?" + "\n" + Duke.lineSeparator);

        Scanner scanner = new Scanner(System.in);

        // Data
        ArrayList<Task> taskList = new ArrayList<>();


        while (true) {
            String userInput = scanner.nextLine().trim();// trim() removes leading and trailing spaces

            try {
                Command command = Command.valueOf(userInput.split(" ", 2)[0].toUpperCase());
                // Guard bye
                if (command.equals(Command.BYE)) {
                    goodbye();
                    break;
                }

                // Guard list
                if (command.equals(Command.LIST)) {
                    list(taskList);
                    continue;
                }

                if (userInput.split(" ").length < 2) {
                    throw new DukeException("☹ OOPS!!! The description cannot be empty.");
                }

                switch (command) {
                    case MARK:
                        mark(taskList, userInput);
                        break;
                    case UNMARK:
                        unmark(taskList, userInput);
                        break;
                    case TODO:
                        addTodo(taskList, userInput);
                        break;
                    case DEADLINE:
                        addDeadline(taskList, userInput);
                        break;
                    case EVENT:
                        addEvent(taskList, userInput);
                        break;
                    case DELETE:
                        deleteTask(taskList, userInput);
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(Duke.lineSeparator + "\n" + e.getMessage() + "\n" + Duke.lineSeparator);
            } catch (Exception e) {
                System.out.println(Duke.lineSeparator + "\n" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n" + Duke.lineSeparator);
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
     */
    public static void list(ArrayList<Task> taskList) {
        // Display Ordered list
        System.out.println(Duke.lineSeparator);
        System.out.println("Here are the tasks in your list:");

        int i = 0;
        for (Task task : taskList) {
            String description = task.toString();
            System.out.printf("%d. %s\n", i + 1, description);
            i++;
        }
        System.out.println(Duke.lineSeparator);
    }

    /**
     * Add task to task list.
     *
     * @param taskList  list of tasks.
     * @param userInput user input.
     */
    public static void add(ArrayList<Task> taskList, String userInput) throws DukeException {
        String description = userInput.split(" ", 2)[1];
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        // Save user input
        Task userTask = new Task(description);

        // Display user input has been added
        addTask(taskList, userTask);
        Duke.printAddResult(taskList.size(), userTask);
    }


    public static void addTodo(ArrayList<Task> taskList, String userInput) throws DukeException {
        String description = userInput.split(" ", 2)[1];
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        ToDoTask userTask = new ToDoTask(description);

        addTask(taskList, userTask);

        // Display user input has been added
        Duke.printAddResult(taskList.size(), userTask);
    }

    public static void addDeadline(ArrayList<Task> taskList, String userInput) throws DukeException {
        if (userInput.split("/by").length < 2) {
            throw new DukeException("☹ OOPS!!! The date of deadline cannot be empty.");
        }
        String by = userInput.split("/by")[1].trim();

        String description = userInput.split("/by")[0].trim().split(" ", 2)[1];
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        DeadlineTask userTask = new DeadlineTask(description, by);
        addTask(taskList, userTask);
        Duke.printAddResult(taskList.size(), userTask);
    }

    public static void addEvent(ArrayList<Task> taskList, String userInput) throws DukeException {
        if (userInput.split("/from").length < 2) {
            throw new DukeException("☹ OOPS!!! The date of event cannot be empty.");
        }

        String date = userInput.split("/from")[1];

        String from = date.split("/to")[0].trim();
        if (from.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The date of 'from' cannot be empty.");
        }

        if (userInput.split("/to").length < 2) {
            throw new DukeException("☹ OOPS!!! The date of 'to' cannot be empty.");
        }
        String to = date.split("/to")[1].trim();

        String description = userInput.split("/from")[0].trim().split(" ", 2)[1];
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }

        EventTask userTask = new EventTask(description, from, to);

        addTask(taskList, userTask);
        Duke.printAddResult(taskList.size(), userTask);
    }

    /**
     * Mark task as done.
     *
     * @param taskList  list of tasks.
     * @param userInput user input.
     */
    public static void mark(ArrayList<Task> taskList, String userInput) throws DukeException {
        // Get task number from user input (e.g. "done 1)
        int taskNumber = getTaskNumber(userInput);
        Task task = taskList.get(taskNumber);

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
    public static void unmark(ArrayList<Task> taskList, String userInput) throws DukeException {
        // Get task number from user input (e.g. "done 1)
        int taskNumber = getTaskNumber(userInput);
        Task task = taskList.get(taskNumber);
        // Unmark task
        task.unmark();

        // Display task has been deleted
        System.out.println(Duke.lineSeparator + "\n" + "OK, I've marked this task as not done yet:" + "\n" +
                task + "\n" + Duke.lineSeparator);
    }

    public static void addTask(ArrayList<Task> taskList, Task task) {
        taskList.add(task);
    }

    public static void deleteTask(ArrayList<Task> taskList, String userInput) throws DukeException {
        int number = getTaskNumber(userInput);
        taskList.remove(number);

        System.out.println(Duke.lineSeparator + "\n" + "Noted. I've removed this task:" + "\n"
                + userInput + "\n" + "Now you have " + (taskList.size()) + " tasks in the list." + "\n"
                + Duke.lineSeparator);
    }

    /**
     * Get task number from user input such as "Mark 1".
     *
     * @param userInput user input.
     * @return task number.
     */
    public static int getTaskNumber(String userInput) throws DukeException {
        if (userInput.split(" ").length < 2) {
            throw new DukeException("☹ OOPS!!! The task number cannot be empty.");
        }
        return Integer.parseInt(userInput.split(" ")[1]) - 1;
    }

    public static void printAddResult(int size, Task task) {
        System.out.println(Duke.lineSeparator + "\n" + "Got it. I've added this task:" + "\n"
                + task.toString() + "\n" + "Now you have " + (size) + " tasks in the list." + "\n"
                + Duke.lineSeparator);
    }


}
