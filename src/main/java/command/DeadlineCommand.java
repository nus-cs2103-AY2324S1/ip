package command;

import duke.DateParser;
import duke.TaskList;
import duke.Ui;
import task.Deadline;

public class DeadlineCommand implements Command{

    public static String process(String input, TaskList tasks, Ui ui) {
        String result;

        try {
            int index = input.indexOf("/by");
            String date;
            if (DateParser.isValidDate(input.substring(index + 4))) {
                date = DateParser.parseDate(input.substring(index + 4));
            } else {
                date = input.substring(index + 4);
            }
            tasks.addTask(new Deadline(input.substring(9, index - 1), date));
            result = ui.deadLineSuccess(tasks.getTasks(tasks.getSize() - 1), tasks.getSize());
        } catch (StringIndexOutOfBoundsException err) {
            result = "☹ OOPS!!! The deadline format is incorrect! \n"
                    + "follow the format: deadline description /by end date";
        }

        return result;
    }

}
