package duke;

import static duke.Event.DATE_TIME_FORMATTER;
import static duke.Storage.saveTasksToFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.application.Platform;




/**
 * Handles the parsing of user input and the corresponding actions in the Duke application.
 */
public class Parser {
    static final String EXIT_PHRASE = "bye";
    static final String LIST_PHRASE = "list";
    static final String TODO_PHRASE = "todo";
    static final String DEADLINE_PHRASE = "deadline";
    static final String EVENT_PHRASE = "event";
    static final String DELETE_PHRASE = "delete";
    static final String SEARCH_PHRASE = "find";


    /**
     * Parses the user input and performs the corresponding actions.
     *
     * @param taskList The list of tasks.
     * @param i The current index of tasks in the list.
     * @param ui The user interface object.
     * @throws DukeException If an error occurs during parsing.
     */
    public static String parse(ArrayList<Task> taskList,
                             int i, Ui ui, Storage storage, String userInput) throws DukeException {
        String finalText = "";
        String[] binaryArr = userInput.split(" ", 2);
        if (binaryArr.length == 1) {
            binaryArr = new String[] {binaryArr[0], null};
        }

        String commandWord = binaryArr[0];
        if (commandWord.equals(EXIT_PHRASE)) {
            saveTasksToFile(taskList, String.valueOf(storage.path));
            Platform.exit();
            return "Bye. Hope to see you again soon!";
        } else if (commandWord.equals(LIST_PHRASE)) {
            if (taskList.isEmpty()) {
                finalText = "There are currently no tasks in your list";
            } else {
                finalText += "Here are the tasks in your list:\n";
                for (int j = 0; j < i; j++) {
                    finalText += j + 1 + "." + taskList.get(j).toString() + "\n";
                }
            }
            return finalText;
        } else if (commandWord.equals("mark")) {
            Task curr = taskList.get(Integer.parseInt(binaryArr[1]) - 1);
            curr.mark();
            finalText = "Nice! I've marked this task as done: \n" + "[X] " + curr.getDescription();
            return finalText;
        } else if (commandWord.equals("unmark")) {
            Task curr = taskList.get(Integer.parseInt(binaryArr[1]) - 1);
            curr.unmark();
            finalText = "OK, I've marked this task as not done yet: \n" + "[ ] " + curr.getDescription();
            return finalText;
        } else if (commandWord.equals(DELETE_PHRASE)) {
            Task curr = taskList.get(Integer.parseInt(binaryArr[1]) - 1);
            taskList.remove(Integer.parseInt(binaryArr[1]) - 1);
            i--;
            finalText += "Noted. I've removed this task:\n";
            finalText += curr.toString();
            finalText += "\nNow you have " + i + " tasks in the list.";
            return finalText;
        } else if (commandWord.equals(TODO_PHRASE)) {
            if (binaryArr[1].equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            taskList.add(new Todo(binaryArr[1]));
            finalText += "Got it. I've added this task: \n";
            finalText += taskList.get(i).toString();
            i++;
            finalText += "\nNow you have " + i + " tasks in the list.";
            return finalText;
        } else if (commandWord.equals(DEADLINE_PHRASE)) {
            if (binaryArr[1].equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] deadlineArr = binaryArr[1].split("/by ");
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
        } else if (userInput.equals(EVENT_PHRASE)) {
            if (binaryArr[1].equals("")) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            String[] eventAndTime = binaryArr[1].split("/from ");
            String[] eventDuration = eventAndTime[1].split("/to ");
            LocalDateTime from = LocalDateTime.parse(eventDuration[0], DATE_TIME_FORMATTER);
            LocalDateTime to = LocalDateTime.parse(eventDuration[1], DATE_TIME_FORMATTER);

            taskList.add(new Event(eventAndTime[0], from, to));
            finalText += "Got it. I've added this task: \n";
            finalText += taskList.get(i).toString();
            i++;
            finalText += "Now you have " + i + " tasks in the list.";
            return finalText;
        } else if (commandWord.equals(SEARCH_PHRASE)) {
            String searchTerm = binaryArr[1];
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
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
