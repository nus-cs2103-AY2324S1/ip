import jdk.jfr.Event;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class BouncyBob {

    public enum TaskType {
        TODO, DEADLINE, EVENT, UNKNOWN
    }
    public enum Action {
        MARK, UNMARK, DELETE, UNKNOWN
    }

    private static void addTaskAndPrint(String[] parts, ArrayList<Task> database) {
        TaskType taskType = Parser.getTaskType(parts[0]);
        String taskName = "";
        Task newTask = null;
        switch (taskType) {
            case TODO:
                taskName = Parser.removeAction(parts);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("Task name for 'todo' cannot be empty.");
                }
                newTask = new ToDos(taskName);
                break;
            case DEADLINE:
                taskName = Parser.removeAction(parts);
                String datetime = Parser.extractDatetime(taskName);
                if (datetime.trim().isEmpty()) {
                    throw new IllegalArgumentException("/by cannot be empty!");
                }
                taskName = Parser.getTaskDeadline(taskName);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("Task name for 'deadline' cannot be empty.");
                }
                newTask = new Deadlines(taskName, datetime);
                break;
            case EVENT:
                taskName = Parser.removeAction(parts);
                String[] fromTo = Parser.extractFromTo(taskName);
                if (fromTo[0] == null || fromTo[1] == null) {
                    throw new IllegalArgumentException("/from and /to cannot be empty!");
                }
                taskName = Parser.getTaskEvent(taskName);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("Task name for 'event' cannot be empty.");
                }
                newTask = new Events(taskName, fromTo[0], fromTo[1]);
                break;
            case UNKNOWN:
                throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
        database.add(newTask);
        Ui.printTaskCount(database.size() - 1, newTask);  // Adjusted to size of ArrayList
    }

    public static void modifyTask(String[] parts, ArrayList<Task> database) {
        Action action = Parser.getAction(parts[0]);
        int index = Integer.parseInt(parts[1]); // Adjust for 0-based index
        Ui.printTaskStatus(database.get(index), action);
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
            } else if (Parser.getAction(parts[0]) != Action.UNKNOWN) {
                try {
                    modifyTask(parts, database);
                    TaskFileHandler.saveTasksToDisk(database);
                } catch (IndexOutOfBoundsException e) {
                    Ui.printIndexOutOfBound();
                }
            } else {
                try {
                    addTaskAndPrint(parts, database);
                    TaskFileHandler.saveTasksToDisk(database);
                } catch (IllegalArgumentException e) {
                    Ui.printIllegalArgumentException(e);
                } catch (DateTimeParseException e) {
                    Ui.printDateTimeParseException();
                }
            }
        }
    }
}
