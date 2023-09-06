package duke.assets.commands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import duke.assets.tasks.TaskAbstract;
import duke.data.TaskList;
import duke.assets.tasks.Event;

public class CreateEventCommand extends CommandAbstract {
    private final boolean isDone;
    public CreateEventCommand(String input, boolean isDone) {
        super(input);
        this.isDone = isDone;
    }

    @Override
    protected boolean isValid(TaskList tasklist) {
        return this.isValid();
    }

    private boolean isValid() {
        String dateFormatRegex = SLASHDATEFORMAT + "|" + DASHDATEFORMAT;
        String commandRegexString = String.format("^event\\s.+\\s/from\\s(%s)\\s(" +
                "\\d{4}\\s/to|/to)\\s(%s)(\\s\\d{4}$|$)/", dateFormatRegex, dateFormatRegex);
        Pattern commandRegex = Pattern.compile(commandRegexString, Pattern.CASE_INSENSITIVE);
        Matcher inputMatcher = commandRegex.matcher(this.input);
        if (!inputMatcher.find()) {
            findException();
            return false;
        }
        return true;
    }

    private void findException() {
        String[] delimitedBySlash = this.input.split("/");
        try {
            String information = delimitedBySlash[0].split(" ")[1];
        } catch (IndexOutOfBoundsException indexExcept) {
            System.out.println("ChadGPT: Please include information about the task you would like to add.");
            return;
        }

        try {
            String startDate = delimitedBySlash[1].substring(5, delimitedBySlash[1].length() - 1);
            String endDate = delimitedBySlash[2].substring(3);
        } catch (StringIndexOutOfBoundsException stringExcept) {
            System.out.println("ChadGPT: Please ensure that you have included the start and end dates.");
            return;
        } catch (IndexOutOfBoundsException indexExcept) {
            System.out.println("ChadGPT: Please verify you have included the start date after /from and " +
                    "end date after /to commands");
            return;
        }

        try {
            String dates = this.input.split(" /from ")[1];
            String[] startDateArr = dates.split(" /to ")[0].split(" ");
            String startDate = startDateArr[0];
            String startDateYear = startDate.substring(0, 4);
            String startDateMonth = startDate.substring(5, 7);
            String startDateDay = startDate.substring(8, 10);
            LocalDate startDateObj = LocalDate.parse(String.format("%s-%s-%s", startDateYear,
                    startDateMonth, startDateDay));
            if (startDateArr.length > 1) {
                Integer.parseInt(startDateArr[1]);
                LocalTime startTimeObj = LocalTime.parse(startDateArr[1].substring(0, 2) + ":" +
                        startDateArr[1].substring(2));
            }
            String[] endDateArr = dates.split(" /to ")[1].split(" ");
            String endDate = endDateArr[0];
            String endDateYear = endDate.substring(0, 4);
            String endDateMonth = endDate.substring(5, 7);
            String endDateDay = endDate.substring(8, 10);
            LocalDate endDateObj = LocalDate.parse(String.format("%s-%s-%s", endDateYear,
                    endDateMonth, endDateDay));
            if (endDateArr.length > 1) {
                Integer.parseInt(endDateArr[1]);
                LocalTime endDateTime = LocalTime.parse(endDateArr[1].substring(0, 2) + ":" +
                        endDateArr[1].substring(2));
            }
        } catch (NumberFormatException numberExcept) {
            System.out.println("ChadGPT: Please ensure the time of your deadline is in numerical format.");
        } catch (IndexOutOfBoundsException | IllegalArgumentException indexExcept) {
            System.out.println("ChadGPT: Ensure that deadline date follows the following format: yyyy-mm-dd or yyyy/mm/dd.");
        }
    }

    private String findInformation() {
        String stringWithoutCommand =  this.input.split("event")[1];
        return stringWithoutCommand.split(" /to ")[0];
    }

    private String findDate(String startOrEnd) {
        Pattern dateRegex = Pattern.compile(String.format("\\s(%s|%s)\\s", SLASHDATEFORMAT, DASHDATEFORMAT));
        Matcher inputDateMatcher = dateRegex.matcher(this.input);
        inputDateMatcher.find();
        if (startOrEnd.equals("end")) {
            inputDateMatcher.find();
        }
        return inputDateMatcher.group();
    }

    private String findTime(String startOrEnd) {
        Pattern startTimeRegex = Pattern.compile("\\s\\d{4}\\s");
        Pattern endTimeRegex = Pattern.compile("\\s\\d{4}$");
        Matcher inputTimeMatcher = startOrEnd.equals("start") ? startTimeRegex.matcher(this.input) :
                endTimeRegex.matcher(this.input);
        inputTimeMatcher.find();
        return inputTimeMatcher.group();
    }

    @Override
    protected void completeOperation(TaskList tasklist) {
        String information = this.input.split(" /from ")[0].split("^(?i)(event)\\s")[0];
        String allDateAndTime = this.input.split(" /from ")[1];
        String startDateAndTime = allDateAndTime.split(" /to ")[0];
        String endDateAndTime = allDateAndTime.split(" /to ")[1];
        TaskAbstract newTask = new Event(information, startDateAndTime, endDateAndTime);
        if (this.isDone) {
            newTask.completeNewTask();
        }
        tasklist.addTask(newTask);
    }
}
