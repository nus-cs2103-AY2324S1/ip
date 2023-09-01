package noac.command;

import noac.Storage;
import noac.TaskList;
import noac.Ui;
import noac.task.Deadline;
import noac.task.Event;
import noac.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * For executing the on command.
 */
public class OnCommand extends Command {

    private LocalDate localDate;

    /**
     * Create the OnCommand class.
     *
     * @param localDate The date to display all the tasks on that date.
     */
    public OnCommand(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Find all the task on the date and display it.
     *
     * @param tasks List of all the task.
     * @param ui UI for printing result to user.
     * @param storage Storage class meant for saving to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        ArrayList<Task> taskArrayList = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i) instanceof Deadline) {
                if (localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(((Deadline) tasks.getTask(i)).getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
                    taskArrayList.add(tasks.getTask(i));
                }
            } else if (tasks.getTask(i) instanceof Event) {
                if (localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(((Event) tasks.getTask(i)).getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) || localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(((Event) tasks.getTask(i)).getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
                    taskArrayList.add(tasks.getTask(i));
                }
            }

        }


        ui.showOnDate(taskArrayList);

    }
}
