import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskArray;

    public TaskList() {
        taskArray = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        taskArray = tasks;
    }

    public static int loadTasksFromFile(String filePath) throws IOException {
        int inputNum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromString(line);
                taskArray.add(task);
                inputNum++;
            }
        }
        return inputNum;
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
            LocalDateTime by = Storage.saveAsDate(parts[3].trim());
            Task task = new Deadline(taskDescription, by);
            setStatus(task, isDone);
            return task;
        } else if (taskType.equals("E")) {
            LocalDateTime start = Storage.saveAsDate(parts[3].trim());
            LocalDateTime end = Storage.saveAsDate(parts[4].trim());
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




    /**
     * Lists the tasks stored in the task array.
     *
     * @param inputNum The number of tasks entered.
     */
     public static String listTasks(int inputNum) {
        String inputArrayString = "";
        int num = 1;
        for (Task task : taskArray) {
            inputArrayString += num + ". " + task.statusAndTask() + "\n";
            num++;
        }
        return inputArrayString;
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param userInput The user input containing the task index.
     * @param inputNum  The number of tasks entered.
     * @throws EmptyTaskException  If the task is missing.
     * @throws OutOfRangeException If the task index is out of range.
     */
    public static void markTask(String userInput, int inputNum) throws EmptyTaskException, OutOfRangeException, IOException {
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
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(currentTask.statusAndTask());
    }

    /**
     * Unmarks a task as done based on user input.
     *
     * @param userInput The user input containing the task index.
     * @param inputNum  The number of tasks entered.
     * @throws EmptyTaskException  If the task is missing.
     * @throws OutOfRangeException If the task index is out of range.
     */
    public static void unmarkTask(String userInput, int inputNum) throws EmptyTaskException, OutOfRangeException {
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
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currentTask.statusAndTask());
    }

    /**
     * Creates and adds a new ToDo task.
     *
     * @param taskName  The name of the ToDo task.
     * @param inputNum  The number of tasks entered.
     */
    public static void makeToDo(String taskName, int inputNum) {
        taskArray.add(new ToDo(taskName));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskArray.get(inputNum).statusAndTask());
        System.out.println("Now you have " + (inputNum + 1) + " task(s) in the list.");
    }

    /**
     * Extracts and returns parts for creating a Deadline task.
     *
     * @param userInput The user input containing task details.
     * @return An array containing the task name and due date.
     * @throws EmptyDateException If the due date is missing.
     */
    public static String[] getDeadlineParts(String userInput) throws EmptyDateException {
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
    public static void makeDeadline(String taskName, LocalDateTime by, int inputNum) {
        taskArray.add(new Deadline(taskName, by));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskArray.get(inputNum).statusAndTask());
        System.out.println("Now you have " + (inputNum + 1) + " task(s) in the list.");
    }

    /**
     * Extracts and returns parts for creating an Event task.
     *
     * @param userInput The user input containing task details.
     * @return An array containing the task name, start date, and end date.
     * @throws EmptyDateException If the start or end date is missing.
     */
    public static String[] getEventParts(String userInput) throws EmptyDateException {
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
    public static void makeEvent(String taskName, LocalDateTime start, LocalDateTime end, int inputNum) {
        taskArray.add(new Event(taskName, start, end));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskArray.get(inputNum).statusAndTask());
        System.out.println("Now you have " + (inputNum + 1) + " task(s) in the list.");
    }

    /**
     * Deletes a task based on user input.
     *
     * @param userInput The user input containing the task index.
     * @param inputNum  The number of tasks entered.
     * @throws EmptyTaskException  If the task is missing.
     * @throws OutOfRangeException If the task index is out of range.
     */
    public static void deleteTask(String userInput, int inputNum) throws EmptyTaskException, OutOfRangeException {
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
        System.out.println("Noted. I've removed this task:");
        System.out.println(currentTask.statusAndTask());
        System.out.println("Now you have " + (inputNum - 1) + " task(s) in the list.");
    }

    public static void updateTaskFile() throws IOException {
        try {
            Storage.generateTaskFileContent(taskArray);
        } catch (IOException e) {
            System.out.println("Something went wrong while updating the task file: " + e.getMessage());
        }
    }


}
