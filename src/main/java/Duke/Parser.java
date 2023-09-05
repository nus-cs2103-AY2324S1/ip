package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    public boolean inputs(String input, TaskList tasks, Ui iu) {
        String[] listOfWords = input.split(" ");
        String prefix = listOfWords[0];
        //future has been used as the boolean which determines when to terminate program
        boolean future = true;
        try {
            if (input.equals("bye")) {
                Storage.saveTasks(tasks);
                Ui.printBye();
                future = false;
            } else if (input.equals("list")) {
                iu.printList(tasks);
            } else if (prefix.equals("find")) {
                iu.printMatchingTasks(tasks, input.split(" ",2)[1]);
            } else if (prefix.equals("mark")) {
                int index = Integer.parseInt(listOfWords[1]);

                if (index < 1 || index > tasks.numOfItems()) {
                    throw new InvalidInput("False Index");
                }

                tasks.checkItem(index);
                iu.printMarked(tasks, index);
            } else if (prefix.equals("unmark")) {
                int index = Integer.parseInt(listOfWords[1]);

                if (index < 1 || index > tasks.numOfItems()) {
                    throw new InvalidInput("False Index");
                }

                tasks.notDoneItem(index);
                iu.printUnmarked(tasks, index);
            } else if (prefix.equals("delete")) {
                int index = Integer.parseInt(listOfWords[1]);


                if (index < 1 || index > tasks.numOfItems()) {
                    throw new InvalidInput("False Index");
                }

                iu.printDelete(tasks, index);
                tasks.deleteIndex(index);
            } else {
                boolean exceptionOccured = false;
                try {
                    tasks.input(input);
                } catch (IncompleteInput e) {
                    exceptionOccured = true;
                    iu.printTaskWithoutDescription();
                } catch (InvalidInput e) {
                    exceptionOccured = true;
                    iu.printNonsense();
                } finally {
                    if (!exceptionOccured) {
                        iu.printAddedToList(tasks);
                    }
                }
            }
        } catch (InvalidInput e) {
            iu.printWrongIndex();
        } finally {
            return future;
        }
    }

    /**
     * Formats a date and time string into a specific output format.
     *
     * @param time The input date and time string in "yyyy-MM-dd HHmm" format.
     * @return The formatted date and time string in "MMM d yyyy HHmm" format, or the original input if parsing fails.
     * @throws DateTimeParseException If the input string is not in the expected format.
     */
    public String formatTime(String time) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        String formattedStringOutput = dateTime.format(outputFormatter);
        return formattedStringOutput;
    }

    /**
     * Converts a date and time string using the formatTime method, handling any exceptions that may occur.
     *
     * @param time The input date and time string.
     * @return The formatted date and time string or the original input if parsing fails.
     */
    public static String dateToString(String time) {
        try {
            Parser dud = new Parser();
            String formattedStringOutput = dud.formatTime(time);
            return formattedStringOutput;
        } catch (DateTimeParseException e) {
            return time;
        }
    }

}


