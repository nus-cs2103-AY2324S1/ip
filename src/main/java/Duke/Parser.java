package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Duke.Exceptions.DuplicateInput;
import Duke.GUI.Ui;
import Duke.Tasks.TaskList;
import Duke.Exceptions.IncompleteInput;
import Duke.Exceptions.InvalidInput;

/**
 * The Parser class is responsible for parsing user input, processing commands, and formatting date and time.
 */
public class Parser {

    public Parser() {

    }

    /**
     * Process user input and perform corresponding actions on the task list and user interface.
     *
     * @param input The user's input.
     * @param tasks The TaskList object to manage tasks.
     * @param iu The Ui object to interact with the user.
     * @return True if the application should continue running; false if it should exit.
     */
    public static String input(String input, TaskList tasks, Ui iu) {
        assert input != null;
        String[] listOfWords = input.split(" ");
        String firstWord = listOfWords[0];
        try {
            if (input.equals("bye")) {
                Storage.saveTasks(tasks);
                return Ui.getGoodbyeMessage();
            } else if (input.equals("list")) {
                return iu.getTaskListMessage(tasks);
            } else if (firstWord.equals("find")) {
                return iu.getMatchingTasksMessage(tasks, input.split(" ", 2)[1]);
            } else if (firstWord.equals("mark")) {
                int index = Integer.parseInt(listOfWords[1]);
                if (index < 1 || index > tasks.getNumOfItems()) {
                    throw new InvalidInput("False Index");
                }
                tasks.markItemAsDone(index);
                return iu.getMarkedTaskMessage(tasks, index);
            } else if (firstWord.equals("unmark")) {
                int index = Integer.parseInt(listOfWords[1]);
                if (index < 1 || index > tasks.getNumOfItems()) {
                    throw new InvalidInput("False Index");
                }
                tasks.markItemAsUndone(index);
                return iu.getUnmarkedTaskMessage(tasks, index);
            } else if (firstWord.equals("delete")) {
                int index = Integer.parseInt(listOfWords[1]);
                if (index < 1 || index > tasks.getNumOfItems()) {
                    throw new InvalidInput("False Index");
                }
                String taskToDelete = iu.getDeleteTaskMessage(tasks, index);
                tasks.deleteItem(index);
                return taskToDelete;
            } else {
                boolean exceptionOccured = false;
                try {
                    tasks.addTask(input);
                } catch (IncompleteInput e) {
                    exceptionOccured = true;
                    return iu.getTaskWithoutDescriptionMessage();
                } catch (InvalidInput e) {
                    exceptionOccured = true;
                    return iu.getUnreadableInputMessage();
                } catch (DuplicateInput e) {
                    exceptionOccured = true;
                    return iu.getDuplicateInputMessage();
                } finally {
                    if (!exceptionOccured) {
                        return iu.getAddedToListMessage(tasks);
                    }
                }
            }
        } catch (InvalidInput e) {
            return iu.getWrongIndexMessage();
        }
        return "Never happens";
    }

    /**
     * Formats a date and time string into a specific output format.
     *
     * @param time The input date and time string in "yyyy-MM-dd HHmm" format.
     * @return The formatted date and time string in "MMM d yyyy HHmm" format, or the original input if parsing fails.
     * @throws DateTimeParseException If the input string is not in the expected format.
     */
    public String formatTime(String time) throws DateTimeParseException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(time, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        String formattedOutput = dateTime.format(outputFormatter);
        return formattedOutput;
    }

    /**
     * Converts a date and time string using the formatTime method, handling any exceptions that may occur.
     *
     * @param time The input date and time string.
     * @return The formatted date and time string or the original input if parsing fails.
     */
    public static String convertTimeToString(String time) {
        try {
            Parser parser = new Parser();
            String formattedOutput = parser.formatTime(time);
            return formattedOutput;
        } catch (DateTimeParseException e) {
            return time;
        }
    }

}


