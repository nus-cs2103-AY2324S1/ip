package duke.assets.commands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.assets.storage.TaskList;
import duke.assets.tasks.Deadline;
import duke.assets.tasks.TaskAbstract;

/**
 * Represents a command to create a new deadline task
 */
public class CreateDeadlineCommand extends CommandAbstract {

    /**
     * A regular expression for validating the input command string
     */
    private static final String INPUT_DEADLINE_REGEX_STRING = String.format("^deadline .+ /by %s($| %s$)",
            VALID_DATE_REGEX_STRING, VALID_TIME_REGEX_STRING);

    /**
     * A flag indicating whether the new task is already completed
     */
    private final boolean isDone;

    /**
     * Constructs a new CreateDeadlineCommand object with the given input command string and completion flag
     *
     * @param input the input command string
     * @param isDone a flag indicating whether the new task is already completed
     */
    public CreateDeadlineCommand(String input, boolean isDone) {
        super(input);
        this.isDone = isDone;
    }

    /**
     * Determines whether the input command is valid for the specified task list
     *
     * @param tasklist the task list to validate against
     * @return true if the input command is valid, false otherwise
     */
    @Override
    protected boolean isValid(TaskList tasklist) {
        return this.isValid();
    }

    /**
     * Determines whether the input command is valid
     *
     * @return true if the input command is valid, false otherwise
     */
    private boolean isValid() {
        Pattern inputRegex = Pattern.compile(INPUT_DEADLINE_REGEX_STRING, Pattern.CASE_INSENSITIVE);
        Matcher inputMatcher = inputRegex.matcher(this.input);
        if (!inputMatcher.find()) {
            findException();
            return false;
        }
        return true;
    }

    /**
     * Handles exceptions that occur when validating the input command
     */
    private void findException() {
        String[] delimitedBySlash = this.input.split("/");

        try { // Checks if user input included description about the task
            String information = delimitedBySlash[0].split(" ")[1];
        } catch (IndexOutOfBoundsException indexExcept) {
            System.out.println("ChadGPT: Please include description about the task you would like to add.");
            return;
        }

        try { // Checks if user input included date details according to format specified
            String endDate = delimitedBySlash[1].substring(3);
        } catch (StringIndexOutOfBoundsException stringExcept) {
            System.out.println("ChadGPT: Please ensure your deadline date is included.");
            return;
        } catch (IndexOutOfBoundsException indexExcept) {
            System.out.println("ChadGPT: Please include the deadline date of your task after \"/by\" command.");
            return;
        }

        try { // Checks if it is possible to parse the user specified date into date time objects.
            String[] dates = this.input.split("/by ")[1].split(" ");
            String endDate = dates[0];
            String year = endDate.substring(0, 4);
            String month = endDate.substring(5, 7);
            String day = endDate.substring(8, 10);
            LocalDate endDateObj = LocalDate.parse(String.format("%s-%s-%s", year, month, day));
            if (dates.length > 1) {
                Integer.parseInt(dates[1]);
                LocalTime timeObj = LocalTime.parse(dates[1].substring(0, 2) + ":" + dates[1]
                        .substring(2));
            }
        } catch (NumberFormatException numberExcept) {
            System.out.println("ChadGPT: Please ensure the time of your deadline is in numerical format.");
        } catch (IndexOutOfBoundsException | IllegalArgumentException formatExcept) {
            System.out.println("ChadGPT: Ensure that deadline date follows the following format: yyyy-mm-dd.");
        }
    }

    /**
     * Completes the operation specified by the input command on the specified task list
     *
     * @param tasklist the task list to operate on
     */
    @Override
    protected void completeOperation(TaskList tasklist) {
        String information = this.input.split(" /by ")[0].split("^(?i)(deadline)\\s")[1];
        String dateAndTime = this.input.split(" /by ")[1];
        TaskAbstract newTask = new Deadline(information, dateAndTime);
        if (this.isDone) {
            newTask.completeNewTask();
        }
        tasklist.addTask(newTask);
    }

    /**
     * Prints the appropriate dialogue from the chatbot to the terminal
     */
    @Override
    public void printChatbotLine() {
        System.out.print("ChadGPT: Gotcha, I have added the task to the list.\n" + HORIZONTAL);
    }
}
