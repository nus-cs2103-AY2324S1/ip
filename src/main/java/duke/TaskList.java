package duke;

import exceptions.EmptyTaskException;
import exceptions.EmptyDateException;
import exceptions.OutOfRangeException;
import java.io.IOException;
import java.lang.reflect.Array;
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

    /**
     * Lists the tasks stored in the task array.
     */
     public static String listTasks() {
        String inputArrayString = "";
        int num = 1;
        for (Task task : taskArray) {
            inputArrayString += num + ". " + task.statusAndTask() + "\n";
            num++;
        }
        return inputArrayString;
    }

    public static void markTask(String userInput) throws EmptyTaskException, OutOfRangeException, IOException {
        if (userInput.equals("mark")) {
            throw new EmptyTaskException("mark");
        }
        String[] parts = userInput.split("\\s+");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex >= taskArray.size() || taskIndex < 0 || taskArray.get(taskIndex) == null) {
            throw new OutOfRangeException("Mark");
        }
        Task currentTask = taskArray.get(taskIndex);
        currentTask.markDone();
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(currentTask.statusAndTask());
    }

    public static void unmarkTask(String userInput) throws EmptyTaskException, OutOfRangeException {
        if (userInput.equals("unmark")) {
            throw new EmptyTaskException("unmark");
        }
        String[] parts = userInput.split("\\s+");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex >= taskArray.size() || taskIndex < 0 || taskArray.get(taskIndex) == null) {
            throw new OutOfRangeException("Unmark");
        }
        Task currentTask = taskArray.get(taskIndex);
        currentTask.unmarkDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currentTask.statusAndTask());
    }

    public static void makeToDo(String userInput) {
        String taskName = userInput.substring("todo".length()).trim();
        taskArray.add(new ToDo(taskName));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskArray.get(taskArray.size() - 1).statusAndTask());
        System.out.println("Now you have " + taskArray.size() + " task(s) in the list.");
    }

    public static void makeDeadline(String userInput) throws EmptyDateException {
        String description = userInput.substring("deadline".length()).trim();
        String[] parts = description.split("/by");
        if (parts.length == 1) {
            throw new EmptyDateException("deadline");
        }
        String[] deadlineParts = {parts[0].trim(), parts[1].trim()};
        String taskName = deadlineParts[0];
        LocalDateTime by = Storage.saveAsDate(deadlineParts[1]);
        taskArray.add(new Deadline(taskName, by));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskArray.get(taskArray.size() - 1).statusAndTask());
        System.out.println("Now you have " + taskArray.size() + " task(s) in the list.");
    }

    public static void makeEvent(String userInput) throws EmptyDateException {
        String description = userInput.substring("event".length()).trim();
        String[] partsA = description.split("/from");
        String taskName = partsA[0].trim();
        String[] partsB = partsA[1].split("/to");
        if (partsB.length == 1 || partsB[0].trim().isEmpty() || partsB[1].trim().isEmpty()) {
            throw new EmptyDateException("event");
        }
        String start = partsB[0].trim();
        String end = partsB[1].trim();
        String[] eventParts = {taskName, start, end};
        LocalDateTime startDateTime = Storage.saveAsDate(eventParts[1]);
        LocalDateTime endDateTime = Storage.saveAsDate(eventParts[2]);
        taskArray.add(new Event(taskName, startDateTime, endDateTime));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskArray.get(taskArray.size() - 1).statusAndTask());
        System.out.println("Now you have " + taskArray.size() + " task(s) in the list.");
    }

    public static void deleteTask(String userInput) throws EmptyTaskException, OutOfRangeException {
        if (userInput.equals("delete")) {
            throw new EmptyTaskException("delete");
        }
        String[] parts = userInput.split("\\s+");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex >= taskArray.size() || taskIndex < 0 || taskArray.get(taskIndex) == null) {
            throw new OutOfRangeException("Delete");
        }
        Task currentTask = taskArray.get(taskIndex);
        taskArray.remove(currentTask);
        System.out.println("Noted. I've removed this task:");
        System.out.println(currentTask.statusAndTask());
        System.out.println("Now you have " + taskArray.size() + " task(s) in the list.");
    }

    public static void updateTaskFile() throws IOException {
        try {
            Storage.generateTaskFileContent(taskArray);
        } catch (IOException e) {
            System.out.println("Something went wrong while updating the task file: " + e.getMessage());
        }
    }

    public String find(String userInput) throws EmptyTaskException {
        if (userInput.equals("find")) {
            throw new EmptyTaskException("find");
        }
        String[] parts = userInput.split("\\s+");
        String keyword = parts[1];
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskArray) {
            if (task.getName().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        String foundTasksString = "";
        int num = 1;
        for (Task task : foundTasks) {
            foundTasksString += num + ". " + task.statusAndTask() + "\n";
            num++;
        }
        return foundTasksString;
    }

    public Task getTask(int i) {
         return taskArray.get(i);
    }
}
