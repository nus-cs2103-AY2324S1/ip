import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        // Send welcome message
        System.out.println(
                "    ____________________________________________________________\n" +
                "     Hello! I'm BbabBBB\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");

        // Implement function to read user input via keyboard
        Scanner scan = new Scanner(System.in);
        Task[] taskArray = new Task[100];
        boolean[] statusArray = new boolean[100];

        int inputNum = 0;

        while (true) {
            try {
                String userInput = scan.nextLine().trim();
                if (Objects.equals(userInput, "bye")) {
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     Bye. Hope to see you again soon!\n" +
                                    "    ____________________________________________________________\n");
                    break;

                } else if (Objects.equals(userInput, "list")) {
                    listTasks(taskArray);

                } else if (userInput.startsWith("mark")) {
                    markTask(userInput, inputNum, taskArray);

                } else if (userInput.startsWith("unmark")) {
                    unmarkTask(userInput, inputNum, taskArray);

                } else if (userInput.startsWith("todo")) {
                    if (userInput.equals("todo")) {
                        throw new EmptyTaskException("todo");
                    }
                    String taskName = userInput.substring("todo".length()).trim();
                    makeToDo(taskName, inputNum, taskArray);
                    inputNum++;

                } else if (userInput.startsWith("deadline")) {
                    if (userInput.equals("deadline")) {
                        throw new EmptyTaskException("deadline");
                    } else if (userInput.endsWith("/by")) {
                        throw new EmptyDateException("deadline");
                    }
                    String[] deadlineParts = getDeadlineParts(userInput);
                    String taskName = deadlineParts[0];
                    String by = deadlineParts[1];
                    makeDeadline(taskName, by, inputNum, taskArray);
                    inputNum++;

                } else if (userInput.startsWith("event")) {
                    if (userInput.equals("event")) {
                        throw new EmptyTaskException("event");
                    }
                    String[] eventParts = getEventParts(userInput);
                    String taskName = eventParts[0];
                    String start = eventParts[1];
                    String end = eventParts[2];
                    makeEvent(taskName, start, end, inputNum, taskArray);
                    inputNum++;

                } else {
                    throw new InvalidInputException("Invalid Input");
                }
            } catch (InvalidInputException | EmptyTaskException | EmptyDateException | OutOfRangeException e) {
                System.out.println(e);
            }
        }
        scan.close();
    }

    private static void listTasks(Task[] taskArray) {
        String inputArrayString = "";
        for (int i = 0; i < taskArray.length; i++) {
            Task currentTask = taskArray[i];
            if (taskArray[i] != null) {
                int num = i + 1;
                inputArrayString += "     " + num + ". " + currentTask.toString() + "\n";
            } else {
                break;
            }
        }
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Here are the tasks in your list:\n" +
                        inputArrayString +
                        "    ____________________________________________________________\n");

    }

    private static void markTask(String userInput, int inputNum, Task[] taskArray) throws EmptyTaskException, OutOfRangeException {
        if (userInput.equals("mark")) {
            throw new EmptyTaskException("mark");
        }
        String[] parts = userInput.split("\\s+");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex >= inputNum || taskIndex < 0 || taskArray[taskIndex] == null) {
            throw new OutOfRangeException("Mark");
        }
        Task currentTask = taskArray[taskIndex];
        currentTask.markDone();
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done:\n" +
                        "       " + currentTask.toString() + "\n" +
                        "    ____________________________________________________________\n");
    }

    private static void unmarkTask(String userInput, int inputNum, Task[] taskArray) throws EmptyTaskException, OutOfRangeException {
        if (userInput.equals("unmark")) {
            throw new EmptyTaskException("unmark");
        }
        String[] parts = userInput.split("\\s+");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex >= inputNum || taskIndex < 0 || taskArray[taskIndex] == null) {
            throw new OutOfRangeException("Unmark");
        }
        Task currentTask = taskArray[taskIndex];
        currentTask.unmarkDone();
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     OK, I've marked this task as not done yet:\n" +
                        "       " + currentTask.toString() + "\n" +
                        "    ____________________________________________________________\n");
    }

    private static void makeToDo(String taskName, int inputNum, Task[] taskArray) {
        taskArray[inputNum] = new ToDo(taskName);
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Got it. I've added this task:\n" +
                        "       " + taskArray[inputNum].toString() + "\n" +
                        "     Now you have " + (inputNum + 1) + " task(s) in the list.\n" +
                        "    ____________________________________________________________\n");
    }

    private static String[] getDeadlineParts(String userInput) throws EmptyDateException {
        String description = userInput.substring("deadline".length()).trim();
        String[] parts = description.split("/by");
        if (parts.length == 1) {
            throw new EmptyDateException("deadline");
        }
        return new String[]{parts[0].trim(), parts[1].trim()};
    }

    private static void makeDeadline(String taskName, String by, int inputNum, Task[] taskArray) {
        taskArray[inputNum] = new Deadline(taskName, by);
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Got it. I've added this task:\n" +
                        "       " + taskArray[inputNum].toString() + "\n" +
                        "     Now you have " + (inputNum + 1) + " task(s) in the list.\n" +
                        "    ____________________________________________________________\n");
    }

    private static void makeEvent(String taskName, String start, String end, int inputNum, Task[] taskArray) {
        taskArray[inputNum] = new Event(taskName, start, end);
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Got it. I've added this task:\n" +
                        "       " + taskArray[inputNum].toString() + "\n" +
                        "     Now you have " + (inputNum + 1) + " task(s) in the list.\n" +
                        "    ____________________________________________________________\n");
    }

    private static String[] getEventParts(String userInput) throws EmptyDateException {
        String description = userInput.substring("event".length()).trim();
        String[] partsA = description.split("/from");
        String taskName = partsA[0].trim();
        String[] partsB = partsA[1].split("/to");
        if (partsB.length == 1 || partsB[0].trim().isEmpty() || partsB[1].trim().isEmpty()) {
            throw new EmptyDateException("event");
        }
        String start = partsB[0].trim();
        String end = partsB[1].trim();
        return new String[]{taskName, start, end};
    }
}
