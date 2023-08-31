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
 * The TaskList class handles all functionality relating to the list of tasks, such as addition, deletion,
 * update and display of tasks.
 */
public class TaskList {
    private List<Task> list;
    private final Storage storage;
    private final UI ui;

    TaskList(Storage storage, UI ui) {
        list = new ArrayList<>();
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Adds a task of type ToDo to the list of tasks.
     * @param task The ToDo command input by the user.
     * @throws BrunoException Thrown if description of ToDo is missing.
     */
    public void addToDo(String task) throws BrunoException {
        if (task.split(" ").length == 1) {
            System.out.print("\t");
            throw new BrunoEmptyException(task.split(" ")[0]);
        }
        list.add(new ToDo(task.substring(task.indexOf(" ") + 1)));
        String s = "\tWoof. I have added this task:\n\t\t" + list.get(list.size() - 1).getString();
        storage.writeToFile();
        ui.displayMessage(s);
    }

    /**
     * Adds a task of type Deadline to the list of tasks.
     * @param task The Deadline command input by the user.
     * @throws BrunoException Thrown if input is missing information.
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
        list.add(new Deadline(task.substring(task.indexOf(' ') + 1, task.indexOf('/') - 1),
                task.substring(task.lastIndexOf('/') + 4)));
        storage.writeToFile();
        String s = "\tWoof. I have added this task:\n\t\t" + list.get(list.size() - 1).getString();
        ui.displayMessage(s);
    }

    /**
     * Adds a task of type Event to the list of tasks.
     * @param task The Event command input by the user.
     * @throws BrunoException Thrown if input is missing information.
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
        list.add(new Event(task.substring(task.indexOf(' ') + 1, task.indexOf('/') - 1),
                task.substring(task.indexOf("from") + 5, task.lastIndexOf('/') - 1),
                task.substring(task.indexOf("to") + 3)));
        storage.writeToFile();
        String s = "\tWoof. I have added this task:\n\t\t" + list.get(list.size() - 1).getString();
        ui.displayMessage(s);
    }

    /**
     * Marks a task as done from the list of tasks.
     * @param task The Mark command input by the user.
     * @throws BrunoException Thrown if user tries to mark an invalid task.
     */
    public void markTask(String task) throws BrunoException {
        String markVal = task.split(" ")[1];
        try {
            int a = Integer.parseInt(markVal);
        } catch (NumberFormatException e) {
            throw new BrunoIntegerMismatchException("mark");
        }
        if (Integer.parseInt(markVal) > list.size()) {
            throw new BrunoIndexOutOfBoundsException("mark");
        }
        if (Integer.parseInt(markVal) < 0) {
            throw new BrunoNegativeArgException("mark");
        }
        list.get(Integer.parseInt(markVal) - 1).markAsDone();
        storage.writeToFile();
        String s =
                "\tWoof Woof! I have marked the task as done.\n\t" + list.get(Integer.parseInt(markVal) - 1)
                        .getString();
        ui.displayMessage(s);
    }

    /**
     * Unmarks a task to show that it is not done, from the list of tasks.
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
        if (Integer.parseInt(unmarkVal) > list.size()) {
            throw new BrunoIndexOutOfBoundsException("unmark");
        }
        if (Integer.parseInt(unmarkVal) < 0) {
            throw new BrunoNegativeArgException("unmark");
        }
        list.get(Integer.parseInt(unmarkVal) - 1).unMark();
        storage.writeToFile();
        String s = "\tOK, I have marked the task as not done yet.\n\t" + list.get(
                Integer.parseInt(unmarkVal) - 1).getString();
        ui.displayMessage(s);
    }

    /**
     * Deletes a task from the list of tasks.
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
        if (Integer.parseInt(deleteVal) > list.size()) {
            throw new BrunoIndexOutOfBoundsException("delete");
        }
        if (Integer.parseInt(deleteVal) < 0) {
            throw new BrunoNegativeArgException("delete");
        }
        String s1 = list.get(Integer.parseInt(deleteVal) - 1).getString();
        list.remove(Integer.parseInt(deleteVal) - 1);
        storage.writeToFile();
        String s = "\tI have removed this task from your list:\n\t" + s1;
        ui.displayMessage(s);
    }

    /**
     * Displays the number of tasks in the list.
     */
    public void displayListSum() {
        String s = "\tNow you have " + list.size() + (list.size() == 1 ? " task" : " tasks") + " in your "
                + "list.";
        ui.displayMessage(s);
    }

    /**
     * Displays the list of tasks.
     */
    public void displayList() {
        String s = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            s += "\t\t" + (i + 1) + ". " + list.get(i).getString() + "\n";
        }
        ui.displayMessage(s);
    }

    /**
     * Displays all the tasks the user has on a given date.
     * @param task The Schedule command input by the user.
     * @throws DateTimeException Thrown if date input by the user is not correct format.
     */
    public void showSchedule(String task) throws DateTimeException {
        String s = "";
        int counter = 0;
        LocalDate d = LocalDate.parse(task.split(" ")[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        for (Task t : list) {
            if (t instanceof Deadline) {
                if (d.isEqual(((Deadline) t).getBy().toLocalDate())) {
                    s = s + "\t" + (++counter) + ". " + t.getString() + "\n";
                }
            } else if (t instanceof Event) {
                if ((d.isAfter(((Event) t).getFrom().toLocalDate()) && d.isBefore(((Event) t).getBy().toLocalDate()))
                        || d.isEqual(((Event) t).getFrom().toLocalDate()) || d.isEqual((
                                (Event) t).getBy().toLocalDate())) {
                    s = s + "\t" + (++counter) + ". " + t.getString() + "\n";
                }
            }
        }
        if (counter == 0) {
            ui.displayMessage("\tYou have no deadlines or events on this date.");
        }
        ui.displayMessage(s);
    }

    public void setList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getList() {
        return this.list;
    }
}
