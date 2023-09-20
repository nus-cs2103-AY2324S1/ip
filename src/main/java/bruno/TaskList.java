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
    public String addToDo(String task) throws BrunoException {
        if (task.split(" ").length == 1) {
            throw new BrunoEmptyException(task.split(" ")[0]);
        }
        String note = "";
        String description = "";
        if (task.contains("/n")) {
            note = task.split("/n")[1];
            description = task.substring(task.indexOf(" ") + 1, task.indexOf('/'));
        } else {
            description = task.substring(task.indexOf(" ") + 1);
        }
        Task todo = new ToDo(description, note);
        assert tasks != null;
        tasks.add(todo);
        storage.writeToFile(this);
        return ui.displayAddMessage(this);
    }

    /**
     * Adds a task of type Deadline to the tasks of tasks.
     *
     * @param task The user input for the task.
     * @throws BrunoException Thrown if user does not provide description or deadline date/time.
     */
    public String addDeadline(String task) throws BrunoException {
        if (task.split(" ").length == 1 || task.indexOf('/') == ("deadline".length() + 1)) {
            throw new BrunoEmptyException(task.split(" ")[0]);
        }
        if (!task.substring(task.indexOf("deadline") + 1).contains("/by")) {
            throw new BrunoMissingDeadlineException();
        }
        String description = task.substring(task.indexOf(' ') + 1, task.indexOf("/by") - 1);
        String by = "";
        String note = "";
        if (task.contains("/n")) {
            by = task.substring(task.indexOf("/by") + 4, task.indexOf("/n") - 1);
            note = task.substring(task.indexOf("/n") + 3);
        } else {
            by = task.substring(task.lastIndexOf('/') + 4);
        }
        Task deadline = new Deadline(description, by, note);
        assert tasks != null;
        tasks.add(deadline);
        storage.writeToFile(this);
        return ui.displayAddMessage(this);
    }

    /**
     * Adds a task of type Event to the tasks of tasks.
     *
     * @param task The user input for the task.
     * @throws BrunoException Thrown if the user does not provide description or start/end time.
     */
    public String addEvent(String task) throws BrunoException {
        if (task.split(" ").length == 1 || task.indexOf('/') == ("event".length() + 1)) {
            throw new BrunoEmptyException(task.split(" ")[0]);
        }
        if (!task.substring(task.indexOf("event") + 1).contains("/from") || !task.substring(
                task.indexOf("event") + 1).contains("/to")) {
            throw new BrunoMissingEventException();
        }
        String description = task.substring(task.indexOf(' ') + 1, task.indexOf("/from") - 1);
        String from = task.substring(task.indexOf("from") + 5, task.lastIndexOf("/to") - 1);
        String to = "";
        String note = "";
        if (task.contains("/n")) {
            to = task.substring(task.indexOf("to") + 3, task.indexOf("/n") - 1);
            note = task.substring(task.indexOf("/n") + 3);
        } else {
            to = task.substring(task.indexOf("to") + 3);
        }
        Task event = new Event(description, from, to, note);
        assert tasks != null;
        tasks.add(event);
        return ui.displayAddMessage(this);
    }

    /**
     * Marks a task as done from the tasks of tasks.
     *
     * @param task The user input for the task.
     * @throws BrunoException Thrown if the user tries to mark an invalid task.
     */
    public String markTask(String task) throws BrunoException {
        String[] parsedTask = task.split(" ");
        checkArgs(parsedTask[1], parsedTask[0], tasks.size());
        int markVal = Integer.parseInt(parsedTask[1]);
        tasks.get(markVal - 1).markAsDone();
        assert tasks.get(markVal - 1).checkDone() : "Task could not be marked";
        storage.writeToFile(this);
        return ui.displayMarkMessage(this, markVal);
    }

    /**
     * Unmarks a task to show that it is not done, from the list of tasks.
     *
     * @param task The Unmark command input by the user.
     * @throws BrunoException Thrown if user tries to unmark an invalid task.
     */
    public String unmarkTask(String task) throws BrunoException {
        String[] parsedTask = task.split(" ");
        checkArgs(parsedTask[1], parsedTask[0], tasks.size());
        int unmarkVal = Integer.parseInt(parsedTask[1]);
        tasks.get(unmarkVal - 1).unMark();
        assert !tasks.get(unmarkVal - 1).checkDone() : "Task could not be unmarked";
        storage.writeToFile(this);
        return ui.displayUnmarkMessage(this, unmarkVal);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param task The Delete command input by the user.
     * @throws BrunoException Thrown if user tries to delete an invalid task.
     */
    public String deleteTask(String task) throws BrunoException {
        String[] parsedTask = task.split(" ");
        checkArgs(parsedTask[1], parsedTask[0], tasks.size());
        int deleteVal = Integer.parseInt(parsedTask[1]);
        String taskString = tasks.get(deleteVal - 1).getString();
        int k = tasks.size();
        tasks.remove(deleteVal - 1);
        assert tasks.size() == (k - 1) : "Task could not be deleted";
        storage.writeToFile(this);
        return ui.displayDeleteMessage(taskString);
    }

    /**
     * Displays the number of tasks in the tasks.
     */
    public String displayListSum() {
        assert !tasks.isEmpty();
        return "Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " " + "in your "
                + "list.";
    }

    /**
     * Displays the tasks of tasks.
     */
    public String displayList() {
        String taskInfo = "";
        for (int i = 0; i < tasks.size(); i++) {
            String taskString = tasks.get(i).getString();
            String terminator = (i != tasks.size() - 1) ? "\n\n" : "";
            taskInfo += (i + 1) + ". " + taskString + terminator;
        }
        if (tasks.isEmpty()) {
            taskInfo = "You have no tasks to do.";
        } else {
            taskInfo = "Here is the list of your tasks:\n" + taskInfo;
        }
        return taskInfo;
    }

    /**
     * Displays all the tasks the user has on a given date.
     *
     * @param task The Schedule command input by the user.
     * @throws DateTimeException Thrown if date input by the user is not correct format.
     */
    public String showSchedule(String task) throws DateTimeException {
        String date = task.split(" ")[1];
        String taskInfo = "";
        int counter = 0;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate d = LocalDate.parse(date, format);
        for (Task t : tasks) {
            if (checkDate(t, d)) {
                taskInfo += (++counter) + ". " + t.getString() + "\n";
            }
        }
        return ui.displaySchedule(taskInfo, counter);
    }

    /**
     * Finds all tasks whose description contains the keyword.
     *
     * @param task The user input for the task.
     */
    public String findTasks(String task) {
        assert task != "" : "Search keyword must not be empty";
        String taskInfo = "";
        String keyWord = task.split(" ")[1];
        int counter = 0;
        for (Task t : tasks) {
            String description = t.getDescription();
            if (description.contains(keyWord)) {
                String taskString = t.getString();
                taskInfo += (++counter) + ". " + taskString + "\n";
            }
        }
        return ui.displaySearch(taskInfo, counter);
    }

    /**
     * Adds a note to an existing task from the list of tasks.
     *
     * @param task User input for the note to be added.
     */
    public String addNote(String task) throws BrunoException {
        String[] parsedTask = task.split(" ");
        checkArgs(parsedTask[1], parsedTask[0], tasks.size());
        int noteVal = Integer.parseInt(parsedTask[1]);
        String note = task.substring(task.indexOf(parsedTask[1]) + parsedTask[1].length() + 1);
        tasks.get(noteVal - 1).setNote(note);
        storage.writeToFile(this);
        return ui.displayNoteMessage(this, noteVal);
    }

    private static void checkArgs(String val, String task, int size) throws BrunoException {
        try {
            int a = Integer.parseInt(val);
        } catch (NumberFormatException e) {
            throw new BrunoIntegerMismatchException(task);
        }
        if (Integer.parseInt(val) > size) {
            throw new BrunoIndexOutOfBoundsException(task);
        }
        if (Integer.parseInt(val) < 0) {
            throw new BrunoNegativeArgException(task);
        }
    }

    public void setList(List<Task> list) {
        assert list != null : "List should not be null";
        this.tasks = list;
    }

    public List<Task> getList() {
        return this.tasks;
    }

    private static boolean checkDate(Task t, LocalDate d) {
        if (t instanceof Deadline) {
            LocalDate by = ((Deadline) t).getBy().toLocalDate();
            return d.isEqual(by);
        } else if (t instanceof Event) {
            LocalDate from = ((Event) t).getFrom().toLocalDate();
            LocalDate by = ((Event) t).getBy().toLocalDate();
            return (d.isAfter(from) && d.isBefore(by));
        } else {
            return false;
        }
    }
}
