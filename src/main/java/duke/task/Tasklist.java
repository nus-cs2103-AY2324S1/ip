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

/**
 * Represents a list of tasks with functionality to add, delete, mark, and unmark tasks.
 */
public class Tasklist {

    /**
     * The actual list storing the tasks.
     */
    private ArrayList<Task> todoList;

    /**
     * Initializes an empty task list.
     */
    public Tasklist() {
        this.todoList = new ArrayList<>();
    }

    /**
     * Marks a task as done based on the index provided.
     *
     * @param i Index of the task in the list.
     */
    public void mark(int i) {
        if (i > todoList.size() || i <= 0) {
            System.out.println("Please mark something in the list");
            return;
        }
        todoList.get(i - 1).mark();
        Task t = todoList.get(i - 1);
        Ui.mark(t);
    }

    /**
     * Unmarks a task based on the index provided.
     *
     * @param i Index of the task in the list.
     */
    public void unmark(int i) {
        if (i > todoList.size() || i <= 0) {
            System.out.println("Please unmark something in the list");
            return;
        }
        todoList.get(i - 1).unmark();
        Task t = todoList.get(i - 1);
        Ui.unmark(t);
    }

    /**
     * Adds a task to the list based on the provided command string.
     *
     * @param s Command string to parse and add the task.
     * @throws DukeMissingArgumentException If the required arguments for a task are missing.
     * @throws DukeInvalidArgumentException  If the provided arguments for a task are invalid.
     */
    public void addToList(String s) throws DukeMissingArgumentException, DukeInvalidArgumentException {
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
                Todo t = new Todo(str.substring(5, str.length()).toString());
                todoList.add(t);
                task = t;
            }
        } else if (cmd.equals("deadline")) {
            if (s.length() <= 9) {
                throw new DukeMissingArgumentException();
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
                    String t = str.substring(9, str.length()).toString();
                    String[] arr = t.split("/by ");
                    LocalDateTime deadline = LocalDateTime.parse(arr[1], formatter);
                    Deadline d = new Deadline(arr[0], deadline);
                    todoList.add(d);
                    task = d;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeMissingArgumentException();
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the start/end time in the format of <DD/MM/YY HH:MM>!");
                    return;
                }
            }
        } else if (cmd.equals("event")) {
            if (s.length() <= 6) {
                throw new DukeMissingArgumentException();
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
                    String t = str.substring(6, str.length()).toString();
                    String[] arr = t.split("/from ");
                    String[] times = arr[1].split(" /to ");
                    LocalDateTime startTime = LocalDateTime.parse(times[0], formatter);
                    LocalDateTime endTime = LocalDateTime.parse(times[1], formatter);
                    if (startTime.isAfter(endTime)) {
                        System.out.println("End time must be after the start time!");
                        return;
                    }
                    Event e = new Event(arr[0], startTime, endTime);
                    todoList.add(e);
                    task = e;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeMissingArgumentException();
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the start/end time in the format of <DD/MM/YY HH:MM>!");
                    return;
                }
            }
        } else {
            throw new DukeInvalidArgumentException();
        }
        Ui.add(task, todoList.size());
    }

    /**
     * Deletes a task from the list based on the provided index.
     *
     * @param i Index of the task to delete.
     */
    public void delete(int i) {
        if (i > todoList.size() || i <= 0) {
            System.out.println("Please delete something in the list");
            return;
        }
        Task t = todoList.get(i - 1);
        todoList.remove(i - 1);
        Ui.delete(t, todoList.size());
    }


    /**
     * Prints all tasks in the list to the console.
     */
    public void printList() {
        for (int i = 1; i <= this.todoList.size(); ++i) {
            Task t =  this.todoList.get(i - 1);
            System.out.println(i + ". " + t.toString());
        }
    }

    /**
     * Saves the current list of tasks to a file using a BufferedWriter.
     *
     * @param bw BufferedWriter to write the tasks to.
     * @throws IOException If an error occurs during writing.
     */
    public void saveList(BufferedWriter bw) throws IOException {
        for (Task task : this.todoList) {
            bw.write(task.stringifyTask());
            bw.newLine();
        }
    }

    public String find(String arg) {
        StringBuilder s = new StringBuilder();
        int i = 1;
        for (Task task : this.todoList) {
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
        for (Task task : this.todoList) {
            s.append(i++);
            s.append(". ");
            s.append(task.toString());
            s.append("\n");
        }
        return s.toString();
    }
}
