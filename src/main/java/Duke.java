import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    /**
     * The main method of the Duke application.
     * Handles user interactions and manages task-related commands.
     *
     * @param args Command-line arguments (not used).
     * @throws DukeException If an error occurs during user input processing.
     */

    static ArrayList<Task> taskArray = new ArrayList<>();

    public static void main(String[] args) throws DukeException {
        // Send welcome message
        System.out.println(
                "    ____________________________________________________________\n" +
                "     Hello! I'm BbabBBB\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");

        try {
            System.out.println("Here is a list of your saved tasks:");
            printFileContents("tasks.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found, proceed to use bot normally.");
        }

        // Implement function to read user input via keyboard
        Scanner scan = new Scanner(System.in);

        int inputNum = 0;

        while (true) {
            try {
                String userInput = scan.nextLine().trim();
                if (Objects.equals(userInput, "bye")) {
                    String file = "tasks.txt";
                    try {
                        String textToWrite = "";
                        for (Task task: taskArray) {
                            textToWrite += writeTaskLine(task);
                        }
                        writeToFile(file, textToWrite);
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     Bye. Hope to see you again soon!\n" +
                                    "    ____________________________________________________________\n");
                    break;

                } else if (Objects.equals(userInput, "list")) {
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     Here are the tasks in your list:\n" +
                                    listTasks(inputNum) +
                                    "    ____________________________________________________________\n");

                } else if (userInput.startsWith("mark")) {
                    markTask(userInput, inputNum);

                } else if (userInput.startsWith("unmark")) {
                    unmarkTask(userInput, inputNum);

                } else if (userInput.startsWith("todo")) {
                    if (userInput.equals("todo")) {
                        throw new EmptyTaskException("todo");
                    }
                    String taskName = userInput.substring("todo".length()).trim();
                    makeToDo(taskName, inputNum);
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
                    makeDeadline(taskName, by, inputNum);
                    inputNum++;

                } else if (userInput.startsWith("event")) {
                    if (userInput.equals("event")) {
                        throw new EmptyTaskException("event");
                    }
                    String[] eventParts = getEventParts(userInput);
                    String taskName = eventParts[0];
                    String start = eventParts[1];
                    String end = eventParts[2];
                    makeEvent(taskName, start, end, inputNum);
                    inputNum++;

                } else if (userInput.startsWith("delete")) {
                    deleteTask(userInput, inputNum);
                    inputNum--;
                }
                else {
                    throw new InvalidInputException("Invalid Input");
                }
            } catch (InvalidInputException | EmptyTaskException | EmptyDateException | OutOfRangeException e) {
                System.out.println(e);
            }
        }
        scan.close();
    }

    /**
     * Lists the tasks stored in the task array.
     *
     * @param inputNum The number of tasks entered.
     */
    private static String listTasks(int inputNum) {
        String inputArrayString = "";
        for (int i = 0; i < inputNum; i++) {
            Task currentTask = taskArray.get(i);
            if (taskArray.get(i) != null) {
                int num = i + 1;
                inputArrayString += "     " + num + ". " + currentTask.toString() + "\n";
            } else {
                break;
            }
        }
        return inputArrayString;
    }

    private static String writeTaskLine(Task task) {
        Boolean isDone = task.getStatus();
        String taskLine = task.getType() + " | " + (isDone ? "1" : "0") + " | " + task.getDescription() + "\n";
        return taskLine;
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param userInput The user input containing the task index.
     * @param inputNum  The number of tasks entered.
     * @throws EmptyTaskException  If the task is missing.
     * @throws OutOfRangeException If the task index is out of range.
     */
    private static void markTask(String userInput, int inputNum) throws EmptyTaskException, OutOfRangeException {
        if (userInput.equals("mark")) {
            throw new EmptyTaskException("mark");
        }
        String[] parts = userInput.split("\\s+");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex >= inputNum || taskIndex < 0 || taskArray.get(taskIndex) == null) {
            throw new OutOfRangeException("Mark");
        }
        Task currentTask = taskArray.get(taskIndex);
        currentTask.markDone();
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done:\n" +
                        "       " + currentTask.statusAndTask() + "\n" +
                        "    ____________________________________________________________\n");
    }

    /**
     * Unmarks a task as done based on user input.
     *
     * @param userInput The user input containing the task index.
     * @param inputNum  The number of tasks entered.
     * @throws EmptyTaskException  If the task is missing.
     * @throws OutOfRangeException If the task index is out of range.
     */
    private static void unmarkTask(String userInput, int inputNum) throws EmptyTaskException, OutOfRangeException {
        if (userInput.equals("unmark")) {
            throw new EmptyTaskException("unmark");
        }
        String[] parts = userInput.split("\\s+");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex >= inputNum || taskIndex < 0 || taskArray.get(taskIndex) == null) {
            throw new OutOfRangeException("Unmark");
        }
        Task currentTask = taskArray.get(taskIndex);
        currentTask.unmarkDone();
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     OK, I've marked this task as not done yet:\n" +
                        "       " + currentTask.statusAndTask() + "\n" +
                        "    ____________________________________________________________\n");
    }

    /**
     * Creates and adds a new ToDo task.
     *
     * @param taskName  The name of the ToDo task.
     * @param inputNum  The number of tasks entered.
     */
    private static void makeToDo(String taskName, int inputNum) {
        taskArray.add(new ToDo(taskName));
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Got it. I've added this task:\n" +
                        "       " + taskArray.get(inputNum).statusAndTask() + "\n" +
                        "     Now you have " + (inputNum + 1) + " task(s) in the list.\n" +
                        "    ____________________________________________________________\n");
    }

    /**
     * Extracts and returns parts for creating a Deadline task.
     *
     * @param userInput The user input containing task details.
     * @return An array containing the task name and due date.
     * @throws EmptyDateException If the due date is missing.
     */
    private static String[] getDeadlineParts(String userInput) throws EmptyDateException {
        String description = userInput.substring("deadline".length()).trim();
        String[] parts = description.split("/by");
        if (parts.length == 1) {
            throw new EmptyDateException("deadline");
        }
        return new String[]{parts[0].trim(), parts[1].trim()};
    }

    /**
     * Creates and adds a new Deadline task.
     *
     * @param taskName  The name of the Deadline task.
     * @param by        The due date of the Deadline task.
     * @param inputNum  The number of tasks entered.
     */
    private static void makeDeadline(String taskName, String by, int inputNum) {
        taskArray.add(new Deadline(taskName, by));
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Got it. I've added this task:\n" +
                        "       " + taskArray.get(inputNum).statusAndTask() + "\n" +
                        "     Now you have " + (inputNum + 1) + " task(s) in the list.\n" +
                        "    ____________________________________________________________\n");
    }

    /**
     * Extracts and returns parts for creating an Event task.
     *
     * @param userInput The user input containing task details.
     * @return An array containing the task name, start date, and end date.
     * @throws EmptyDateException If the start or end date is missing.
     */
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

    /**
     * Creates and adds a new Event task.
     *
     * @param taskName  The name of the Event task.
     * @param start     The start date of the Event task.
     * @param end       The end date of the Event task.
     * @param inputNum  The number of tasks entered.
     */
    private static void makeEvent(String taskName, String start, String end, int inputNum) {
        taskArray.add(new Event(taskName, start, end));
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       " + taskArray.get(inputNum).statusAndTask() + "\n" +
                "     Now you have " + (inputNum + 1) + " task(s) in the list.\n" +
                "    ____________________________________________________________\n");
    }

    /**
     * Deletes a task based on user input.
     *
     * @param userInput The user input containing the task index.
     * @param inputNum  The number of tasks entered.
     * @throws EmptyTaskException  If the task is missing.
     * @throws OutOfRangeException If the task index is out of range.
     */
    private static void deleteTask(String userInput, int inputNum) throws EmptyTaskException, OutOfRangeException {
        if (userInput.equals("delete")) {
            throw new EmptyTaskException("delete");
        }
        String[] parts = userInput.split("\\s+");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex >= inputNum || taskIndex < 0 || taskArray.get(taskIndex) == null) {
            throw new OutOfRangeException("Delete");
        }
        Task currentTask = taskArray.get(taskIndex);
        taskArray.remove(currentTask);
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task:\n" +
                "       " + currentTask + "\n" +
                "     Now you have " + (inputNum - 1) + " task(s) in the list.\n" +
                "    ____________________________________________________________");
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }




}
