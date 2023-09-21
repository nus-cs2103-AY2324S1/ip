package noac.command;

import noac.util.Storage;
import noac.util.TaskList;
import noac.util.Ui;
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
     * @return String to be displayed to user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskArrayList = getTasksOnDate(this.localDate, tasks);

        return ui.showTasksOnDate(taskArrayList);
    }


    private ArrayList<Task> getTasksOnDate(LocalDate localDate, TaskList tasks) {
        ArrayList<Task> taskArrayList = new ArrayList<>();

        String formattedOnDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i) instanceof Deadline) {
                String deadlineByFormattedDate = ((Deadline) tasks.getTask(i)).getBy()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if (formattedOnDate.equals(deadlineByFormattedDate)) {
                    taskArrayList.add(tasks.getTask(i));
                }
            } else if (tasks.getTask(i) instanceof Event) {
                String eventFromFormattedDate = ((Event) tasks.getTask(i)).getFrom()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String eventToFormattedDate = ((Event) tasks.getTask(i)).getTo()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if (formattedOnDate.equals(eventFromFormattedDate)
                        || formattedOnDate.equals(eventToFormattedDate)) {
                    taskArrayList.add(tasks.getTask(i));
                }
            }
        }
        return taskArrayList;
    }
}
