package duke.task;

import duke.exceptions.DukeInvalidArgumentException;
import duke.exceptions.DukeMissingArgumentException;
import duke.Parser;
import duke.Ui;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Represents a list of tasks with functionality to add, delete, mark, and unmark tasks.
 */
public class Tasklist {

    /**
     * The actual list storing the tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * A set to check if the any task shares the same name
     */
    private HashSet<String> set;

    /**
     * Initializes an empty task list.
     */
    public Tasklist() {
        this.set = new HashSet<>();
        this.tasks = new ArrayList<>();
    }

    /**
     * Marks a task as done based on the index provided.
     *
     * @param i Index of the task in the list.
     */
    public String mark(int i) {
        if (i > tasks.size() || i <= 0) {
            return "Please mark something in the list";
        }
        tasks.get(i - 1).mark();
        Task t = tasks.get(i - 1);
        return Ui.mark(t);
    }

    /**
     * Unmarks a task based on the index provided.
     *
     * @param i Index of the task in the list.
     */
    public String unmark(int i) {
        if (i > tasks.size() || i <= 0) {
            return "Please unmark something in the list";
        }
        tasks.get(i - 1).unmark();
        Task t = tasks.get(i - 1);
        return Ui.unmark(t);
    }

    /**
     * Adds a task to the list based on the provided command string.
     *
     * @param s Command string to parse and add the task.
     * @throws DukeMissingArgumentException If the required arguments for a task are missing.
     * @throws DukeInvalidArgumentException  If the provided arguments for a task are invalid.
     */
    public String addToList(String s) throws DukeMissingArgumentException, DukeInvalidArgumentException {
        StringBuilder str = new StringBuilder(s);
        String cmd = Parser.parseCommand(s);
        Task task = null;
        if (cmd == null) {
            throw new DukeInvalidArgumentException();
        }
        if (cmd.equals("todo")) {
            if (s.length() <= 5) {
                throw new DukeMissingArgumentException();
            } else {
                return addTodo(str);
            }
        } else if (cmd.equals("deadline")) {
            if (s.length() <= 9) {
                throw new DukeMissingArgumentException();
            } else {
                return addDeadline(str);
            }
        } else if (cmd.equals("event")) {
            if (s.length() <= 6) {
                throw new DukeMissingArgumentException();
            } else {
                return addEvent(str);
            }
        } else {
            throw new DukeInvalidArgumentException();
        }
    }

    private String addEvent(StringBuilder str) throws DukeMissingArgumentException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            String t = str.substring(6, str.length()).toString();
            String[] arr = t.split("/from ");
            String[] times = arr[1].split(" /to ");
            LocalDateTime startTime = LocalDateTime.parse(times[0], formatter);
            LocalDateTime endTime = LocalDateTime.parse(times[1], formatter);
            if (startTime.isAfter(endTime)) {
                return "End time must be after the start time!";
            }
            Event e = new Event(arr[0], startTime, endTime);
            if (set.contains(e.toString())) {
                return Ui.duplicateMessage(e);
            }
            set.add(e.toString());
            tasks.add(e);
            return Ui.add(e, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            return "Please enter the start/end time in the format of <DD/MM/YY HH:MM>!";
        }
    }
    private String addDeadline(StringBuilder str) throws DukeMissingArgumentException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            String t = str.substring(9, str.length()).toString();
            String[] arr = t.split("/by ");
            LocalDateTime deadline = LocalDateTime.parse(arr[1], formatter);
            Deadline d = new Deadline(arr[0], deadline);
            if (set.contains(d.toString())) {
                return Ui.duplicateMessage(d);
            }
            set.add(d.toString());
            tasks.add(d);
            return Ui.add(d, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            return "Please enter the start/end time in the format of <DD/MM/YY HH:MM>!";
        }
    }

    private String addTodo(StringBuilder str) {
        Todo t = new Todo(str.substring(5, str.length()).toString());
        if (set.contains(t.toString())) {
            return Ui.duplicateMessage(t);
        }
        set.add(t.toString());
        tasks.add(t);
        return Ui.add(t, tasks.size());
    }
    /**
     * Deletes a task from the list based on the provided index.
     *
     * @param i Index of the task to delete.
     */
    public String delete(int i) {
        if (i > tasks.size() || i <= 0) {
            return "Please delete something in the list";
        }
        Task t = tasks.get(i - 1);
        tasks.remove(i - 1);
        return Ui.delete(t, tasks.size());
    }


    /**
     * Prints all tasks in the list to the console.
     */
    public String printList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= this.tasks.size(); ++i) {
            Task t =  this.tasks.get(i - 1);
            stringBuilder.append(i + ". " + t.toString() + "\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Saves the current list of tasks to a file using a BufferedWriter.
     *
     * @param bw BufferedWriter to write the tasks to.
     * @throws IOException If an error occurs during writing.
     */
    public void saveList(BufferedWriter bw) throws IOException {
        for (Task task : this.tasks) {
            bw.write(task.stringifyTask());
            bw.newLine();
        }
    }

    public String find(String arg) {
        StringBuilder s = new StringBuilder();
        int i = 1;
        for (Task task : this.tasks) {
            if (task.find(arg)) {
                s.append(i++);
                s.append(". ");
                s.append(task.toString());
                s.append("\n");
            }
        }
        return s.toString();
    }

    /**
     * Converts the task list to a string representation.
     *
     * @return String representation of the task list.
     */

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int i = 1;
        for (Task task : this.tasks) {
            s.append(i++);
            s.append(". ");
            s.append(task.toString());
            s.append("\n");
        }
        return s.toString();
    }
}
