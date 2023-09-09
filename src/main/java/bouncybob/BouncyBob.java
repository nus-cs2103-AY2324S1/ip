package bouncybob;

import jdk.jfr.Event;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import bouncybob.task.Task;
import bouncybob.task.Deadlines;
import bouncybob.task.Events;
import bouncybob.task.ToDos;
import bouncybob.util.TaskList;
import bouncybob.util.TaskFileHandler;
import bouncybob.util.Parser;
import bouncybob.util.Ui;

public class BouncyBob {

    public enum TaskType {
        TODO, DEADLINE, EVENT, UNKNOWN
    }
    public enum Action {
        MARK, UNMARK, DELETE, UNKNOWN
    }

    private static void addTaskAndPrint(String[] parts, TaskList taskList) {
        TaskType taskType = Parser.getTaskType(parts[0]);
        String taskName = "";
        Task newTask = null;
        switch (taskType) {
            case TODO:
                taskName = Parser.removeAction(parts);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'todo' cannot be empty.");
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
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'deadline' cannot be empty.");
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
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'event' cannot be empty.");
                }
                newTask = new Events(taskName, fromTo[0], fromTo[1]);
                break;
            case UNKNOWN:
                throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
        taskList.addTask(newTask);
        Ui.printTaskCount(taskList.size() - 1, newTask);  // Adjusted to size of ArrayList
    }

    public static void modifyTask(String[] parts, TaskList taskList) {
        Action action = Parser.getAction(parts[0]);
        int index = Integer.parseInt(parts[1]); // Adjust for 0-based index
        switch(action) {
            case MARK:
                taskList.getTask(index).setDone();
                Ui.printTaskStatus(taskList.getTask(index), action);
                break;
            case UNMARK:
                taskList.getTask(index).setUnDone();
                Ui.printTaskStatus(taskList.getTask(index), action);
                break;
            case DELETE:
                Ui.printTaskStatus(taskList.getTask(index), action);
                taskList.removeTask(index);
                break;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Ui.printIntro();
        TaskFileHandler.loadTasksFromDisk(taskList);

        while (true) {
            System.out.println("Enter something:");
            String userInput = scanner.nextLine();
            String[] parts = userInput.split(" ");
            if (userInput.equals("bye")) {
                Ui.printBye();
                break;
            } else if (userInput.equals("list")) {
                Ui.printDatabase(taskList);  // Adjusted for ArrayList
            } else if (parts[0].equals("find")) {
                // find
                String subString = Parser.removeAction(parts);
                TaskList subTaskList = taskList.getSubTaskList(subString);
                Ui.printDatabase(subTaskList);
            } else if (Parser.getAction(parts[0]) != Action.UNKNOWN) {
                try {
                    modifyTask(parts, taskList);
                    TaskFileHandler.saveTasksToDisk(taskList);
                } catch (IndexOutOfBoundsException e) {
                    Ui.printIndexOutOfBound();
                }
            } else {
                try {
                    addTaskAndPrint(parts, taskList);
                    TaskFileHandler.saveTasksToDisk(taskList);
                } catch (IllegalArgumentException e) {
                    Ui.printIllegalArgumentException(e);
                } catch (DateTimeParseException e) {
                    Ui.printDateTimeParseException();
                }
            }
        }
    }
}
