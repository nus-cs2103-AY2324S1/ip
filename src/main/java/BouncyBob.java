import jdk.jfr.Event;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class BouncyBob {
    private static final String TOP_BORDER = "================================================";
    private static final String MIDDLE_BORDER = "|                                              |";
    private static final String BOTTOM_BORDER = "================================================";
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

    private static void printTaskStatus(Task task) {
        String marking = " ";
        if (task.isDone()) {
            marking = "X";
        }
        System.out.println("[" + task.getSymbol() + "]" + "[" + marking + "]" + " " + task.getDescription());
    }

    private static void printBye() {
        System.out.println(TOP_BORDER);
        System.out.println(MIDDLE_BORDER);
        System.out.println("|        BouncyBob: Bye! Bounce back soon!     |");
        System.out.println(MIDDLE_BORDER);
        System.out.println(BOTTOM_BORDER);
    }

    private static String getTaskEvent(String input) {
        String task = input.split("/from")[0].trim();
        return task;
    }

    private static String getTaskDeadline(String input) {
        String task = input.split("/by")[0].trim();
        return task;
    }

    private static void printDatabase(ArrayList<Task> database) {
        System.out.println(TOP_BORDER);
        if (database.isEmpty()) {
            System.out.println("Balls! You currently do not have any tasks!");
        } else {
            System.out.println("Here are your tasks!");
            for (Task curTask : database) {
                printTaskStatus(curTask);
            }
        }
        System.out.println(BOTTOM_BORDER);
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
        System.out.println(TOP_BORDER);
        System.out.println("Added to database: " + newTask.getDescription());
        TaskFileHandler.saveTasksToDisk(database);
        printTaskCount(database.size() - 1);  // Adjusted to size of ArrayList
        System.out.println(BOTTOM_BORDER);
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
        System.out.println(TOP_BORDER);
        switch(action) {
            case MARK:
                System.out.println("You've done it, very bouncy!");
                database.get(index).setDone();
                printTaskStatus(database.get(index));
                break;
            case UNMARK:
                System.out.println("Gotta pump for air! It's unmarked!");
                database.get(index).setUnDone();
                printTaskStatus(database.get(index));
                break;
            case DELETE:
                System.out.println("Task deleted: ");
                printTaskStatus(database.get(index));
                database.remove(index);
                break;
        }
        System.out.println(BOTTOM_BORDER);
    }

    private static void printTaskCount(int pointer) {
        String s = pointer == 0 ? "task" : "tasks";
        String str = String.format("Currently, you have %s %s, start bouncing!", pointer + 1, s);
        System.out.println(str);
    }

    private static void printIntro() {
        System.out.println(TOP_BORDER);
        System.out.println(MIDDLE_BORDER);
        System.out.println("|                   Hey there!                 |");
        System.out.println("|      I'm BouncyBob, your bubbly chatbot!     |");
        System.out.println("| Wow you look very round today, like a ball!  |");
        System.out.println("|       Use yyyy-mm-dd hhmm for dates          |");
        System.out.println(MIDDLE_BORDER);
        System.out.println(BOTTOM_BORDER);
    }
    private static void printIllegalArgumentException(IllegalArgumentException e) {
        System.out.println(TOP_BORDER);
        System.out.println("Oops! " + e.getMessage());
        System.out.println(BOTTOM_BORDER);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> database = new ArrayList<>();
        printIntro();
        TaskFileHandler.loadTasksFromDisk(database);

        while (true) {
            System.out.println("Enter something:");
            String userInput = scanner.nextLine();
            String[] parts = userInput.split(" ");
            if (userInput.equals("bye")) {
                printBye();
                break;
            } else if (userInput.equals("list")) {
                printDatabase(database);  // Adjusted for ArrayList
            } else if (getAction(parts[0]) != Action.UNKNOWN) {
                try {
                    modifyTask(parts, database);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(TOP_BORDER);
                    System.out.println("Make sure your index is within the length of the list!");
                    System.out.println(BOTTOM_BORDER);
                }
            } else {
                try {
                    addTaskAndPrint(parts, database);
                } catch (IllegalArgumentException e) {
                    printIllegalArgumentException(e);
                }
            }
        }
    }
}
