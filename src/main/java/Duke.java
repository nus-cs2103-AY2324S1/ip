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
    static int inputNum;

    public static void main(String[] args) throws DukeException {
        inputNum = 0;
        // Send welcome message
        System.out.println(
                "    ____________________________________________________________\n" +
                "     Hello! I'm BbabBBB\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");

        try {
            loadTasksFromFile("tasks.txt");
            // System.out.println("Here is a list of your saved tasks:");
            // printFileContents("tasks.txt");
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Here are the tasks in your list:\n" +
                            listTasks(inputNum) +
                            "    ____________________________________________________________\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found, proceed to use bot normally.");
        } catch (IOException e) {
            System.out.println("e.getMessage");
        }

        // Implement function to read user input via keyboard
        Scanner scan = new Scanner(System.in);

        while (true) {
            try {
                String userInput = scan.nextLine().trim();
                if (Objects.equals(userInput, "bye")) {
                    updateTaskFile();
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
                    updateTaskFile();

                } else if (userInput.startsWith("unmark")) {
                    unmarkTask(userInput, inputNum);
                    updateTaskFile();

                } else if (userInput.startsWith("todo")) {
                    if (userInput.equals("todo")) {
                        throw new EmptyTaskException("todo");
                    }
                    String taskName = userInput.substring("todo".length()).trim();
                    makeToDo(taskName, inputNum);
                    inputNum++;
                    updateTaskFile();

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
                    updateTaskFile();

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
                    updateTaskFile();

                } else if (userInput.startsWith("delete")) {
                    deleteTask(userInput, inputNum);
                    inputNum--;
                    updateTaskFile();
                }
                else {
                    throw new InvalidInputException("Invalid Input");
                }
            } catch (InvalidInputException | EmptyTaskException | EmptyDateException | OutOfRangeException | IOException e) {
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
                inputArrayString += "     " + num + ". " + currentTask.statusAndTask() + "\n";
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
    private static void markTask(String userInput, int inputNum) throws EmptyTaskException, OutOfRangeException, IOException {
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
                "       " + currentTask.statusAndTask() + "\n" +
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

    private static void loadTasksFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromString(line);
                taskArray.add(task);
                inputNum++;
            }
        }
    }

    private static Task parseTaskFromString(String line) {
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String taskDescription = parts[2].trim();

        if (taskType.equals("T")) {
            Task task = new ToDo(taskDescription);
            setStatus(task, isDone);
            return task;
        } else if (taskType.equals("D")) {
            String by = parts[3].trim();
            Task task = new Deadline(taskDescription, by);
            setStatus(task, isDone);
            return task;
        } else if (taskType.equals("E")) {
            String start = parts[3].trim();
            String end = parts[4].trim();
            Task task = new Event(taskDescription, start, end);
            setStatus(task, isDone);
            return task;
        } else {
            // Handle unrecognized task type
            return null;
        }
    }

    private static void setStatus(Task task, boolean isDone) {
        if (isDone) {
            task.markDone();
        } else {
            task.unmarkDone();
        }
    }

    private static void generateTaskFileContent() throws IOException {
        StringBuilder content = new StringBuilder();
        for (Task task : taskArray) {
            content.append(writeTaskLine(task));
        }
        writeToFile("tasks.txt", content.toString());
    }

    private static void updateTaskFile() throws IOException {
        try {
            generateTaskFileContent();
        } catch (IOException e) {
            System.out.println("Something went wrong while updating the task file: " + e.getMessage());
        }
    }



}
