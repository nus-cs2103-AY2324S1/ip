package command;

import duke.DateParser;
import duke.TaskList;
import duke.Ui;
import task.Event;

public class EventCommand implements Command{

    public static String process(String input, TaskList tasks, Ui ui) {
        String result;
        try {
            int indexFrom = input.indexOf("/from");
            int indexTo = input.indexOf("/to");

            String dateFrom;
            if (DateParser.isValidDate(input.substring(indexFrom + 6, indexTo - 1))) {
                dateFrom = DateParser.parseDate(input.substring(indexFrom + 6, indexTo - 1));
            } else {
                dateFrom = input.substring(indexFrom + 6, indexTo - 1);
            }

            String dateTo;
            if (DateParser.isValidDate(input.substring(indexTo + 4))) {
                dateTo = DateParser.parseDate(input.substring(indexTo + 4));
            } else {
                dateTo = input.substring(indexTo + 4);
            }

            tasks.addTask(new Event(input.substring(6, indexFrom - 1),
                    dateFrom,
                    dateTo));

            result = ui.eventSuccess(tasks.getTasks(tasks.getSize() - 1), tasks.getSize());
        } catch (StringIndexOutOfBoundsException err) {
            result = "☹ OOPS!!! The event format is incorrect! \n"
                    + "follow the format: event description /from start date /to end date";
        }

        return result;
    }
}
