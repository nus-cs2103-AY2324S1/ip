import jdk.jfr.Event;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class BouncyBob {

    public enum TaskType {
        TODO, DEADLINE, EVENT, UNKNOWN
    }
    public enum Action {
        MARK, UNMARK, DELETE, UNKNOWN
    }

    private static TaskType getTaskType(String taskType) {
        switch (taskType.toLowerCase()) {
            case "todo":
                return TaskType.TODO;
            case "deadline":
                return TaskType.DEADLINE;
            case "event":
                return TaskType.EVENT;
            default:
                return TaskType.UNKNOWN;
        }
    }

    private static Action getAction(String action) {
        switch (action.toLowerCase()) {
            case "mark":
                return Action.MARK;
            case "unmark":
                return Action.UNMARK;
            case "delete":
                return Action.DELETE;
            default:
                return Action.UNKNOWN;
        }
    }

    private static String getTaskEvent(String input) {
        String task = input.split("/from")[0].trim();
        return task;
    }

    private static String getTaskDeadline(String input) {
        String task = input.split("/by")[0].trim();
        return task;
    }



    private static void addTaskAndPrint(String[] parts, ArrayList<Task> database) {
        TaskType taskType = getTaskType(parts[0]);
        String taskName = "";
        Task newTask = null;
        switch (taskType) {
            case TODO:
                taskName = arrayToString(parts, 1);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("Task name for 'todo' cannot be empty.");
                }
                newTask = new ToDos(taskName);
                break;
            case DEADLINE:
                taskName = arrayToString(parts, 1);
                String datetime = extractDatetime(taskName);
                if (datetime.trim().isEmpty()) {
                    throw new IllegalArgumentException("/by cannot be empty!");
                }
                taskName = getTaskDeadline(taskName);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("Task name for 'deadline' cannot be empty.");
                }
                newTask = new Deadlines(taskName, datetime);
                break;
            case EVENT:
                taskName = arrayToString(parts, 1);
                String[] fromTo = extractFromTo(taskName);
                if (fromTo[0] == null || fromTo[1] == null) {
                    throw new IllegalArgumentException("/from and /to cannot be empty!");
                }
                taskName = getTaskEvent(taskName);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("Task name for 'event' cannot be empty.");
                }
                newTask = new Events(taskName, fromTo[0], fromTo[1]);
                break;
            case UNKNOWN:
                throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
        database.add(newTask);
        TaskFileHandler.saveTasksToDisk(database);
        Ui.printTaskCount(database.size() - 1, newTask);  // Adjusted to size of ArrayList
    }

    private static String arrayToString(String[] arr, int index) {
        String combinedString = "";
        for (int i = index; i < arr.length; i++) {
            combinedString = combinedString + arr[i];
            if (i != arr.length - 1) {
                combinedString += " ";
            }
        }
        return combinedString;
    }

    private static String extractDatetime(String input) {
        String[] parts = input.split("/by", 2);
        if (parts.length > 1) {
            return parts[1].trim();
        }
        return "";
    }

    private static String[] extractFromTo(String string) {
        Pattern pattern = Pattern.compile("/from\\s+(.*?)\\s+/to\\s+(.*)");
        Matcher matcher = pattern.matcher(string);
        String[] fromTo = new String[2];

        if (matcher.find()) {
            fromTo[0] = matcher.group(1).trim();
            fromTo[1] = matcher.group(2).trim();
        }
        return fromTo;
    }

    public static void modifyTask(String[] parts, ArrayList<Task> database) {
        Action action = getAction(parts[0]);
        int index = Integer.parseInt(parts[1]); // Adjust for 0-based index
        switch(action) {
            case MARK:
                database.get(index).setDone();
                break;
            case UNMARK:
                database.get(index).setUnDone();
                break;
            case DELETE:
                database.remove(index);
                break;
        }
        Ui.printTaskStatus(database.get(index), action);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> database = new ArrayList<>();
        Ui.printIntro();
        TaskFileHandler.loadTasksFromDisk(database);

        while (true) {
            System.out.println("Enter something:");
            String userInput = scanner.nextLine();
            String[] parts = userInput.split(" ");
            if (userInput.equals("bye")) {
                Ui.printBye();
                break;
            } else if (userInput.equals("list")) {
                Ui.printDatabase(database);  // Adjusted for ArrayList
            } else if (getAction(parts[0]) != Action.UNKNOWN) {
                try {
                    modifyTask(parts, database);
                } catch (IndexOutOfBoundsException e) {
                    Ui.printIndexOutOfBound();
                }
            } else {
                try {
                    addTaskAndPrint(parts, database);
                } catch (IllegalArgumentException e) {
                    Ui.printIllegalArgumentException(e);
                } catch (DateTimeParseException e) {
                    Ui.printDateTimeParseException();
                }
            }
        }
    }
}
