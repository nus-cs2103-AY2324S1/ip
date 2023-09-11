package duke;

import static duke.Event.DATE_TIME_FORMATTER;
import static duke.Storage.saveTasksToFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.application.Platform;


enum CommandType {
    EXIT, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, SEARCH, UNKNOWN
}

/**
 * Handles the parsing of user input and the corresponding actions in the Duke application.
 */
public class Parser {
    /**
     * Parses the user input and performs the corresponding actions.
     *
     * @param taskList The list of tasks.
     * @param i        The current index of tasks in the list.
     * @throws DukeException If an error occurs during parsing.
     */
    public static String parse(ArrayList<Task> taskList,
                               int i, Storage storage, String userInput) throws DukeException {
        String[] binaryArr = userInput.split(" ", 2);
        if (binaryArr.length == 1) {
            binaryArr = new String[]{binaryArr[0], null};
        }

        String commandWord = binaryArr[0];
        String instructionDetails = binaryArr[1];
        CommandType commandType = getCommandType(commandWord);
        switch (commandType) {
        case EXIT:
            handleExit(taskList, storage);
            return "Bye. Hope to see you again soon!";
        case LIST:
            return handleList(taskList, i);
        case MARK:
            return handleMark(taskList, instructionDetails);
        case UNMARK:
            return handleUnmark(taskList, instructionDetails);
        case DELETE:
            return handleDelete(taskList, instructionDetails, i);
        case TODO:
            return handleTodo(taskList, instructionDetails, i);
        case DEADLINE:
            return handleDeadline(taskList, instructionDetails, i);
        case EVENT:
            return handleEvent(taskList, instructionDetails, i);
        case SEARCH:
            return handleSearch(taskList, instructionDetails);
        case UNKNOWN:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        default:
            throw new DukeException("☹ OOPS!!! An error occurred while parsing your command.");
        }
    }
    private static CommandType getCommandType(String userInput) {
        userInput = userInput.trim().toLowerCase();

        if (userInput.equals("bye")) {
            return CommandType.EXIT;
        } else if (userInput.equals("list")) {
            return CommandType.LIST;
        } else if (userInput.startsWith("mark")) {
            return CommandType.MARK;
        } else if (userInput.startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (userInput.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (userInput.startsWith("todo")) {
            return CommandType.TODO;
        } else if (userInput.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (userInput.startsWith("event")) {
            return CommandType.EVENT;
        } else if (userInput.startsWith("find")) {
            return CommandType.SEARCH;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    private static void handleExit(ArrayList<Task> taskList, Storage storage) {
        saveTasksToFile(taskList, String.valueOf(storage.path));
        Platform.exit();
    }

    private static String handleList(ArrayList<Task> taskList, int i) {
        String finalText = "";
        if (taskList.isEmpty()) {
            finalText = "There are currently no tasks in your list";
        } else {
            finalText += "Here are the tasks in your list:\n";
            for (int j = 0; j < i; j++) {
                finalText += j + 1 + "." + taskList.get(j).toString() + "\n";
            }
        }
        return finalText;
    }

    private static String handleMark(ArrayList<Task> taskList, String instructionDetails) {
        String finalText = "";
        Task curr = taskList.get(Integer.parseInt(instructionDetails) - 1);
        curr.mark();
        finalText = "Nice! I've marked this task as done: \n" + "[X] " + curr.getDescription();
        return finalText;
    }

    private static String handleUnmark(ArrayList<Task> taskList, String instructionDetails) {
        String finalText = "";
        Task curr = taskList.get(Integer.parseInt(instructionDetails) - 1);
        curr.unmark();
        finalText = "OK, I've marked this task as not done yet: \n" + "[ ] " + curr.getDescription();
        return finalText;
    }

    private static String handleDelete(ArrayList<Task> taskList, String instructionDetails, int i) {
        String finalText = "";
        Task curr = taskList.get(Integer.parseInt(instructionDetails) - 1);
        taskList.remove(Integer.parseInt(instructionDetails) - 1);
        i--;
        finalText += "Noted. I've removed this task:\n";
        finalText += curr.toString();
        finalText += "\nNow you have " + i + " tasks in the list.";
        return finalText;
    }
    private static String handleTodo(ArrayList<Task> taskList, String instructionDetails, int i) throws
            DukeException {
        String finalText = "";
        if (instructionDetails.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        taskList.add(new Todo(instructionDetails));
        finalText += "Got it. I've added this task: \n";
        finalText += taskList.get(i).toString();
        i++;
        finalText += "\nNow you have " + i + " tasks in the list.";
        return finalText;
    }
    private static String handleDeadline(ArrayList<Task> taskList, String instructionDetails, int i) throws
            DukeException {
        String finalText = "";
        if (instructionDetails.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] deadlineArr = instructionDetails.split("/by ");
        LocalDate by;
        if (deadlineArr[1].equals("today")) {
            by = LocalDate.now();
        } else {
            by = LocalDate.parse(deadlineArr[1]);
        }
        taskList.add(new Deadline(deadlineArr[0], by));
        finalText += "Got it. I've added this task: \n";
        finalText += taskList.get(i).toString();
        i++;
        finalText += "Now you have " + i + " tasks in the list.";
        return finalText;
    }

    private static String handleEvent(ArrayList<Task> taskList, String instructionDetails, int i) throws
            DukeException {
        String finalText = "";
        if (instructionDetails.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] eventAndTime = instructionDetails.split("/from ");
        String[] eventDuration = eventAndTime[1].split("/to ");
        LocalDateTime from = LocalDateTime.parse(eventDuration[0], DATE_TIME_FORMATTER);
        LocalDateTime to = LocalDateTime.parse(eventDuration[1], DATE_TIME_FORMATTER);

        taskList.add(new Event(eventAndTime[0], from, to));
        finalText += "Got it. I've added this task: \n";
        finalText += taskList.get(i).toString();
        i++;
        finalText += "Now you have " + i + " tasks in the list.";
        return finalText;
    }

    private static String handleSearch(ArrayList<Task> taskList, String instructionDetails) {
        String finalText = "";
        String searchTerm = instructionDetails;
        ArrayList<Task> searchList = new TaskList();
        taskList.forEach(t -> {
            if (t.getDescription().contains(searchTerm)) {
                searchList.add(t);
            }
        });
        int searchListSize = searchList.size();
        finalText += "Here are the matching tasks in your list:\n";
        for (int j = 0; j < searchListSize; j++) {
            finalText += j + 1 + "." + searchList.get(j).toString() + "\n";
        }
        return finalText;
    }
}
