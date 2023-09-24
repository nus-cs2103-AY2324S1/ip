package urchatbot.commands;
import urchatbot.exception.URChatBotException;
import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Task;
import urchatbot.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds and lists all tasks in tasklist which the date matches the input date.
 */
public class PrintCommand extends Command {
    private String formattedDate;

    /**
     * Constructs the PrintCommand class.
     *
     * @param formattedDate Date that the users want to search.
     */
    public PrintCommand(String formattedDate) {
        super("Print");
        this.formattedDate = formattedDate;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws URChatBotException {
        int count = 0;
        List<Task> printList = new ArrayList<>();
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            Task task = tasks.getTasks().get(i);
            if (task.toString().contains(formattedDate)) {
                count++;
                printList.add(task);
            }
        }
        if (count <= 1) {
            return ui.showPrintMessage(count, formattedDate, printList);
        } else {
            return ui.showPrintMessagePlural(count, formattedDate, printList);
        }
    }
}
