package task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import exception.DukeException;

/**
 * Represents a list of Tasks a User can interact with.
 */
public class TaskList {
    /** A list containing all the User's tasks. */
    private List<Task> list;

    /**
     * Constructs a TaskList using a list of Tasks.
     * @param list
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds a Task to the list and writes the change into the local .txt file.
     * Returns a message to be printed by the UI.
     * @param task
     * @return message
     */
    public String add(Task task) {
        list.add(task);
        updateFile();
        return "I Gotchu. This task added successfully:\n" + task.toString()
                + "\nYeaboi only " + list.size() + " in the list.";
    }

    /**
     * Marks a Task as completed in the list and writes the change into the local .txt file.
     * Returns a message to be printed by the UI.
     * Throws a DukeException if index is out of bounds.
     * @param idx
     * @return message
     * @throws DukeException
     */
    public String mark(int idx) throws DukeException {
        if (idx > this.list.size()) {
            throw new DukeException("Out of bounds..");
        }

        Task task = this.list.get(idx - 1);
        task.toggleIsDone(true);
        updateFile();

        return "Noice! I've marked this task as donezo:\n" + task;
    }

    /**
     * Deletes a Task in the list and writes the change into the local .txt file.
     * Returns a message to be printed by the UI.
     * Throws a DukeException if index is out of bounds.
     * @param idx
     * @return message
     * @throws DukeException
     */
    public String delete(int idx) throws DukeException {
        if (idx > this.list.size()) {
            throw new DukeException("Out of bounds..");
        }

        Task task = this.list.remove(idx - 1);
        updateFile();

        return "Task remove UwU:\n" + task.toString();
    }

    /**
     * Marks a Task as uncompleted in the list and writes the change into the local .txt file.
     * Returns a message to be printed by the UI.
     * Throws a DukeException if index is out of bounds.
     * @param idx
     * @return message
     * @throws DukeException
     */
    public String unmark(int idx) throws DukeException {
        if (idx > this.list.size()) {
            throw new DukeException("Out of bounds..");
        }

        Task task = this.list.get(idx - 1);
        task.toggleIsDone(false);
        updateFile();

        return "OK, I've marked this task as not done yet bruh:\n" + task;
    }

    /**
     * Returns a String representation of the list of Task due on the
     * LocalDate specified by the User.
     * @param due
     * @return a String representation of the list of Task due
     */
    public String dueOn(LocalDate due) {
        List<Task> dueList = list.stream()
                .filter(task -> (task instanceof Deadline))
                .filter(deadline -> ((Deadline) deadline).by.equals(due))
                .collect(Collectors.toList());

        if (dueList.isEmpty()) {
            return "Nothing to see here...";
        } else {
            StringBuilder s = new StringBuilder("ALERT!! Due on " + due);

            for (Task t : dueList) {
                s.append("\n" + t.toString());
            }

            return s.toString();
        }
    }

    /**
     * Returns a String representation of the list of Task containing the
     * substring in its description specified by the User.
     * @param substring
     * @return a String representation of the list of Task found
     */
    public String find(String substring) {
        List<Task> subList = list.stream()
                .filter(task -> task.description.contains(substring))
                .collect(Collectors.toList());

        if (subList.isEmpty()) {
            return "No such Task...";
        } else {
            StringBuilder s = new StringBuilder("Do this:");

            for (Task t : subList) {
                s.append("\n" + t.toString());
            }

            return s.toString();
        }
    }

    /**
     * Returns a String representation of the Tasks in the list.
     * @return a String representation of the TaskList.
     */
    public String print() {
        StringBuilder s = new StringBuilder("");
        s.append("Here yo tasks in your list my g:");

        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            s.append("\n" + (i + 1) + "." + task.toString());
        }

        return s.toString();
    }

    /**
     * Returns a String representation of the TaskList for the .txt file.
     * @return a String representation
     */
    public String toFileString() {
        StringBuilder res = new StringBuilder("");

        for (Task t : list) {
            res.append(t.toFileString());
        }

        return res.toString();
    }

    /**
     * Writes the current String representation of the TaskList
     * into the local .txt file.
     * Throws an IOException if it fails.
     */
    public void updateFile() {
        try {
            String filePath = "./data/tasks.txt";
            FileWriter fileWriter = new FileWriter(filePath);

            String newContent = toFileString();
            fileWriter.write(newContent);

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("oops i done goofed");
        }
    }
}
