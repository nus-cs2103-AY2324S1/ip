package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Data structure that stores the bot's tasks,
 * along with its operations to manipulate the data structure.
 */
public class TaskList {
    /** ArrayList stores bot's tasks. **/
    private ArrayList<Task> tasks;

    /**
     * Constructor for Tasklist with initial tasks read from saved file.
     * @param inputList String array containing tasks from saved file
     */
    public TaskList(ArrayList<String[]> inputList) {
        tasks = new ArrayList<>();
        for (String[] line : inputList) {
            switch (line[0]) {
            case "T":
                ToDo t = new ToDo(line[2]);
                if (line[1].equals("1")) {
                    t.markAsDone();
                }
                tasks.add(t);
                break;
            case "D":
                Deadline d = new Deadline(line[2],
                        LocalDateTime.parse(line[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
                if (line[1].equals("1")) {
                    d.markAsDone();
                }
                tasks.add(d);
                break;
            case "E":
                Event e = new Event(line[2],
                        LocalDateTime.parse(line[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                        LocalDateTime.parse(line[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
                if (line[1].equals("1")) {
                    e.markAsDone();
                }
                tasks.add(e);
                break;
            }
        }
    }

    /**
     * Constructor for empty tasklist.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Deletes specified task from memory.
     * @param index Task number to be deleted
     * @throws DukeException errors that occur due to invalid user input
     */
    public void delete(int index) throws DukeException {
        if (index < 0 || index > tasks.size()-1) {
            throw new DukeException("Wow, deleting a nonexistent task? Check your tasks again with 'list'.");
        }
        Task t = tasks.get(index);
        tasks.remove(t);
        String returnLine = "Looks like you have more time to sleep now. Deleted this for you:\n"
                + t.toString()
                + "\nYou now have " + tasks.size() + " things to do.\n";
        System.out.println(returnLine);
    }

    /**
     * Adds a new Deadline task.
     * @param task Deadline to be added
     */
    public void addDeadline(Deadline task) {
        tasks.add(task);
    }

    /**
     * Adds a new Event task.
     * @param task Event to be added
     */
    public void addEvent(Event task) {
        tasks.add(task);
    }

    /**
     * Adds a new ToDo task.
     * @param task ToDo to be added
     */
    public void addToDo(ToDo task) {
        tasks.add(task);
    }

    /**
     * Adds a new Task into the tasklist.
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves specified task from tasklist
     * @param index task number to be retrieved
     * @return task specified
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns number of tasks in tasklist
     * @return number of tasks in tasklist
     */
    public int getLength() {
        return tasks.size();
    }

    /**
     * Returns a string representation of the tasks in the tasklist.
     * @return Tasks to write into save file
     */
    public String[] toWriteString() {
        String[] writtenTasks = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            writtenTasks[i] = tasks.get(i).toWriteString();
        }
        return writtenTasks;
    }

}
