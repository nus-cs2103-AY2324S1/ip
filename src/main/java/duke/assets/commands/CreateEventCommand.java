package duke.assets.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.data.TaskList;
import duke.assets.tasks.Event;

public class CreateEventCommand extends CommandAbstract {
    public CreateEventCommand(String input) {
        super(input);
    }

    @Override
    protected boolean isValid() {
        String[] delimitedBySlash = this.input.split("/");
        try {
            String information = delimitedBySlash[0].split(" ")[1];
        } catch (IndexOutOfBoundsException indexExcept) {
            System.out.println("ChadGPT: Please include information about the task you would like to add.");
            return false;
        }

        try {
            String startDate = delimitedBySlash[1].substring(5, delimitedBySlash[1].length() - 1);
            String endDate = delimitedBySlash[2].substring(3);
        } catch (StringIndexOutOfBoundsException stringExcept) {
            System.out.println("ChadGPT: Please ensure that you have included the start and end dates.");
            return false;
        } catch (IndexOutOfBoundsException indexExcept) {
            System.out.println("ChadGPT: Please verify you have included the start date after /from and " +
                    "end date after /to commands");
            return false;
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
            return false;
        } catch (IndexOutOfBoundsException | IllegalArgumentException indexExcept) {
            System.out.println("ChadGPT: Ensure that deadline date follows the following format: yyyy-mm-dd.");
            return false;
        }
        return true;
    }

    @Override
    protected void completeOperation(TaskList tasklist) {
        String[] delimitedBySlash = this.input.split("/");
        tasklist.addTask(new Event(delimitedBySlash[0].substring(6, delimitedBySlash[0].length() - 1),
                delimitedBySlash[1].substring(5, delimitedBySlash[1].length() - 1),
                delimitedBySlash[2].substring(3)));
    }
}
