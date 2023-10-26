package duke.commands;

import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.TaskList;

public class Sort extends Command{

    public Sort(Storage storage, Parser parser, TaskList tasks) {
        super(storage, parser, tasks);
    }

    @Override
    public String execute() {
        String message;
        if (!(parser.getTaskName().equals("by start date") || parser.getTaskName().equals("by end date"))) {
            message = "Sorry, I do not know how you want to sort the tasks";
        } else {

            if (parser.getTaskName().equals("by start date")) {
                tasks.sortByStartDate();
            }

            if (parser.getTaskName().equals("by end date")) {
                tasks.sortByEndDate();
            }

            store();
            message = "Your list is now sorted!\n\n";

            message = message + tasks.displayList();
        }

        return message;
    }
}