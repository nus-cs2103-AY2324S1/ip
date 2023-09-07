package aj;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Parser class to parse user input and return respective Tasks.
 */
public class Parser {


    /**
     * Takes in user input string, parse it, and return command string and task string
     *
     * @param userInput User input.
     * @return String array, where first element is command, and second element is task, if no match, return empty
     * array.
     */
    public String[] parseCommand(String userInput) {
        Pattern pattern = Pattern.compile("(\\w+) (.*)");
        Matcher matcher = pattern.matcher(userInput);
        String[] strArray;
        if (matcher.matches()) {
            String command = matcher.group(1);
            String taskString = matcher.group(2);
            taskString = " " + taskString;
            strArray = new String[]{command, taskString};
            return strArray;
        }
        return new String[]{};
    }

    /**
     * Creates and return a Todo Task.
     *
     * @param remaining The remaining sub-command from user input.
     * @param isMark    Mark task as complete or not.
     * @return Todo Task.
     */
    public Task getTodoTask(String remaining, boolean isMark) {
        return new Todo(remaining.substring(1),
                isMark);
    }

    /**
     * Creates and return a Deadline Task.
     *
     * @param remaining The remaining sub-command from user input.
     * @param isMark    Mark task as complete or not.
     * @return Deadline Task.
     */
    public Task getDeadlineTask(String remaining, boolean isMark) throws
            WrongDescriptionException { // takes in command, parse it and return task object
        // deadline return book /by 2019-10-15
        String pattern = " (.*) /by (.*)";

        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(remaining);
        try {
            if (matcher.matches()) {
                String taskName = matcher.group(1);
                String by = matcher.group(2);
                LocalDate newDate = LocalDate.parse(by);
                return new Deadline(taskName,
                        isMark,
                        newDate);
            }
        } catch (Exception e) {
            throw new WrongDescriptionException("ERROR!! Please ensure that your date format is correct");
        }
        throw new WrongDescriptionException("ERROR!! Please type 'deadline' to view correct format!!");
    }

    /**
     * Creates and return a Event Task.
     *
     * @param remaining The remaining sub-command from user input.
     * @param isMark    Mark task as complete or not.
     * @return Event Task.
     */
    public Task getEventTask(String remaining, boolean isMark) throws
            WrongDescriptionException { // takes in command, parse it and return task object
        String pattern = " (.*) /from (.*?) /to (.*)";

        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(remaining);

        if (matcher.matches()) {
            String taskName = matcher.group(1);
            String startTime = matcher.group(2);
            String endTime = matcher.group(3);
            return new Event(taskName,
                    isMark,
                    startTime,
                    endTime);
        }
        throw new WrongDescriptionException("ERROR!! Please type 'event' to view correct format!!");
    }
}
