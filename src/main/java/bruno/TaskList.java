package bruno;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bruno.exceptions.BrunoEmptyException;
import bruno.exceptions.BrunoException;
import bruno.exceptions.BrunoIndexOutOfBoundsException;
import bruno.exceptions.BrunoIntegerMismatchException;
import bruno.exceptions.BrunoMissingDeadlineException;
import bruno.exceptions.BrunoMissingEventException;
import bruno.exceptions.BrunoNegativeArgException;
import bruno.task.Deadline;
import bruno.task.Event;
import bruno.task.Task;
import bruno.task.ToDo;

/**
 * The TaskList class handles all functionality relating to the tasks of tasks, such as addition, deletion,
 * update and display of tasks.
 */
public class TaskList {

    private List<Task> tasks;
    private Storage storage;
    private UI ui;

    TaskList(Storage storage, UI ui) {
        tasks = new ArrayList<>();
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Adds a task of type ToDo to the tasks of tasks.
     *
     * @param task The user input for task.
     * @throws BrunoException Thrown if user does not provide description for task.
     */
    public void addToDo(String task) throws BrunoException {
        if (task.split(" ").length == 1) {
            System.out.print("\t");
            throw new BrunoEmptyException(task.split(" ")[0]);
        }
        tasks.add(new ToDo(task.substring(task.indexOf(" ") + 1)));
        String taskInfo = "\tWoof. I have added this task:\n\t\t" + tasks.get(tasks.size() - 1).getString();
        storage.writeToFile(this);
        ui.displayMessage(taskInfo);
    }

    /**
     * Adds a task of type Deadline to the tasks of tasks.
     *
     * @param task The user input for the task.
     * @throws BrunoException Thrown if user does not provide description or deadline date/time.
     */
    public void addDeadline(String task) throws BrunoException {
        if (task.split(" ").length == 1 || task.indexOf('/') == 9) {
            System.out.print("\t");
            throw new BrunoEmptyException(task.split(" ")[0]);
        }
        if (!task.substring(task.indexOf("deadline") + 1).contains("/by")) {
            System.out.print("\t");
            throw new BrunoMissingDeadlineException();
        }
        tasks.add(new Deadline(task.substring(task.indexOf(' ') + 1, task.indexOf('/') - 1),
                task.substring(task.lastIndexOf('/') + 4)));
        storage.writeToFile(this);
        String taskInfo = "\tWoof. I have added this task:\n\t\t" + tasks.get(tasks.size() - 1).getString();
        ui.displayMessage(taskInfo);
    }

    /**
     * Adds a task of type Event to the tasks of tasks.
     *
     * @param task The user input for the task.
     * @throws BrunoException Thrown if the user does not provide description or start/end time.
     */
    public void addEvent(String task) throws BrunoException {
        if (task.split(" ").length == 1 || task.indexOf('/') == 6) {
            System.out.print("\t");
            throw new BrunoEmptyException(task.split(" ")[0]);
        }
        if (!task.substring(task.indexOf("event") + 1).contains("/from") || !task.substring(
                task.indexOf("event") + 1).contains("/to")) {
            System.out.print("\t");
            throw new BrunoMissingEventException();
        }
        tasks.add(new Event(task.substring(task.indexOf(' ') + 1, task.indexOf('/') - 1),
                task.substring(task.indexOf("from") + 5, task.lastIndexOf('/') - 1),
                task.substring(task.indexOf("to") + 3)));
        storage.writeToFile(this);
        String taskInfo = "\tWoof. I have added this task:\n\t\t" + tasks.get(tasks.size() - 1).getString();
        ui.displayMessage(taskInfo);
    }

    /**
     * Marks a task as done from the tasks of tasks.
     *
     * @param task The user input for the task.
     * @throws BrunoException Thrown if the user tries to mark an invalid task.
     */
    public void markTask(String task) throws BrunoException {
        String markVal = task.split(" ")[1];
        try {
            int a = Integer.parseInt(markVal);
        } catch (NumberFormatException e) {
            throw new BrunoIntegerMismatchException("mark");
        }
        if (Integer.parseInt(markVal) > tasks.size()) {
            throw new BrunoIndexOutOfBoundsException("mark");
        }
        if (Integer.parseInt(markVal) < 0) {
            throw new BrunoNegativeArgException("mark");
        }
        tasks.get(Integer.parseInt(markVal) - 1).markAsDone();
        storage.writeToFile(this);
        String taskInfo =
                "\tWoof Woof! I have marked the task as done.\n\t" + tasks.get(Integer.parseInt(markVal) - 1)
                        .getString();
        ui.displayMessage(taskInfo);
    }

    /**
     * Unmarks a task to show that it is not done, from the list of tasks.
     *
     * @param task The Unmark command input by the user.
     * @throws BrunoException Thrown if user tries to unmark an invalid task.
     */
    public void unmarkTask(String task) throws BrunoException {
        String unmarkVal = task.split(" ")[1];
        try {
            int a = Integer.parseInt(unmarkVal);
        } catch (NumberFormatException e) {
            throw new BrunoIntegerMismatchException("mark");
        }
        if (Integer.parseInt(unmarkVal) > tasks.size()) {
            throw new BrunoIndexOutOfBoundsException("unmark");
        }
        if (Integer.parseInt(unmarkVal) < 0) {
            throw new BrunoNegativeArgException("unmark");
        }
        tasks.get(Integer.parseInt(unmarkVal) - 1).unMark();
        storage.writeToFile(this);
        String taskInfo = "\tOK, I have marked the task as not done yet.\n\t" + tasks.get(
                Integer.parseInt(unmarkVal) - 1).getString();
        ui.displayMessage(taskInfo);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param task The Delete command input by the user.
     * @throws BrunoException Thrown if user tries to delete an invalid task.
     */
    public void deleteTask(String task) throws BrunoException {
        String deleteVal = task.split(" ")[1];
        try {
            int a = Integer.parseInt(deleteVal);
        } catch (NumberFormatException e) {
            throw new BrunoIntegerMismatchException("mark");
        }
        if (Integer.parseInt(deleteVal) > tasks.size()) {
            throw new BrunoIndexOutOfBoundsException("delete");
        }
        if (Integer.parseInt(deleteVal) < 0) {
            throw new BrunoNegativeArgException("delete");
        }
        String s1 = tasks.get(Integer.parseInt(deleteVal) - 1).getString();
        tasks.remove(Integer.parseInt(deleteVal) - 1);
        storage.writeToFile(this);
        String taskInfo = "\tI have removed this task from your tasks:\n\t" + s1;
        ui.displayMessage(taskInfo);
    }

    /**
     * Displays the number of tasks in the tasks.
     */
    public void displayListSum() {
        String taskInfo = "\tNow you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " "
                + "in your "
                + "tasks.";
        ui.displayMessage(taskInfo);
    }

    /**
     * Displays the tasks of tasks.
     */
    public void displayList() {
        String taskInfo = "\tHere are the tasks in your tasks:\n";
        for (int i = 0; i < tasks.size(); i++) {
            taskInfo += "\t\t" + (i + 1) + ". " + tasks.get(i).getString() + "\n";
        }
        ui.displayMessage(taskInfo);
    }

    /**
     * Displays all the tasks the user has on a given date.
     *
     * @param task The Schedule command input by the user.
     * @throws DateTimeException Thrown if date input by the user is not correct format.
     */
    public void showSchedule(String task) throws DateTimeException {
        String taskInfo = "";
        int counter = 0;
        LocalDate d = LocalDate.parse(task.split(" ")[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        for (Task t : tasks) {
            if (t instanceof Deadline) {
                if (d.isEqual(((Deadline) t).getBy().toLocalDate())) {
                    taskInfo = taskInfo + "\t" + (++counter) + ". " + t.getString() + "\n";
                }
            } else if (t instanceof Event) {
                if ((d.isAfter(((Event) t).getFrom().toLocalDate())
                        && d.isBefore(((Event) t).getBy().toLocalDate()))
                        || d.isEqual(((Event) t).getFrom().toLocalDate())
                        || d.isEqual(((Event) t).getBy().toLocalDate())) {
                    taskInfo = taskInfo + "\t" + (++counter) + ". " + t.getString() + "\n";
                }
            }
        }
        if (counter == 0) {
            ui.displayMessage("\tYou have no deadlines or events on this date.");
        }
        ui.displayMessage(taskInfo);
    }

    public void setList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getList() {
        return this.tasks;
    }
}
