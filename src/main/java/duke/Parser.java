package duke;

import duke.task.*;
public class Parser {
    private static TaskList tasks;
    public static void parse(String input, TaskList tasks) throws DukeException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        switch (command) {
        case "list":
            tasks.listTask();
            break;
        case "delete":
            int task = parseDeleteCommand(parts);
            tasks.deleteTask(task);
            break;
        case "todo":
            ToDoTask toDoTask = parseTodoCommand(parts, false);
            tasks.addTask(toDoTask);
            break;
        case "deadline":
            DeadlineTask deadlineTask = parseDeadline(parts[1], false);
            tasks.addTask(deadlineTask);
            break;
        case "event":
            EventTask eventTask = parseEvent(parts[1], false);
            tasks.addTask(eventTask);
            break;
        case "mark":
            int markTask = parseMarkCommand(parts) - 1;
            if (markTask < 0 || markTask >= tasks.getTotalTasks()) {
                System.out.println("There is no task for this number!");
                break;
            }
            tasks.getTask(markTask).markTask();
            break;
        case "unmark":
            int unmarkTask = parseUnmarkCommand(parts) - 1;
            if (unmarkTask < 0 || unmarkTask >= tasks.getTotalTasks()) {
                System.out.println("There is no task for this number!");
                break;
            }
            tasks.getTask(unmarkTask).unmarkTask();
            break;
        default:
            System.out.println("You inputted an invalid command! Please try deadline, todo or event :)");
        }
    }

    //For the data loading
    public static Task parse(String taskType, String taskDetails, boolean isDone) throws DukeException {
        if (taskType.equalsIgnoreCase("[T")) {
            taskDetails = taskDetails.replace(" ", "");
            return new ToDoTask(taskDetails, isDone);
        } else if (taskType.equals("[D")) {
            return Parser.parseDeadline(taskDetails, isDone);
        } else if (taskType.equals("[E")) {
            return Parser.parseEvent(taskDetails, isDone);
        } else {
            return null;
        }
    }



    private static int parseMarkCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("Please provide a task number for 'done' command.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new DukeException("Please pick a number instead of using letters!");
        }
    }

    private static int parseUnmarkCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("Please provide a task number for 'done' command.");
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new DukeException("Please pick a number instead of using letters!");
        }
    }

    private static int parseDeleteCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("Please provide a task number for 'delete' command.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return taskNumber;
        } catch (NumberFormatException e) {
        throw new DukeException("Please pick a number instead of using letters!");
        }
    }

    private static ToDoTask parseTodoCommand(String[] parts, boolean isDone) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new ToDoTask(parts[1], isDone);
    }

    private static DeadlineTask parseDeadline(String taskDetails, boolean isDone) throws DukeException {
        if (!taskDetails.contains("by:")) {
            throw new DukeException("Remember to include 'by:' after the deadline command!");
        }
        String[] details = taskDetails.split("by:", 2);
        String description = details[0];
        description = description.replace(" ", "");
        String by = details[1];
        by = by.replace(" ", "");
        return new DeadlineTask(description, by, isDone);
    }

    public static EventTask parseEvent(String taskDetails, boolean isDone) throws DukeException {
        if (!taskDetails.contains("from:") || !taskDetails.contains("to:")) {
            throw new DukeException("Remember to include 'from:' and 'to:' after the event command!");
        }
        String[] details = taskDetails.split("from:", 2);
        String[] innerDetails = details[1].split(" to:", 2);
        String description = details[0];
        description = description.replace(" ", "");
        String from = innerDetails[0];
        from = from.replace(" ", "");
        String to = innerDetails[1];
        to = to.replace(" ", "");

        return new EventTask(description, from, to, isDone);
    }
}
