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
        tasks.add(new ToDo(task.substring(task.indexOf(" ") + 1)));
        String taskInfo = "Woof. I have added this task:\n" + tasks.get(tasks.size() - 1).getString();
        storage.writeToFile(this);
        return taskInfo;
    }

    /**
     * Adds a task of type Deadline to the tasks of tasks.
     *
     * @param task The user input for the task.
     * @throws BrunoException Thrown if user does not provide description or deadline date/time.
     */
    public String addDeadline(String task) throws BrunoException {
        if (task.split(" ").length == 1 || task.indexOf('/') == 9) {
            throw new BrunoEmptyException(task.split(" ")[0]);
        }
        if (!task.substring(task.indexOf("deadline") + 1).contains("/by")) {
            throw new BrunoMissingDeadlineException();
        }
        tasks.add(new Deadline(task.substring(task.indexOf(' ') + 1, task.indexOf('/') - 1),
                task.substring(task.lastIndexOf('/') + 4)));
        storage.writeToFile(this);
        String taskInfo = "Woof. I have added this task:\n" + tasks.get(tasks.size() - 1).getString();
        return taskInfo;
    }

    /**
     * Adds a task of type Event to the tasks of tasks.
     *
     * @param task The user input for the task.
     * @throws BrunoException Thrown if the user does not provide description or start/end time.
     */
    public String addEvent(String task) throws BrunoException {
        if (task.split(" ").length == 1 || task.indexOf('/') == 6) {
            throw new BrunoEmptyException(task.split(" ")[0]);
        }
        if (!task.substring(task.indexOf("event") + 1).contains("/from") || !task.substring(
                task.indexOf("event") + 1).contains("/to")) {
            throw new BrunoMissingEventException();
        }
        tasks.add(new Event(task.substring(task.indexOf(' ') + 1, task.indexOf('/') - 1),
                task.substring(task.indexOf("from") + 5, task.lastIndexOf('/') - 1),
                task.substring(task.indexOf("to") + 3)));
        storage.writeToFile(this);
        String taskInfo = "Woof. I have added this task:\n" + tasks.get(tasks.size() - 1).getString();
        return taskInfo;
    }

    /**
     * Marks a task as done from the tasks of tasks.
     *
     * @param task The user input for the task.
     * @throws BrunoException Thrown if the user tries to mark an invalid task.
     */
    public String markTask(String task) throws BrunoException {
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
                "Woof Woof! I have marked the task as done.\n" + tasks.get(Integer.parseInt(markVal) - 1)
                        .getString();
        return taskInfo;
    }

    /**
     * Unmarks a task to show that it is not done, from the list of tasks.
     *
     * @param task The Unmark command input by the user.
     * @throws BrunoException Thrown if user tries to unmark an invalid task.
     */
    public String unmarkTask(String task) throws BrunoException {
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
        String taskInfo = "OK, I have marked the task as not done yet.\n" + tasks.get(
                Integer.parseInt(unmarkVal) - 1).getString();
        return taskInfo;
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param task The Delete command input by the user.
     * @throws BrunoException Thrown if user tries to delete an invalid task.
     */
    public String deleteTask(String task) throws BrunoException {
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
        String taskInfo = "I have removed this task from your tasks:\n" + s1;
        return taskInfo;
    }

    /**
     * Displays the number of tasks in the tasks.
     */
    public String displayListSum() {
        String taskInfo = "Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " "
                + "in your "
                + "tasks.";
        return taskInfo;
    }

    /**
     * Displays the tasks of tasks.
     */
    public String displayList() {
        String taskInfo = "Here are the tasks in your tasks:\n";
        for (int i = 0; i < tasks.size(); i++) {
            taskInfo += (i + 1) + ". " + tasks.get(i).getString() + ((i != tasks.size() - 1) ? "\n" : "");
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
        String taskInfo = "";
        int counter = 0;
        LocalDate d = LocalDate.parse(task.split(" ")[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        for (Task t : tasks) {
            if (t instanceof Deadline) {
                if (d.isEqual(((Deadline) t).getBy().toLocalDate())) {
                    taskInfo += (++counter) + ". " + t.getString() + "\n";
                }
            } else if (t instanceof Event) {
                if ((d.isAfter(((Event) t).getFrom().toLocalDate())
                        && d.isBefore(((Event) t).getBy().toLocalDate()))
                        || d.isEqual(((Event) t).getFrom().toLocalDate())
                        || d.isEqual(((Event) t).getBy().toLocalDate())) {
                    taskInfo += (++counter) + ". " + t.getString() + "\n";
                }
            }
        }
        if (counter == 0) {
            ui.displayMessage("You have no deadlines or events on this date.");
        }
        return taskInfo;
    }

    /**
     * Finds all tasks whose description contains the keyword.
     *
     * @param task The user input for the task.
     */
    public String findTasks(String task) {
        String taskInfo = "";
        String keyWord = task.split(" ")[1];
        int counter = 0;
        for (int i = 0; i < tasks.size(); i++) {
            String description = tasks.get(i).getDescription();
            if (description.contains(keyWord)) {
                taskInfo += (++counter) + ". " + tasks.get(i).getString() + "\n";
            }
        }
        if (counter == 0) {
            taskInfo += "There are no items matching your search.";
        } else {
            taskInfo = "Here are the tasks matching your search:\n" + taskInfo;
        }
        return taskInfo;
    }

    public void setList(List<Task> list) {
        this.tasks = list;
    }

    public List<Task> getList() {
        return this.tasks;
    }
}
