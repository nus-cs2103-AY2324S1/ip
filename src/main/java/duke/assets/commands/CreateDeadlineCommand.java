package duke.assets.commands;

import duke.assets.tasks.Deadline;
import duke.data.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateDeadlineCommand extends CommandAbstract {
    public CreateDeadlineCommand(String input) {
        super(input);
    }

    @Override
    protected boolean isValid() {
        String[] delimitedBySlash = this.input.split("/");

        try {   //Checks if user input included description about the task
            String information = delimitedBySlash[0].split(" ")[1];
        } catch (IndexOutOfBoundsException indexExcept) {
            System.out.println("ChadGPT: Please include description about the task you would like to add.");
            return false;
        }

        try {   //Checks if user input included date details according to format specified
            String endDate = delimitedBySlash[1].substring(3);
        } catch (StringIndexOutOfBoundsException stringExcept) {
            System.out.println("ChadGPT: Please ensure your deadline date is included.");
            return false;
        } catch (IndexOutOfBoundsException indexExcept) {
            System.out.println("ChadGPT: Please include the deadline date of your task after \"/by\" command.");
            return false;
        }

        try {   //Checks if it is possible to parse the user specified date into date time objects.
            String[] dates = this.input.split("/by ")[1].split(" ");
            String endDate = dates[0];
            String year = endDate.substring(0, 4);
            String month = endDate.substring(5, 7);
            String day = endDate.substring(8, 10);
            LocalDate endDateObj = LocalDate.parse(String.format("%s-%s-%s", year, month, day));
            if (dates.length > 1) {
                Integer.parseInt(dates[1]);
                LocalTime timeObj = LocalTime.parse(dates[1].substring(0, 2) + ":" + dates[1].substring(2));
            }
        } catch (NumberFormatException numberExcept) {
            System.out.println("ChadGPT: Please ensure the time of your deadline is in numerical format.");
            return false;
        } catch (IndexOutOfBoundsException | IllegalArgumentException formatExcept) {
            System.out.println("ChadGPT: Ensure that deadline date follows the following format: yyyy-mm-dd.");
            return false;
        }
        return true;
    }

    @Override
    protected void completeOperation(TaskList tasklist) {
        String[] delimitedBySlash = this.input.split("/");
        tasklist.addTask(new Deadline(delimitedBySlash[0].substring(9, delimitedBySlash[0]
                .length() - 1), delimitedBySlash[1].substring(3)));
    }
}
