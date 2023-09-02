package aj;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser class to parse user input and return respective Tasks.
 */
public class Parser {

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
    public Task getDeadlineTask(String remaining, boolean isMark) { // takes in command, parse it and return task object
        // deadline return book /by 2019-10-15
        String pattern = " (.*) /by (.*)";

        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(remaining);

        if (matcher.matches()) {
            String taskName = matcher.group(1);
            String by = matcher.group(2);
            LocalDate newDate = LocalDate.parse(by);
//            System.out.println(newDate);
            return new Deadline(taskName,
                    isMark,
                    newDate);
        }
        return null;
    }

    /**
     * Creates and return a Event Task.
     *
     * @param remaining The remaining sub-command from user input.
     * @param isMark    Mark task as complete or not.
     * @return Event Task.
     */
    public Task getEventTask(String remaining, boolean isMark) { // takes in command, parse it and return task object
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
        return null;
    }
}
